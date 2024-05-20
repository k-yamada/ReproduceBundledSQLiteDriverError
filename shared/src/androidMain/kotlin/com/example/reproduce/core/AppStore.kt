package com.example.reproduce.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AppState(
    val mainState: MainState = MainState(),
    ) : ReduxState {
    companion object Factory {
        fun create(): AppState {
            return AppState()
        }
    }
}

class AppStore() : Store<AppState, Action, Effect>,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(AppState())
    private val sideEffect = MutableSharedFlow<Effect>()
    private val mainReducer = MainReducer(this)

    override fun observeState(): StateFlow<AppState> = state

    override fun observeSideEffect(): Flow<Effect> = sideEffect

    override fun dispatch(action: Action) {
        val oldState: AppState = state.value

        val newState: AppState = when (action) {
            is MainAction -> oldState.copy(mainState = mainReducer.reduce(action, oldState))
            else -> oldState
        }

        if (newState != oldState) {
            state.value = newState
        }
    }

    suspend fun emitSideEffect(effect: Effect) {
        sideEffect.emit(effect)
    }
}

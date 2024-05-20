package com.example.reproduce.core

import com.example.reproduce.DataRepository
import com.example.reproduce.model.BookmarkData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class MainState(
    val bookmarks: List<BookmarkData> = emptyList()
) : ReduxState

sealed class MainAction : Action {
    data object OnStarted : MainAction()
    data class OnLoadBookmarks(val bookmarks: List<BookmarkData>) : MainAction()
    data object OnTapAddButton : MainAction()
    data class OnTapDeleteButton(val bookmark: BookmarkData) : MainAction()
}

sealed class MainSideEffect : Effect() {
}

class MainReducer(val store: AppStore) :
    CoroutineScope by CoroutineScope(Dispatchers.Main), KoinComponent {

    private val dataRepository: DataRepository by inject()

    fun reduce(
        action: MainAction,
        state: AppState?
    ): MainState {
        val oldState = state?.mainState ?: MainState()
        return when (action) {
            is MainAction.OnStarted -> {
                launch {
                    loadBookmarks()
                }
                oldState
            }

            is MainAction.OnLoadBookmarks -> {
                oldState.copy(bookmarks = action.bookmarks)
            }

            is MainAction.OnTapAddButton -> {
                launch {
                    dataRepository.bookmarkRepository.insert(
                        BookmarkData(
                            name = Clock.System.now().toString()
                        )
                    )
                    loadBookmarks()
                }
                oldState
            }

            is MainAction.OnTapDeleteButton -> {
                launch {
                    dataRepository.bookmarkRepository.delete(action.bookmark)
                    loadBookmarks()
                }
                oldState
            }
        }
    }

    private suspend fun loadBookmarks() {
        val bookmarks = dataRepository.bookmarkRepository.all()
        store.dispatch(MainAction.OnLoadBookmarks(bookmarks))
    }
}

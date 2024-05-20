package com.example.reproduce.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import com.example.reproduce.core.AppState
import com.example.reproduce.core.AppStore
import com.example.reproduce.core.MainAction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val store: AppStore by inject()
        val state = store.observeState().collectAsState()

        LifecycleEffect(
            onStarted = {
                store.dispatch(MainAction.OnStarted)
            },
            onDisposed = {}
        )

        MainContent(store, state.value)
    }
}

@Composable
fun MainContent(store: AppStore, appState: AppState) {
    val state = appState.mainState

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(modifier = Modifier.padding(8.dp), onClick = {
            store.dispatch(MainAction.OnTapAddButton)
        }) {
            Text("Add")
        }

        LazyColumn(modifier = Modifier.weight(1f, true)) {
            items(state.bookmarks) { bookmark ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = bookmark.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            store.dispatch(MainAction.OnTapDeleteButton(bookmark))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

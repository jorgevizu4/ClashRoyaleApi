package com.vizu.clashroyaleapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.vizu.clashroyaleapi.data.database.RepositorioAutenticacionFirebase
import com.vizu.clashroyaleapi.data.model.Arena
import com.vizu.clashroyaleapi.data.model.Card
import com.vizu.clashroyaleapi.presentation.viewmodel.ClashRoyaleViewModel
import com.vizu.clashroyaleapi.presentation.viewmodel.UiState

@Composable
fun ArenasListScreen(
    vm: ClashRoyaleViewModel,
    repositorio: RepositorioAutenticacionFirebase,
    navController: NavHostController
) {
    when (val state = vm.stateArenas) {
        UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Error: Can´t load cards")
            Spacer(Modifier.height(12.dp))
            Button(onClick = { vm.loadCards() }) {
                Text("Reintentar")
            }
        }

        is UiState.Success -> LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.data.distinctBy { it.key }, key = { it.id }) { arena ->
                ArenaItem(arena)
            }
        }
    }
}

@Composable
private fun ArenaItem(arena: Arena) {
    val imgUrl =
        buildImageUrl("https://raw.githubusercontent.com/RoyaleAPI/cr-api-assets/refs/heads/master/arenas/${arena.key}.png")
    val painter = rememberAsyncImagePainter(imgUrl)

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Imagen con estados
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(strokeWidth = 2.dp)
                    }

                    is AsyncImagePainter.State.Error -> {
                        Text("IMG ", style = MaterialTheme.typography.labelSmall)
                    }

                    else -> Unit
                }

                Image(
                    painter = painter,
                    contentDescription = arena.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.width(12.dp))

            // Texto
            Column(Modifier.weight(1f)) {
                Text(
                    text = arena.title ?: "—",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = arena.subtitle ?: "—",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun buildImageUrl(cardPath: String?): String? {
    if (cardPath.isNullOrBlank()) return null

    return if (cardPath.startsWith("http")) {
        cardPath
    } else {
        // CDN oficial de la API
        //"https://raw.githubusercontent.com/RoyaleAPI/cr-api-assets/tree/master/cards-150-gold/$cardPath"
        null
    }
}
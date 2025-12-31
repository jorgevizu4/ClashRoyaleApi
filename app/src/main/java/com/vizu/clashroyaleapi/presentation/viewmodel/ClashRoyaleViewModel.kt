package com.vizu.clashroyaleapi.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vizu.clashroyaleapi.data.model.Arena
import com.vizu.clashroyaleapi.data.model.Card
import com.vizu.clashroyaleapi.data.remote.ClashRoyaleNetwork
import com.vizu.clashroyaleapi.data.repository.ClashRoyaleRepository
import kotlinx.coroutines.launch

class ClashRoyaleViewModel : ViewModel() {
    private val repository = ClashRoyaleRepository(ClashRoyaleNetwork.api)

    var stateCards by mutableStateOf<UiState<List<Card>>>(UiState.Loading)
        private set
    var stateArenas by mutableStateOf<UiState<List<Arena>>>(UiState.Loading)
        private set


    init {
        loadCards()
        loadArenas()
    }

    private fun loadArenas() {
        viewModelScope.launch {
            stateArenas = UiState.Loading
            stateArenas = try {
                UiState.Success(repository.getArenas())
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadCards() {
        viewModelScope.launch {
            stateCards = UiState.Loading
            stateCards = try {
                UiState.Success(repository.getCards())
                } catch (e: Exception) {
                UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
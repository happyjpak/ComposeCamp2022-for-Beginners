package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.determineDessertToShow
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    /**
     * Determine which dessert to show.
     */
    fun determineDessertToIndex(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0

        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertIndex
    }

    fun onDessertClicked() {
        // Update the revenue
        _uiState.update { dessertUiState ->
            val dessertsSold = dessertUiState.dessertsSold + 1
            val nextDessertIndex = determineDessertToIndex(dessertsSold)
            dessertUiState.copy(
                revenue = dessertUiState.revenue + dessertUiState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertIndex = nextDessertIndex,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
                currentDessertPrice = dessertList[nextDessertIndex].price,
            )
        }
    }

}
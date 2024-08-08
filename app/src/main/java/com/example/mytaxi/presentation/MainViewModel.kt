package com.example.mytaxi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytaxi.domain.LocationRepository
import com.example.mytaxi.presentation.intent.MainScreenEvents
import com.example.mytaxi.presentation.intent.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun reduce(intent: MainScreenEvents) {
        when (intent) {
            is MainScreenEvents.GetCurrentLocation -> fetchLocation()
        }
    }

    private fun fetchLocation() {
        viewModelScope.launch {
            locationRepository.getCurrentLocation().onStart {
                _state.update { it.copy(isLoading = true) }
            }.collect { location ->
                _state.update { it.copy(currentLocation = location, isLoading = false) }
                locationRepository.saveLocation(location)
            }
        }
    }
}

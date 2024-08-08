package com.example.mytaxi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytaxi.domain.LocationRepository
import com.example.mytaxi.presentation.intent.MainScreenState
import com.example.mytaxi.presentation.intent.MainScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state

    fun reduce(intent: MainScreenEvents) {
        when (intent) {
            is MainScreenEvents.GetCurrentLocation -> fetchLocation()
        }
    }

    private fun fetchLocation() {
        viewModelScope.launch {
            locationRepository.getCurrentLocation().collect { location ->
                _state.update { it.copy(currentLocation = location) }
                location?.let {
                    locationRepository.saveLocation(it)
                }
            }
        }
    }
}

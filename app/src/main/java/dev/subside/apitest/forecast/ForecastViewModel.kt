package dev.subside.apitest.forecast

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.subside.apitest.forecast.api.ForecastService
import dev.subside.apitest.forecast.models.ForecastData
import kotlinx.coroutines.launch

/**
 * This ViewModel is in charge of containing the LiveData object, and the communication with
 * the service retrieving it.
 */
class ForecastViewModel @ViewModelInject constructor(
    private val forecastService: ForecastService,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /** A livedata object containing the most recent ForecastData object */
    val forecast: MutableLiveData<ForecastData> = MutableLiveData()

    /** This will refresh the forecast data */
    fun load(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            forecast.postValue(forecastService.getForecastData(latitude, longitude))
        }
    }
}
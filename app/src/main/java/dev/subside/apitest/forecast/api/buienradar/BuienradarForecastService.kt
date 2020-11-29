package dev.subside.apitest.forecast.api.buienradar

import dev.subside.apitest.forecast.api.ForecastService
import dev.subside.apitest.forecast.models.Forecast
import dev.subside.apitest.forecast.models.ForecastData

/**
 * This is just an intermediate service between the ForecastService and BuienradarApiService
 * I made this service because the Buienradar structure is very specific, and I wanted a more broad
 * definition so it is easier to implement for other platforms. So this service just maps the
 * Buienradar data object to our general data object
 */
class BuienradarForecastService(
    val buienradarApiService: BuienradarApiService
) : ForecastService {
    override suspend fun getForecastData(latitude: Double, longitude: Double): ForecastData {
        val data = buienradarApiService.getForecastData(latitude, longitude)
        return map(data)
    }

    /** A simple function that will map our buienradar forecast data to our generic forecast data */
    fun map(forecast: BuienradarForecastData): ForecastData {
        return ForecastData(
            forecast.createdUtc,
            forecast.lat,
            forecast.lon,
            forecast.forecasts.map { Forecast(it.datetime, it.value) }
        )
    }
}
package dev.subside.apitest.forecast.api.test

import dev.subside.apitest.forecast.api.ForecastService
import dev.subside.apitest.forecast.models.Forecast
import dev.subside.apitest.forecast.models.ForecastData
import java.util.*

/**
 * Just a test forecast service for if it isn't raining :')
 */
class TestForecastService : ForecastService {
    override suspend fun getForecastData(latitude: Double, longitude: Double): ForecastData {
        return ForecastData(
            Date(),
            latitude,
            longitude,
            listOf(
                Forecast(Date(), 10),
                Forecast(Date(), 50),
                Forecast(Date(), 90),
                Forecast(Date(), 0),
                Forecast(Date(), 10),
            )
        )
    }
}
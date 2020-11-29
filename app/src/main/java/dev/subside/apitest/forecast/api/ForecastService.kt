package dev.subside.apitest.forecast.api

import dev.subside.apitest.forecast.models.ForecastData

/** Our forecast interface which we use to grab our data */
interface ForecastService {

    /**
     * The forecast data for the given latitude and longitude
     * @param latitude  The latitude
     * @param longitude The longitude
     * @return          A ForecastData object
     */
    suspend fun getForecastData(latitude: Double, longitude: Double): ForecastData
}
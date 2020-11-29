package dev.subside.apitest.forecast.api.buienradar

import retrofit2.http.GET
import retrofit2.http.Query

/** The interface that we use to build a service with Retrofit */
interface BuienradarApiService {
    @GET("forecast/geo/Rain3Hour")
    suspend fun getForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): BuienradarForecastData
}
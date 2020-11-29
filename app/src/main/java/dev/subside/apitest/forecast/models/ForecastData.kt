package dev.subside.apitest.forecast.models

import java.io.Serializable
import java.util.*

/**
 * Forecast data that we receive from our service
 *
 * @param dateUpdated   The date this data was updated
 * @param latitude      The latitude of this data
 * @param longitude     The longitude of this data
 * @param forecasts      A list of Forecast objects
 */
data class ForecastData(
    val dateUpdated: Date,
    val latitude: Double,
    val longitude: Double,
    val forecasts: List<Forecast>,
): Serializable
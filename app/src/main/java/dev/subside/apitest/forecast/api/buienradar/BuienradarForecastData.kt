package dev.subside.apitest.forecast.api.buienradar

import java.util.*

/**
 * This is the exact structure we receive from the Buienradar api
 */
data class BuienradarForecastData(
    val color: String,
    val lat: Double,
    val lon: Double,
    val borders: List<Border>,
    val timeOffset: Int,
    val radius: Int,
    val forecasts: List<Forecast>,
    val emptytext: String,
    val createdUtc: Date,
    val lastRefreshUtc: Date,
) {

    data class Border(
        val title: String,
        val lower: Int,
        val upper: Int,
    )

    data class Forecast(
        val datetime: Date,
        val utcdatetime: Date,
        val precipitation: Int,
        val precipation: Int,
        val original: Int,
        val value: Int,
    )
}




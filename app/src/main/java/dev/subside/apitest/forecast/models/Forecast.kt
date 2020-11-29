package dev.subside.apitest.forecast.models

import android.content.Context
import dev.subside.apitest.R
import java.io.Serializable
import java.util.*


/**
 * A simple object containing a forecast for a given date
 *
 * @param date      The date this forecast is tied to
 * @param intensity The intensity of the precipitation between 0 and 100 where 0 is no
 *                  precipitation and 100 is very heavy precipitation
 */
data class Forecast(
    val date: Date,
    val intensity: Int,
): Serializable {

    companion object {
        /**
         * A function that will return a string based on intensity
         *
         * @param context   The context to grab the string from
         * @param intensity The intensity of this forecast
         * @return          A string based on the intensity
         */
        fun getIntensityText(context: Context, intensity: Int): String {
            return context.getString(
                when {
                    intensity == 0 -> R.string.intensity_none
                    intensity < 40 -> R.string.intensity_low
                    intensity < 70 -> R.string.intensity_medium
                    else -> R.string.intensity_heavy
                }
            )
        }
    }
}
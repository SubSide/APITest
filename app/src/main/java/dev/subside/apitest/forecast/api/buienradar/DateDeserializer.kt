package dev.subside.apitest.forecast.api.buienradar

import android.annotation.SuppressLint
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.IllegalArgumentException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * As the date we receive from Buienradar is in a specific format, we make sure it will be
 * correctly deserialized by providing our own deserializer
 */
class DateDeserializer : JsonDeserializer<Date> {
    @SuppressLint("SimpleDateFormat")
    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Date {
        return sdf.parse(json.asString)
            ?: throw IllegalArgumentException("Couldn't parse $json to Date")
    }
}
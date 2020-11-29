package dev.subside.apitest.forecast.api.buienradar

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * The module that will automatically provide the Buienradar API service
 */
@Module
@InstallIn(ActivityComponent::class)
object BuienradarApiModule {
    @Provides
    fun provideBuienradarApiService(): BuienradarApiService {
        // Create a Gson object with our DateDeserializer
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()

        // Create a converter factory with our custom gson object
        val converter = GsonConverterFactory.create(gson)

        // Create a retrofit instance with the the buienradar base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://graphdata.buienradar.nl/2.0/")
            .addConverterFactory(converter)
            .build()

        // Create a retrofit service from our interface
        return retrofit.create(BuienradarApiService::class.java)
    }
}
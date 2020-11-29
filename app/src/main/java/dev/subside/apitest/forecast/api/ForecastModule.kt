package dev.subside.apitest.forecast.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.subside.apitest.forecast.api.buienradar.BuienradarApiService
import dev.subside.apitest.forecast.api.buienradar.BuienradarForecastService
import dev.subside.apitest.forecast.api.test.TestForecastService

@Module
@InstallIn(ActivityComponent::class)
object ForecastModule {
    @Provides
    fun provideForecastService(
        buienradarApiService: BuienradarApiService
    ): ForecastService {
//        return BuienradarForecastService(buienradarApiService)
        return TestForecastService()
    }
}
package dev.subside.apitest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** For Hilt to work we need an application annotated with the @HiltAndroidApp annotation */
@HiltAndroidApp
class ExampleApplication : Application()
package dev.subside.apitest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Our activity whose only job is to load the navigation graph
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity main as contentView
        setContentView(R.layout.activity_main)
        // And setup the toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
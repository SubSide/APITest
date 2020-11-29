package dev.subside.apitest.forecast

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.subside.apitest.R
import dev.subside.apitest.forecast.models.Forecast
import java.text.SimpleDateFormat

/**
 * I was really out of ideas on what to display in the details fragment, so it just shows time
 * and intensity
 */
@AndroidEntryPoint
class ForecastDetailsFragment : Fragment() {

    // Our parsed forecast data
    private lateinit var forecast: Forecast

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Grab the forecast from the arguments
        forecast = ForecastDetailsFragmentArgs.fromBundle(requireArguments()).forecast
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the date
        view.findViewById<TextView>(R.id.date).text = sdf.format(forecast.date)

        // Set the text based on intensity
        view.findViewById<TextView>(R.id.intensity).text =
            Forecast.getIntensityText(view.context, forecast.intensity)
    }
}
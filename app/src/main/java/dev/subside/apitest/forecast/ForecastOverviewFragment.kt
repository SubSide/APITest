package dev.subside.apitest.forecast

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dev.subside.apitest.R
import dev.subside.apitest.forecast.models.ForecastData
import java.text.SimpleDateFormat

/**
 * Displays a list of forecasts, and when it was refreshed. It also contains a FAB for refreshing
 * the data.
 */
@SuppressLint("SimpleDateFormat")
@AndroidEntryPoint
class ForecastOverviewFragment : Fragment(), Observer<ForecastData> {
    private val viewModel: ForecastViewModel by viewModels()

    // Our recyclerview adapter
    private val forecastAdapter: ForecastAdapter by lazy { ForecastAdapter() }
    // The format how we display the time the data was updated
    private val dateUpdatedSdf: SimpleDateFormat by lazy { SimpleDateFormat("HH:mm") }

    private lateinit var lastUpdatedView: TextView
    private lateinit var noPrecipitationView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Some view caching
        lastUpdatedView = view.findViewById(R.id.lastUpdated)
        noPrecipitationView = view.findViewById(R.id.noPrecipitation)

        // Set up the recyclerview adapter
        view.findViewById<RecyclerView>(R.id.forecasts).let {
            it.layoutManager = LinearLayoutManager(view.context)
            it.adapter = forecastAdapter
        }

        // Do first forecast load
        viewModel.load(LATITUDE, LONGITUDE)

        // Also add the onclick for the FAB
        view.findViewById<FloatingActionButton>(R.id.refresh).setOnClickListener {
            viewModel.load(LATITUDE, LONGITUDE)
        }

        // Observe the forecasts
        viewModel.forecast.observe(viewLifecycleOwner, this)
    }

    /** Called when we receive a new ForecastData object */
    override fun onChanged(data: ForecastData?) {
        Log.d("ForecastOverview", "Received new forecast data: $data")
        // If the data is for some reason null, we just cancel for now
        if (data == null) return

        // Filter a list of relevant forecasts
        val forecasts = data.forecasts.filter { it.intensity > 0 }

        // Update the items in the adapter
        forecastAdapter.items = forecasts

        // Update the lastUpdated text field
        lastUpdatedView.text =
            getString(R.string.last_updated, dateUpdatedSdf.format(data.dateUpdated))

        // If there are no relevant forecasts show the NoPrecipitationView
        noPrecipitationView.visibility = if (forecasts.isEmpty()) View.VISIBLE else View.GONE
    }


    companion object {
        // For now we use a constant val for latitude and longitude, this could eventually be
        // moved to use GPS or in settings
        const val LATITUDE: Double = 52.16
        const val LONGITUDE: Double = 4.49
    }
}
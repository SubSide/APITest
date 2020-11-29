package dev.subside.apitest.forecast

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.subside.apitest.R
import dev.subside.apitest.forecast.models.Forecast
import java.text.SimpleDateFormat

/** Our recyclerview adapter for displaying forecasts */
class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("HH:mm")

    var items: List<Forecast> = emptyList()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_overview_list_item, parent, false)

        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = items[position]

        // Load in the date
        holder.time.text = sdf.format(forecast.date)
        // Load in the text based on intensity
        holder.severity.text =
            Forecast.getIntensityText(holder.itemView.context, forecast.intensity)

        // Add the onClick listener to our detail page
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(
                R.id.forecastDetailsFragment,
                ForecastDetailsFragmentArgs(forecast).toBundle(),
            )
        }
    }


    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
        val severity: TextView = itemView.findViewById(R.id.severity)
    }
}
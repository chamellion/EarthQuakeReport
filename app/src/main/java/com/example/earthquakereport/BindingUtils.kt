package com.example.earthquakereport

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.earthquakereport.domain.EarthQuakeDomain
import com.example.earthquakereport.earthQuakeNetworkAPI.EarthQuake
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bindMagnitude")
fun TextView.bindMagnitude(earthQuake: EarthQuakeDomain?) {
    val mag = earthQuake?.mag
    val decimalFormat = DecimalFormat("0.0")
    val formattedMag = decimalFormat.format(mag)
    text = formattedMag.toString()
}
@BindingAdapter("bindFirstLocation")
fun bindLocationOne(textView: TextView, property: EarthQuakeDomain?) {
    val locationSeparator = "of"
    property?.let {
        val location = property.place
        if (location.contains(locationSeparator)) {
            val split = location.split(locationSeparator)
            val splitOne = split[0] + locationSeparator
            textView.text = splitOne
        }
    }
}
@BindingAdapter("bindSecondLocation")
fun TextView.bindLocationTwo(property: EarthQuakeDomain?) {
    val locationSeparator = "of"
    property?.let {
      val location = property.place
        if (location.contains(locationSeparator)){
           val split = location.split(locationSeparator)
            val splitTwo = split[1]
            text = splitTwo
        }
    }
}
@BindingAdapter("bindDateToTextView")
fun TextView.bindTimeInDate(property: EarthQuakeDomain?) {
    property?.let {
        val epochTimeToDate = Date(it.time)
        val dateFormat = SimpleDateFormat("LLL dd, yyyy")
        val formattedDate = dateFormat.format(epochTimeToDate)
        text = formattedDate.toString()
    }
}

@BindingAdapter("bindTimeToTextView")
fun TextView.bindTimeInTime(property: EarthQuakeDomain?) {
    property?.let {
        val epochTimeToDate = Date(it.time)
        val dateFormat = SimpleDateFormat("h:mm a")
        val formattedDate = dateFormat.format(epochTimeToDate)
        text = formattedDate.toString()
    }
}


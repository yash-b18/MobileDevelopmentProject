package com.example.weatherapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class ForecastDetailsViewModelFactory(private val args: WeatherDetailsFragmentArgs) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastDetailsViewModel::class.java)) {
            return ForecastDetailsViewModel(args) as T
        }
        throw IllegalArgumentException("Error in Class")
    }
}

class ForecastDetailsViewModel(args: WeatherDetailsFragmentArgs) : ViewModel() {

    private val weatherLiveData : MutableLiveData<WeatherDetails> = MutableLiveData()
    val details : LiveData<WeatherDetails> = weatherLiveData

    init {
        weatherLiveData.value = WeatherDetails(
            temperature = args.temp,
            description = args.description,
            date = DATE_FORMAT.format(Date(args.date * 1000)),
            icon = "http://openweathermap.org/img/wn/${args.icon}@2x.png"
        )
    }
}

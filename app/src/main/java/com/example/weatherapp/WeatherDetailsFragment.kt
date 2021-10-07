package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding


class WeatherDetailsFragment : Fragment()
{
    private val args: WeatherDetailsFragmentArgs by navArgs()

    private lateinit var viewModelFactory: ForecastDetailsViewModelFactory
    private val viewModel: ForecastDetailsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var temperatureSettings: TemperatureSettings

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        temperatureSettings = TemperatureSettings(requireContext())
        viewModelFactory = ForecastDetailsViewModelFactory(args)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<WeatherDetails> { viewState ->
            binding.tempText.text = formatTempForDisplay(viewState.temperature, temperatureSettings.getTempSetting())
            binding.descriptionText.text = viewState.description
            binding.dateText.text = viewState.date
            binding.forecastIcon.load(viewState.icon)
        }
        viewModel.details.observe(viewLifecycleOwner, viewStateObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
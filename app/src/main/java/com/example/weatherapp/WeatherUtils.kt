package com.example.weatherapp

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting) : String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F°", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C°", temp)
        }
    }
}

fun showTempDisplaySettingDialog(context: Context, temperatureSettings: TemperatureSettings) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose Display Units")
        .setMessage("Choose Temperature Unit")
        .setPositiveButton("F°", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
               temperatureSettings.updateSetting(TempDisplaySetting.Fahrenheit)
            }
        })
        .setNeutralButton("C°") { _,_ ->
            temperatureSettings.updateSetting(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener {
            Toast.makeText(context, "Restart App to change unit", Toast.LENGTH_SHORT).show()
        }

    dialogBuilder.show()
}
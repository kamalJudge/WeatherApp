package com.kamalpreet.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getEditText = findViewById<EditText>(R.id.et_City)
        var getForecastButton = findViewById<Button>(R.id.button_getForecast)
        getForecastButton.setOnClickListener{

            var intent  = Intent(this, ForecastActivity::class.java)
            intent.putExtra("userValue", getEditText.text.toString())
            startActivity(intent)
        }
    }
}

package com.kamalpreet.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        var userValue = intent.extras.getString("userValue")
        var retriever = WeatherRetriever()
        var callback = object : Callback<Weather>
        {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?)
            {
                title = response?.body()?.query?.results?.channel?.title
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                var forecastStrings = mutableListOf<String>()

                if(forecasts!= null)
                {
                    for (forecast in forecasts)
                    {

                        var weatherString: String = "${forecast.date} - ${forecast.day} - High: ${forecast.high} Low:${forecast.low} - ${forecast.text}"
                        forecastStrings.add(weatherString)
                    }
                }

                var forecastList = findViewById<ListView>(R.id.listId)
                var adapter = ArrayAdapter(this@ForecastActivity, android.R.layout.simple_list_item_1, forecastStrings );
                forecastList.adapter = adapter

            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?)
            {
                println("Not Working")
            }


        }


        retriever.getForecast(callback, userValue)

    }
}

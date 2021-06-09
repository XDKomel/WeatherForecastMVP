package com.camille.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var place: TextView
    lateinit var description: TextView
    lateinit var current: TextView
    lateinit var feelsLike: TextView
    lateinit var tempLow: TextView
    lateinit var tempHigh: TextView
    lateinit var update: Button
    lateinit var seekBar: SeekBar
    val presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        place = findViewById(R.id.place)
        description = findViewById(R.id.description)
        current = findViewById(R.id.current)
        feelsLike = findViewById(R.id.feels_like)
        tempLow = findViewById(R.id.temp_low)
        tempHigh = findViewById(R.id.temp_high)
        update = findViewById(R.id.update)
        seekBar = findViewById(R.id.seekBar)

        update.setOnClickListener {
            presenter.updateModel()
            updateViews()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.updateProgress(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateViews() {
        place.text = presenter.parseCity()
        description.text = presenter.parseDescription()
        current.text = presenter.parseCurrentTemp()
        feelsLike.text = presenter.parseFeelsLikeTemp()
        tempHigh.text = presenter.parseHighTemp()
        tempLow.text = presenter.parseLowTemp()
    }
}
package com.camille.weatherforecast

import android.util.Log
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random

class WeatherForecast (current_temp: Temp = Temp(), place: Place = Place()) {
    val current_temp: Temp = current_temp
    val place: Place = place
    fun random() {
        current_temp.random()
        place.random()
    }
}

enum class Unit (val representation: String, val parseNumber: (Double) -> Double) {
    fahrenheit ("F", { Unit.kToF(it) }),
    celsium ("C", { Unit.kToC(it) }),
    kelvin ("K", { it });
    companion object {
        fun random(): Unit { return values().random() }
        fun kToC(value: Double): Double { return value-273.0 }
        fun kToF(value: Double): Double { return (value-273.0)*9/5+32 }
        fun getByOrdinal(value: Int): Unit {
            return when (value) {
                0 -> Unit.celsium
                1 -> Unit.fahrenheit
                2 -> Unit.kelvin
                else -> Unit.celsium
            }
        }
    }
}

class Temp (
    temp_curr: Double = 0.0,
    temp_low: Double = 0.0,
    temp_high: Double = 0.0,
    feels_like: Double = 0.0,
    unit: Unit = Unit.celsium,
    description: String = "no description"
) {
    var temp_curr: Double = temp_curr
    var temp_low: Double = temp_low
    var temp_high: Double = temp_high
    var feels_like: Double = feels_like
    var unit: Unit = unit
    var description: String = description
    fun random() {
        temp_curr = unit.parseNumber(randomTempInKelvin()).roundTo(1)
        temp_high = (temp_curr + randomDeviation(3)).roundTo(1)
        temp_low = (temp_curr - randomDeviation(3)).roundTo(1)
        feels_like = (temp_curr - randomDeviation(4)).roundTo(1)
        description = descriptions.random()
        Log.i("INFO", "parse from ${randomTempInKelvin()}")
    }
    fun randomTempInKelvin(): Double {
        return randomDouble(253, 306)
    }
    fun randomDeviation(till: Int): Double {
        return randomDouble(0, till)
    }
    fun randomDouble(from: Int, to: Int): Double {
        return Random.nextDouble(from.toDouble(), to.toDouble())
    }
    val descriptions = listOf("Sunny", "Foggy", "Cloudy")
}

class Place (
    name: String = "no place"
) {
    var name: String = name
    val places = listOf("Moscow", "Kazan", "Saint Petersburg", "Krasnoyarsk", "Makhachkala", "Innopolis")
    fun random() { name = places.random() }
}

fun Double.roundTo(n : Int) : Double {
    return "%.${n}f".format(this).toDouble()
}

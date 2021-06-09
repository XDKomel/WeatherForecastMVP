package com.camille.weatherforecast

class Presenter (
    weatherForecast: WeatherForecast = WeatherForecast()
) {
    val weatherForecast: WeatherForecast = weatherForecast

    fun updateModel() {
        weatherForecast.random()
    }

    fun updateProgress(progress: Int) {
        weatherForecast.current_temp.unit = Unit.getByOrdinal(progress)
    }

    fun parseCity(): String {
        return weatherForecast.place.name
    }

    fun parseTemp(value: Double): String {
        return "${value}°${weatherForecast.current_temp.unit.representation}"
    }

    fun parseDescription(): String {
        return weatherForecast.current_temp.description
    }

    fun parseCurrentTemp(): String {
        return parseTemp(weatherForecast.current_temp.temp_curr)
    }

    fun parseLowTemp(): String {
        return "Low: ${parseTemp(weatherForecast.current_temp.temp_low)}"
    }

    fun parseHighTemp(): String {
        return "High: ${parseTemp(weatherForecast.current_temp.temp_high)}"
    }

    fun parseFeelsLikeTemp(): String {
        return "Feels like ${parseTemp(weatherForecast.current_temp.feels_like)}"
    }

}
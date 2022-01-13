//eagerevaluation.kt

fun getTemperature(city: String): Double {
	println("fetch from webservice for $city")
	return 30.0
}

fun main() {
	val showTemperature = false
	val city = "Boulder"

	val temperature = getTemperature(city) //fetch from webservice
	if(showTemperature && temperature > 20)
		println("wWarm")
	else
		println("Nothing to report") //Nothing to report
}
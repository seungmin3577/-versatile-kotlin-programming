//shortcircuit.kt

fun getTemperature(city: String): Double {
	println("fetch from webservice for $city")
	return 30.0
}

fun main() {
	val showTemperature = false
	val city = "Boulder"

	if(showTemperature && getTemperature(city) > 20) //(nothing here)
		println("Warm")
	else
		println("Nothing to report") //Nothing to report
}
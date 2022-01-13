//observe.kt

import Kotlin.properties.Delegates.observable

fun main() {
	var count by observable(0) {
		property, oldValue, newValue -> println("Property: $property old: $oldValue new: $newValue")
	}

	println("The value of count is: $count") //The value of count is: 0
	count++
	println("The value of count is: $count") //The value of count is: 1
	count--
	println("The value of count is: $count") //The value of count is: 0
}
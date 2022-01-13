// veto.kt
import kotlin.properties.Delegates.vetoable

fun main() {
	var count by vetoable(0) {_, oldValue, newValue -> newValue > oldValue}

	println("The value of count is: $count") //The value of count is: 0
	count++
	println("The value of count is: $count") //The value of count is: 1
	count--
	println("The value of count is: $count") //The value of count is: 1
}

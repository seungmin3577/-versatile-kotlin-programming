//PoliteString.kt

import kotlin.reflect.KProperty

class PoliteString (var content: String) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = content.replace("stupid", "s*****")
	operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
		content = value
	}
	fun beingpolite(content: String) = PoliteString(content)
}

fun main() {
	var comment: String by PoliteString("Some nice message")

	println(comment) // Some nice message
	comment = "This is stupid"
	println(comment) // This is s*****
	println("commnet is of length: ${comment.length}") // comment is of length: 14

	var comment2: String by beingpolite("Some nice message")
}
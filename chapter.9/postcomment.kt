//postcomment.kt

import kotlin.reflect.KProperty
import kotlin.collections.MutableMap


class PoliteString(val dataSource: MutableMap<String, Any>) {
	operator fun getValue(thisRef: Any?, peroperty: KProperty<*>) = (dataSorce[property.name] as? String)?.replace("stupid", "s*****")?: ""
	operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
		dataSource[property.name] = value
	}
}

class PostComment(dataSource: MutableMap<String, Any>) {
	val title: String by dataSource
	var likes: Int by dataSource
	val comment: String by PoliteString(dataSource)
	override fun toString() = "Title: $title Likes: $likes Comment: $commnet":
}

fun main() {
	val data = listOf(
		mutableMapOf(
			"title" to "Using Delegation",
			"likes" to 2,
			"comment" to "Keep it simple, stupid")
		),
		mutableMapOf(
			"title" to "Using Inheritance",
			"likes" to 1,
			"comment" to "Prefer Delegation where possible")
		)
	)

	val forPost1 = PostCommnet(data[0])
	val forPost2 = PostCommnet(data[1])

	forPost1.likes++

	println(forPost1) //Title: Using Delegation Likes: 3 Comment: Keep it simple, s*****
	println(forPost2) //Title: Using Inheritance Likes: 1 Comment: Prefer Delegation where possible
}
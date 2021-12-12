fun main() {
    val fruits: List<String> = listOf("Apple", "Banana", "Apple",  "Banana", "Grape")
    println(fruits)
    println("first's ${fruits[0]}, that's ${fruits.get(0)}")
    println(fruits.contains("Apple"))
    println("Apple" in fruits)
    // fruits.add("Orange") // error: unresolved reference: add

    val fruits2 = fruits + "Orange"
    println(fruits)
    println(fruits2)
    val subBanana = fruits - "Banana"
    println(subBanana)

    println(fruits.javaClass)
    println(fruits::class)

    val fruits3: MutableList<String> = mutableListOf("Apple", "Bnana", "Apple",  "Banana", "Grape") 
    println(fruits3::class)
    println(fruits3.javaClass)
    println(fruits3.add("Banana")) // true
    println(fruits3)
    println(fruits3.remove("Apple")) // true
    println(fruits3)
    println(fruits3.remove("Apple2")) // false
    println(fruits3)
}
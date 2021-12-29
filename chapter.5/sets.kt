

import kotlin.collections.hashSetOf

fun main() {
    val fruits: Set<String> = setOf("Apple", "Banana", "Apple")
    println(fruits)
    // println(fruits.add("Apple"))

    val fruits2: MutableSet<String> = mutableSetOf("Apple", "Banana", "Apple")
    println(fruits2)
    println(fruits2.add("Apple"))
    println(fruits2) // false
    println(fruits2.add("Apple2"))
    println(fruits2) // true
    println(fruits2.remove("Apple2"))
    println(fruits2.size)
    println("Apple" in fruits2)
    println(fruits2.contains("Apple"))

    val numbers: HashSet<Int> = hashSetOf(1, 3, 2, 7, 4)
    println(numbers)
    numbers.add(-3)
    numbers.add(0)
    numbers.add(5)
    println(numbers)
    println(numbers.javaClass)
    println(numbers::class)

    val numbers2: LinkedHashSet<Int> = linkedSetOf(1, 3, 2, 7, 4)
    println(numbers2)
    numbers2.add(-3)
    numbers2.add(0)
    numbers2.add(5)
    println(numbers2)
    println(numbers2.javaClass)
    println(numbers2::class)

    val numbers3: MutableSet<Int> = mutableSetOf(1, 3, 2, 7, 4)
    println(numbers3)
    numbers3.add(-3)
    numbers3.add(0)
    numbers3.add(5)
    println(numbers3)
    println(numbers3.javaClass)
    println(numbers3::class)
}
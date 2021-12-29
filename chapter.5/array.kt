fun main() {
    val friends = arrayOf("Tintin", "Snowy", "Haddock", "Calculus")

    println(friends::class)
    println(friends.javaClass)
    println("${friends[0]} and ${friends[1]}")

    val numbers = arrayOf(1, 2, 3)
    println(numbers::class)
    println(numbers.javaClass)
    println(numbers.size)
    println(numbers.average())
    
    val numbers2 = intArrayOf(1, 2, 3)
    println(numbers2::class)
    println(numbers2.javaClass)

    println(Array(5) { i -> (i + 1) * (i + 1)}.sum())
}
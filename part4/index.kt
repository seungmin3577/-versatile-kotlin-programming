fun main() {
    val names = listOf("Tom", "Jerry", "Spike")
    for (index in names.indices) {
        println("Position of ${names.get(index)} is $index")
    }

    for ((index, name) in names.withIndex()) {
        println("Position of $name is $index")
    }
}
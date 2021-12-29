class Animal {
    override operator fun equals(other: Any?) = other is Animal
}

fun main() {
    val greet: Any = "Hello"
    val odie: Any = Animal()
    val toto: Any = Animal()
    println(odie == greet)  // false
    println(odie == toto)   // true
}
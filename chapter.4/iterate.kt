fun main() {
    val array = arrayOf(1, 2, 3)
    for (element in array) {print("$element, ")}

    val list = listOf(1, 2, 3)
    println(list.javaClass) // class java.util.Arrays$ArrayList
    for (element in list) {print("$element, ")}
}
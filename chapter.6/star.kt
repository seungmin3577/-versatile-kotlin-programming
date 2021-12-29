fun printValues(values: Array<*>) {
    for(value in values) {
        println(value)
    }
    //values[0] = vbalues[1] //ERROR
}

fun main() {
    printValues(arrayOf(1, 2))
}
//copyinner.kt

fun main () {
    val bananaBasket = Array<Banana>(3) {_ -> Banana()}
    val things = Array<Any>(3) {_ -> Fruit()}
    copyFromTo(bananaBasket, things) // ERROR: type mismatch
}

fun copyFromTo (from: Array<out Fruit>, to: Array<Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
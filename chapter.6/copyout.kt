fun main () {
    val bananaBasket = Array<Banana>(3) {_ -> Banana()}
    val fruitsBasket = Array<Fruit>(3) {_ -> Fruit()}
    copyFromTo(bananaBasket, fruitsBasket)
}

fun copyFromTo (from: Array<out Fruit>, to: Array<Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
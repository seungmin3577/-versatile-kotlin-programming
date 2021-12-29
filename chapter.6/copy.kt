fun main() {
    val fruitsBasket1 = Array<Fruit>(3) {_ -> Fruit()}
    val fruitsBasket2 = Array<Fruit>(3) {_ -> Fruit()}
    copyFromTo(fruitsBasket1, fruitsBasket2)


    /**
     * val fruitsBasket1 = Array<Fruit>(3) {_ -> Fruit()}
     * val fruitsBasket2 = Array<Banana>(3) {_ -> Fruit()}
     * copyFromTo(fruitsBasket1, fruitsBasket2) //ERROR: type mismatch
     */

}

fun copyFromTo(from: Array<Fruit>, to: Array<Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
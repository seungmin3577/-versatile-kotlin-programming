fun drawCircle() {
    val circle = object { // an expression
        val x = 10
        val y = 20
        val radius = 30

        fun printCalculateCircumference() {
            val answer = 2 * radius * 3.14
            println("""
            Circumference Is 2 * $radius * 3.14
            The Answer Is $answer
            """)
        }
    }

    circle.printCalculateCircumference()
}

fun main () {
    drawCircle()
}
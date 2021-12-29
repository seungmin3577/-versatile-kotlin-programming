fun createRunnable(): Runnable {
    val runnable = object: Runnable {
        override fun run() {println("You called...")}
    }

    return runnable
}

fun main () {
    createRunnable().run()

    val aRunnable = createRunnable()

    aRunnable.run()
}
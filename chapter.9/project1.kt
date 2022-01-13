// project1.kt

interface Worker {
	fun work()
	fun takeVacation()
}

open class JavaProgrammer: Worker {
	override fun work() = println("...write Java...")
	override fun takeVacation() = println("...code at the beach...")
}

class CSharpProgrammer: Worker {
	override fun work() = println("...write C#...")
	override takeVacation() = println("...branch at the ranch...")
}

// 1번 Manager
// class Manager: JavaProgrammer()

/**
 * 2번 Manager 
 * class Manger(val worker: Worker) {
 * 	fun work() worker.work()
 * 	fun takeVacation() = worker.work() // 쉬는게 쉬는게 아니다
 * }
 */

// 3번 Manager
class Manager() : Worker by JavaProgrammer()

fun main() {
	val doe = Manager()
	doe.work() //...write Java...
	val coder: JavaProgrammer = doe //Error: type mismatch
}
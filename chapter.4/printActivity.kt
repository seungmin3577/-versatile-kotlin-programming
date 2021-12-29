fun printWhatToDo(dayOfWeek: Any) {
    when (dayOfWeek) {
       "Saturday", "Sunday" -> println( "Relax")
       in listOf("Monday", "Tuesday", "Wednesday", "Thursday") -> println( "월급 루팡")
       in 2..4 -> println( "Work Hard")
       "Friday" -> println( "Party")
       is String -> println( "What?")
    }
}

fun main() {
    printWhatToDo("Sunday")
    printWhatToDo("Wednesday")
    printWhatToDo(3)
    printWhatToDo("Friday")
    printWhatToDo("Munday")
    printWhatToDo(8)
}
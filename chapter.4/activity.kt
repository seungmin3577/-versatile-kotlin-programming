fun whatToDo(dayOfWeek: Any) = when (dayOfWeek) {
    "Saturday", "Sunday" -> "Relax"
    in listOf("Monday", "Tuesday", "Wednesday", "Thursday") -> "월급 루팡"
    in 2..4 -> "Work Hard"
    "Friday" -> "Party"
    is String -> "What?"
    else -> "No clue"
}

fun main() {
    println(whatToDo("Sunday"))
    println(whatToDo("Wednesday"))
    println(whatToDo(3))
    println(whatToDo("Friday"))
    println(whatToDo("Munday"))
    println(whatToDo(8))
}
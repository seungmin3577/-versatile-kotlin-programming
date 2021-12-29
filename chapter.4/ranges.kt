fun main() {
  val oneToFive: IntRange = 1..5
  val aToE: CharRange = 'a'..'e'
  val seekHelp: ClosedRange<String> = "hello".."help"

  //number repeat
  oneToFive.forEach { print(it) }

  //string repeat
  aToE.forEach { print(it) }

  // seekHelp.forEach { println(it) }  // Wrong Syntax!
  println(seekHelp.contains("helm"))
  println(seekHelp.contains("helq"))

  for(i in 1..5) {print("$i, ")}
  for(ch in 'a'..'e'){print(ch)}
  // for(word in 'hell'..'help'){print("$word, ")} // error: too many characters in a character literal ''hell''

  for(i in 5.downTo(1)) {print("$i, ")}
  for(i in 5 downTo 1) {print("$i, ")}

}
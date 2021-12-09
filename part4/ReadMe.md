- 코틀린은 외부 반복자와 내부 반복자 모두 제공한다.
- 이장은 외부 반복의 효율성에 집중한다.
- 이번 파트는 범위를 가지는 값들과 콜렉션 객체들을 간결하고 우아하게 반복하는 기능을 배운다.

# 범위와 반복

## 레인지 클래스

```kotlin
//ranges.kt

fun main() {
  val oneToFive: IntRange = 1..5
  val aToE: CharRange = 'a'..'e'
  val seekHelp: ClosedRange<String> = "hell".."help"

  //number repeat
  oneToFive.forEach { print(it) }

  //string repeat
  aToE.forEach { print(it) }

  // seekHelp.forEach { println(it) }  // Wrong Syntax!
  println(seekHelp.contains("helm"))
  println(seekHelp.contains("helq"))

}
```

- kotlin.ranges 패키지는 Int, Long, Char같은 원시타입에 국한되지 않고 지원한다.
- String값도 가능하다. 위 예제에서 helm은 hell과 help 사이에 있는 문자이기 때문에 true를 반환 하고 helq는 범위 밖이므로 `false`를 반환하게 된다.

## 정방향 반복

```kotlin
//ranges.kt

fun main() {
  //...

  for(i in 1..5) {print("$i, ")}
	for(ch in 'a'..'e'){print(ch)}
	for(word in 'hell'..'help'){print("$word, ")} // for-loop range must hanve an 'iterator()' method
}
```

- 범위를 만들었기 때문에 for(x in ..) 문법일 이용해 반복을 수행할 수 있다.
- 명시적으로 선언하지 않았어도 변수 i는 `val`이다. 즉, 우리는 반복문 안에서 i를 변경시킬 수 없다.
- "hell" 부터 "help"까지 반복을 시도하면 for-loop range must hanve an 'iterator()' method 라는 에러가 발생한다. → 실제는 error: too many characters in a character literal ''hell''가 발생
- 이유는 IntRange나 CharRange같은 클래스들은 iterator() 함수를 갖고 있지만, 문자열의 반복은 iterator() 함수를 가지고 있지 않다.
- 하지만 방법이 없는건 아니다 12장에서 객체를 반복하는 확장 함수에 대해 알아볼 것임.

## 후방향 반복

```kotlin
//ragnes.ts

fun main() {
  // ...

  for(i in 5.downTo(1)) {print("$i, ")}
  for(i in 5 downTo 1) {print("$i, ")}
}
```

- 후방향 반복도 쉽게 할 수 있다. 5..1로 범위를 만들면 동작하지 않는다.
- downTo() 함수를 사용한다.
- 중위표기법으로 점과 괄호를 생략할 수 있다.

## 범위 안의 값 건너뛰기

```kotlin
//skipvalues.kt

fun main() {
    for(i in 1 until 5) {print("$i, ")}

    for(i in 1 until 10 step 3) {print("$i, ")}

    for(i in 10 downTo 0 step 3) {print("$i, ")}

    for(i in (1..9).filter {it % 3 == 0 || it % 5 == 0}) {
        print("$i, ")
    }

		for(i in (1..9).filter { it %3 == 0 || it % 5 == 0 }) {
        print("$i, ")
    }
}
```

- 숫자 범위를 반복할 때 마지막 값을 건너 뛰려면 `until()`을 이용해서 범위를 만들면 된다.
- `..`과는다르게 `until()`메소드는 마지막 값을 포함시키지 않는다.
- `downTo()`와 마찬가지로 중위표기법을 사용할 수 있다.
- 반복 중에 특정 값을 건너 뛰기 위해서 `step()` 메소드를 제공한다.

# 배열과 리스트의 반복

```kotlin
//iterate.kt

fun main() {
    val array = arrayOf(1, 2, 3)
    for (element in array) {print("$element, ")}

    val list = listOf(1, 2, 3)
    println(list.javaClass) // class java.util.Arrays$ArrayList
    for (element in list) {print("$element, ")}
}
```

- 배열을 만들기 위해선 `arrayOf()`함수를 사용한다.
- 리스트를 만들기 위해선 `listOf()`함수를 사용한다.

```kotlin
// index.kt

fun main() {
    val names = listOf("Tom", "Jerry", "Spike")
    for (index in names.indices) {
        println("Position of ${names.get(index)} is $index")
    }

		for ((index, name) in names.withIndex()) {
        println("Position of $name is $index")
    }
}
```

- 코틀린의 반복에서 index가 필요한 경우 `indices()`함수를 사용하면 된다.
- index와 value 값을 같이 얻고 싶다면 `withIndex()`함수를 사용하자.

# when을 사용해야 할 때

- 코틀린에는 switch문이 없다. 대신에 when이 있다.
- 표현식과 명령문 각각 다른 맛을 보여준다.

## 표현식으로서의 when

```kotlin
//boring.kt
fun isAliveIfElseFunction(alive: Boolean, numberOfLiveNeighbors: Int): Boolean {
    if(numberOfLiveNeighbors < 2) {return false}
    if(numberOfLiveNeighbors > 3) {return false}
    if(numberOfLiveNeighbors == 3) {return true}

    return alive && numberOfLiveNeighbors == 2
}

//when.kt
fun isAliveWhenFunction (alive: Boolean, numberOfLiveNeighbors: Int) = when {
    numberOfLiveNeighbors < 2 -> false
    numberOfLiveNeighbors > 3 -> false
    numberOfLiveNeighbors == 3 -> true
    else -> alive && numberOfLiveNeighbors == 2
}
```

- when은 일반적으로 if 에 비해서 간결하다.
- 코틀린 컴파일러는 else 부분이 존재하는지 표현식이 가능한 모든 입력에 대해 값을 생성하는지 **검증**한다.

```kotlin
//activity.kt

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
```

- 코틀린 컴파일러는 when에서 else가 마지막이 아닌 부분에 오는것은 허용하지 않는다.
- → 뒤에 블록을 허용해주지만 가독성 측면에서 블록을 이요하지 않는게 좋다.
- 단일 표현식이나 명령문 보다 복잡한 로직이 필요하다면 리펙토링을 통해서 함수나 메소드로 분리하고 뒤에서 해당 함수나 메소드를 호출해라.

## 명령문으로써의 when

```kotlin
//printActivity.kt

fun printWhatToDo(dayOfWeek: Any) = when (dayOfWeek) {
    "Saturday", "Sunday" -> println( "Relax")
    in listOf("Monday", "Tuesday", "Wednesday", "Thursday") -> println( "월급 루팡")
    in 2..4 -> println( "Work Hard")
    "Friday" -> println( "Party")
    is String -> println( "What?")
}

fun main() {
    printWhatToDo("Sunday")
    printWhatToDo("Wednesday")
    printWhatToDo(3)
    printWhatToDo("Friday")
    printWhatToDo("Munday")
    printWhatToDo(8)
}
```

- printWhatToDO()함수의 리턴타입은 `Unit`이다
- 코틀린에서 when이 명령문으로 사용될 때는 else가 없어도 상관없다.

## When과 변수의 스코프

```kotlin
//cores.kt

fun systemnInfo(): String {
    val numberOfCores = Runtime.getRuntime().availableProcessors()
    return when (numberOfCores) {
        1 -> "1 core, packing this onte to the museum"
        in 2..16 -> "You han $numberOfCores cores"
        else -> "numberOfCores cores!, I want your machine"
    }
}

fun reFactorSystemnInfo(): String  = when (val numberOfCores = Runtime.getRuntime().availableProcessors()) {
        1 -> "1 core, packing this onte to the museum"
        in 2..16 -> "You han $numberOfCores cores"
        else -> "numberOfCores cores!, I want your machine"
    }

fun main() {
    systemnInfo()
}
```

- systemInfo() 함수는 코어의 숫자를 리턴하는데 코ㅓ드가 약간 지저분하다.
- 코드를 다시 작성해서 노이즈를 줄여보면 numberOfCores 변수의 스코프를 제한할 수 있다.
- 바깥쪽 {} 블럭과 return키워드를 제거해 덜 복잡ㅂ한 짧은 코드를 만들 수 있다.

# 정리

- 코틀린의 특별한 클래스인 범위를 이용해서 높은 레벨의 추상화를 만들었다.
- 정방향/역방향 반복, 스킵 등을 쉽게 만들어 준다.
- when이 만들어 주는 전달인자 매칭문법이 기존의 조건문을 사용할 때 딸려오는 코드의 노이즈를 제거해 준다.

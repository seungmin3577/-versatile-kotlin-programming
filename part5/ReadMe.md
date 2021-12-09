- 자바에서 사용 가능한 콜렉션은 코틀린에서 바로 사용 가능하다.
- 추가로 콜레션에 대한 몇 가지 `뷰 인터페이스`를 제공한다.
- 코틀린의 `페어`와 `트리플`은 각각 2개, 3개의 값들을 위한 콜렉션이다.
- 코틀린은 뮤터블 콜레션 인터페이스 뷰와 이뮤터블 콜렉션 인터페이스 뷰 모드를 제공한다.
- 간단한 싱글 스레드라면 뮤터블 콜렉션 인터페이스를 사용하고 복잡한 함수형 비동기 프로그램이라면 이뮤터블 콜렉션 인터페이스를 사용하는게 안전하다.

# 콜렉션의 특징

- 자바의 뮤터블 콜레션 인터페이스는 코틀린에서 일기전용과 읽기-쓰기 인터페이스로 나뉘어 졌다.
- 콜렉션 종류
  - Pair - 값이 두개인 튜플
  - Triple - 값이 세 개인 튜플
  - Array - 객체나 원시 타입으로 구성되어 순번이 있고, 크기가 고정된 콜렉션
  - List - 객체들이 정렬된 콜렉션
  - Set - 객체들이 정렬되지 않은 콜렉션
  - Map - 연관 사전 혹은 키와 값의 맵

## 코틀린이 제공하는 편리한 메소드들

```kotlin
// extension.kt

fun main() {
    val names = listOf("Tom", "Jerry")
    println(names.javaClass)
    for((index, value) in names.withIndex()){
        println("$index $value")
    }
}
```

- JDK에서 가지고 온 `ArrayList`객체를 가지고 오고 그객체에 있는 `withIndex()` 메소드를 호출했다.
- `withIndex()` 메소드는 `IndexedValue`라는 특별한 `iterator`를 리턴한다.
- `IndexedValue`는 `data class`이다
- 코틀린에서는 `data class`의 `구조분해`를 사용해 값을 아주 쉽게 추출할 수 있다.

## 뷰

- 코틀린은 연산이 불가능하다는것을 런타임이에 알려주는게 아니라 컴파일 시 알려준다. 그래서 코틀린에 뷰가 있는 것이다.
- 리스트, 셋, 맵은 각기 읽기전용 뷰와 읽기-쓰기 전용 뷰를 두가지씩 가지고 있다.
- 예를들어 `List`, `MutableList`는 콜틀린의 `ArryayList`뷰다 하지만 List 뷰를 사용할 때 요소를 추가하거나 값을 set하려고 하면 컴파일 시점에 실패 한다.

# 페어와 트리플 사용하기

```kotlin
//airporttemperatures.kt

fun getTemperatureAtAirport(code: String): String = "${Math.round(Math.random() * 30) + code.count()}"

fun main {
    val airportCodes = listOf("LAX", "SFO", "PDX", "SEA")
    val temperatures = airportCodes.map {code -> code to getTemperatureAtAirport(code)}
    for(temp in temperatures) {
        println("Airport: ${temp.first}: Temperature: ${temp.second}")
    }
}
```

- airportCodes 콜렉션을 함수형 스타일로 반복했다.
- 반복문에서 공항 코드를 (코드, 온도)꼴의 Pair로 도치 시켰다.
- airportCodes는 List<Pair<String, String>>이 되었다.
- 페어는 2개의 값을 다룰 때 유용하다.
- 3개의 객체가 필요하다면 페어대신 트리플을 사용하면 된다.
- 페어와 트리플 모두 이뮤터블 이다.

# 객체 배열과 원시 배열

```kotlin
//array.kt

fun main() {
    val friends = arrayOf("Tintin", "Snowy", "Haddock", "Calculus")

    println(friends::class) // class kotlin.Array
    println(friends.javaClass) // class [Ljava.lang.String;
    println("${friends[0]} and ${friends[1]}")

		val numbers = arrayOf(1, 2, 3)
    println(numbers::class) // class kotlin.Array
    println(numbers.javaClass) // class [Ljava.lang.Integer;
}
```

- Array<T> 클래스는 코틀린의 배열을 상징한다.
- 배열은 **낮은 수준의 최적화(?)**가 필요할 때만 사용한다. 그외에는 List같은 다른 자료구조를 사용하자.
- 배열을 만들면 인덱스 연산자 []를 이요해 요소에 접근할 수 있다.
- friends 변수는 배열 인스턴스의 참조(주소값)를 가지고 있다.
- numbers 배열의 자바 Class를 보면 Integer 배열로 정의 된다. 이렇게 되면 int 배열에 비해서 **오버헤드(?)**가 크게 걸린다.

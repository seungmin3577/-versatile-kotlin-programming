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

		val numbers2 = intArrayOf(1, 2, 3)
		println(numbers2::class)// class kotlin.Array
    println(numbers2.javaClass) // class [I
}
```

- `Array<T>` 클래스는 코틀린의 배열을 상징한다.
- 배열은 **낮은 수준의 최적화(?)**가 필요할 때만 사용한다. 그외에는 List같은 다른 자료구조를 사용하자.
- 배열을 만들면 인덱스 연산자 []를 이요해 요소에 접근할 수 있다.
- friends 변수는 배열 인스턴스의 참조(주소값)를 가지고 있다.
- numbers 배열의 자바 Class를 보면 Integer 배열로 정의 된다. 이렇게 되면 int 배열에 비해서 **오버헤드(?)**가 크게 걸린다.
- Class로 박싱되면서 발생하는 오버헤드를 피하기 위해서 만들어진 intArraytOf() 함수 같은 특수 함수들이 있다.
- Array 객체의 생성자는 파라미터로 배열의 사이즈와 () 부터 시작하는 인덱스를 받아 해당 위치에 있는 값을 리턴해 주는 함수를 받는다.
- 만약에 정렬된, 길이가 바뀔 수 있는 콜렉션을 원한다면 배열보다는 리스트 사용을 고려해야한다. 배열은 뮤터블한 반면, 리스트는 뮤터블/이뮤터블 모두 제공하기 때문에 원하는 대로 사용이 가능하다.

<aside>
💡 주한님 Question: 배열과 List가 다른점은 무엇인가요?

</aside>

### Array와 List의 차이점?

- 배열
  - 같은 자료형을 가진 값들을 하나로 나타낸 것
  - 초기화와 동시에 `크기가 정해짐`
  - `메모리 공간에 연속적`으로 저장 됌
  - 인덱스를 통해 값에 접근한다.
  - 정적 타입
- 리스트
  - 순서가 있는 엘리먼트의 집합, 시퀀스라고도 부름
  - `불연속적인 메모리` 공간을 점유, 메모리 관리가 용이하다.
  - 동적 타입
  - 포인터를 통해 값에 접근
  - 인덱스는 몇 번째 데이터인가 정도의 의미를 갖음

장단점

- 배열에서는 값을 삭제하더라도 크기가 줄어들지 않아 메모리가 낭비된다.
- 연속된 메모리 공간을 사용해서 메모리 관리가 편하고 검색성능이 좋다.
- 리스트는 포인터를 통해 다음값을 가르키므로 배열 대비 추가적인 메모리 공간이 필요하다.

# List 사용하기

```kotlin
//lists.kt
fun main() {
    val fruits: List<String> = listOf("Apple", "Banana", "Apple",  "Banana", "Grape")
    println(fruits)
    println("first's ${fruits[0]}, that's ${fruits.get(0)}")
    println(fruits.contains("Apple"))
    println("Apple" in fruits)
    // fruits.add("Orange") // error: unresolved reference: add

    val fruits2 = fruits + "Orange"
    println(fruits)
    println(fruits2)
    val subBanana = fruits - "Banana"
    println(subBanana)

    println(fruits.javaClass)
    println(fruits::class)

    val fruits3: MutableList<String> = mutableListOf("Apple", "Bnana", "Apple",  "Banana", "Grape")
    println(fruits3::class)
    println(fruits3.javaClass)
    println(fruits3.add("Banana")) // true
    println(fruits3)
    println(fruits3.remove("Apple")) // true
    println(fruits3)
    println(fruits3.remove("Apple2")) // false
    println(fruits3)
}
```

- 리스트를 만들려면 이뮤터블/뮤터블 (listOf()/mutableListOf) 인지 선택 해야한다.
- 이뮤터블을 선호하는걸 권장한다.
- 리스트의 내장 메소드
  - 접근하기 위해서는 전톡적인 get()을 사용할 수 있다 그리고 인덱스 연산자 [] 역시 사용 가능하다. ( 노이즈가 적고 편리한 인덱스 연산자를 사용하는 것을 권장한다.)
  - 콜렉션에 값이 있는지 없는지 확인하기 위해서 contains()를 사용하거나 in 연산자를 사용할 수 있다. (표현력이 좋고 직관성이 있는 in을 사용하는 것을 권장한다.)
- "+" 연산자로 리스트에 해당 값을 추가된 리스트를 반환해 만들어 줄 수 있다.
- "-" 연산자로 리스트에 해당 값을 뺀 리스트를 반환해 만들어 줄 수 있다.
  - 여러개 있다고 하더라도 처음에 서칭된 값 하나만 빼진다.
  - 값이 없다면 없는대로 반환 된다.
- listOf() 메소드는 읽기 전용 참조를 리턴해 준다.

# Set 사용하기

```kotlin
// sets.kt
import kotlin.collections.hashSetOf

fun main() {
    val fruits: Set<String> = setOf("Apple", "Banana", "Apple")
    println(fruits)
    // println(fruits.add("Apple"))

    val fruits2: MutableSet<String> = mutableSetOf("Apple", "Banana", "Apple")
    println(fruits2)
    println(fruits2.add("Apple"))
    println(fruits2) // false
    println(fruits2.add("Apple2"))
    println(fruits2) // true
    println(fruits2.remove("Apple2"))
    println(fruits2.size)
    println("Apple" in fruits2)
    println(fruits2.contains("Apple"))

    val numbers: HashSet<Int> = hashSetOf(1, 3, 2, 7, 4)
    println(numbers)
    numbers.add(-3)
    numbers.add(0)
    numbers.add(5)
    println(numbers)
    println(numbers.javaClass)
    println(numbers::class)

    val numbers2: LinkedHashSet<Int> = linkedSetOf(1, 3, 2, 7, 4)
    println(numbers2)
    numbers2.add(-3)
    numbers2.add(0)
    numbers2.add(5)
    println(numbers2)
    println(numbers2.javaClass)
    println(numbers2::class)

    val numbers3: MutableSet<Int> = mutableSetOf(1, 3, 2, 7, 4)
    println(numbers3)
    numbers3.add(-3)
    numbers3.add(0)
    numbers3.add(5)
    println(numbers3)
    println(numbers3.javaClass)
    println(numbers3::class)
}
```

- Set은 정렬되지 않은 요소의 모음이다.
- List처럼 이뷰터블/뮤터블 버전 모두 있다. (setOf/mutableSet)
- hashSetOf() 메소드를 이용해서 java.util.HashSet의 참조를 만들 수도 있다.
- LinkedHashSet을 만들려면 linkedSetOf()를 사용한다.
- TreeSet을 만들려면 sortedSetOf()를 이용한다.
- List처럼 Set과 MutableSet에는 +, -, contains, in 등 많은 함수들이 있다.
- hashSetOf vs linkedSetOf의 차이점은 순서 보장의 차이가 있다. HashSetOf는 add()시 들어가는 순서가 보장되지 않는 반면 hashLinkedSet은 add()시 들어가는 순서가 보장이 된다.
- set은 linkedSetOf와 같다.

# Map 사용하기

```kotlin
// usingmap.kt
fun main() {
    val sites = mapOf("Progprog" to "https://www.pragprog.com", "agiledeveloper" to "https://agiledeveloper.com")

    println(sites.size) //2
    println(sites.containsKey("agiledeveloper"))
    println(sites.containsValue("http://www.example.com"))
    println(sites.contains(("agiledeveloper")))
    println("agiledeveloper" in sites)

    // val pragProgSite: String = sites.get("pragprog") // error: type mismatch: inferred type is String? but String was expected
    val pragProgSite: String? = sites.get("pragprog")
    val pragProgSite2: String? = sites["pragprog"]

    val agiledeveloper = sites.getOrDefault("agiledeveloper", "http://example.com")

    val sitesWithExample = sites + ("example" to "http://www.example.com")
    val withoutAgileDeveloper = sites - "agiledeveloper"

    for( entry in sites) {
        println("${entry.key} --- ${entry.value}" )
    }

    for((key, value) in sites) {
        println("$key --- $value")
    }

}

```

- Map은 키-값 페어를 보관하는 콜렉션이다.
- List처럼 이뮤터블/뮤터블 두가지 인터페이스를 제공한다. (mapOf/mutableMap)
- JDK의 HashMap의 참조를 얻기 위해선 hashMapOf()를 사용할 수 있다.
- LinkedHashMap을 얻기 위해선 linkedMapOf()를 사용 할 수 잇다.
- SortedMap을 얻기 위해선 sortedMapOf()를 사용한다.
- 맵의 keys 속성을 이용해서 맵에 존재하는 모든키를 반복할 수 있다.
- values를 이용하면 맵에 존재하는 모든 값을 반복할 수 있다.
- contains() 메소드 또는 containsKey() 또는 in 연산자를 이용해서 키가 존재하는지 확인할 수 있다.
- containsValue() 메소드를 통해 값이 존재하는지 확인 할 수 있다.
- get()메소드 또는 인덱스 연산자 []는 키가 맵에 없을 경우 nullable 타입을 리턴한다.
- getOrDefault(key, default)를 통해 키가 없을 시 기본값을 리턴할 수 있다.
- mapOf()함수는 읽기전용 참조만 전달해 줘서 맵을 변경할 수 없지만 키-값 Pair를 +연산자로 추가해서 새로운 맵을 만들 수 있다.
- 비슷하게 -연산자를 이요해 특정 키-값을 제거한 새로운 맵을 만들수 있다.
- 맵을 반복하기 위해서 for 루프를 사용할 수 있다. 구조분해도 가능하다.
- 맵 인터페이스는 2개의 특별한 메소드를 가지고 있다. getValue()와 setValue()이다. 두 메소드는 맵을 대리자로 사용 가능하게 해주는 메소드이다.

# 정리

- 코틀린은 자바의 콜렉션을 확장하는 동시에 읽기전용 뷰를 통해서 컴파일 시간의 안정성을 향상시켰다.
- 함수형 코드, 동시성 코드, 비동기 프로그램을 만들 때는 읽기전용 뷰를 사용해야 한다.
- 페어와 트리플을 한정된 작은 크기의 콜렉션을 만들기에 유용하다.
- 크기가 크고, 고정된 크기의 콜렉션을 만들 때는 Array클래스를 사용하는 것이 좋다.
- 크키가 동적으로 변경되는 콜렉션이라면 리스트와 셋 중에서 골라서 사용하면 된다.

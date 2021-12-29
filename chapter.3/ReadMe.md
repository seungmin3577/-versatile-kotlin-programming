# Chapter3. 함수를 사용하자

- 코틀린은 무조건 클래스를 사용하라고 강요하지 않는다.
- 코드 재사용이 무조건 클래스 계층화를 의미하는 건 아니다.
- 코틀린은 구조분해(destructuring) 기능을 가지고 있다.

# 함수 생성

```kotlin
//kiss.kts

fun greet() = "hello"
println(greet())

val message: Int = greet() // Error
//type mismatch: inferred type is String but Int was expected

fun goodGreet(): String = "Hello"
```

- 함수의 정의는 `fun Keyword`
- 짧은 단일표현함수 사용 시 바디를 {}로 만드는 대신 함수 정의 부분과 함수 바디를 = 로 구분할 수 있다. `return 키워드는 사용 불가`
- 단일표현함수에 타입을 집어 넣지 않더라도 추론이 가능하기때문에 넣을 필요가 없다.
- 함수가 외부에서 사용되거나 복잡하다면 리턴타입을 지정해주는게 좋다.

## 모든 함수는 표현식이다

- 코틀린은 명령문 보다는 표현식을 좋아한다.
- 함수는 명령문보다는 표현식으로 취급되어야 한다.
- 코틀린은 `Unit`이라는 특별한 타입을 사용. Java의 `void`와 대응된다.

```kotlin
//inferunit.kts

fun sayHello() = println("Well, hello")
val message: String = sayHello() //ERROR
//type mismatch: inferred type is Unit but String was expected

fun goodSayHello(): Unit = println("Well, hello")
val message: Unit = sayHello()
println("The result of sayHello is $message")
```

- `Unit` 타입은 `toString()`, `equals()`, `hashCode()`메소드를 가지고 있다.

## 파라미터 정의하기

- 코틀린은 함수나 메소드에 파라미터의 타입을 명시하도록 했다.

  ```kotlin
  // passingarguments.kts

  fun greet(name: String): String = "Hello $name"
  println(greet("Eve")) //Hello Eve
  ```

- 파라미터를 변경하려하면 컴파일 오류를 발생 시킨다.

## 블록바디로 만든 함수

- 함수가 복잡하다면 우리는 {} 블록을 사용해서 바디를 만든다.
- 블록바디를 이용하면 항상 리턴 타입을 정해줘야 한다. 정의하지 않는다면 리턴타입은 `Unit으로 추론`된다.
- 리턴타입을 생략하고 = 을 사용해 단일 표현식 대신 블록 바디를 사용하면 `익명함수(anonymous function)으로 취급`
  ```kotlin
  fun notReally() = {2}
  ```
- =을 블록과 함께 사용하고 리턴타입을 명시하지 않은 경우

  ```kotlin
  //caveat.kts

  fun f1() = 2
  fun f2() = {2}
  fun f3(factor: Int) = {n: Int -> n * factor}
  println(f1())      // 2
  println(f2())      // () -> kotlin.Int
  println(f2()())    // 2
  println(f3(2))     // (kotlin.Int) -> kotlin>Int
  println(f3(2)(3))  // 6
  ```

# 기본 인자와 명시적 인자

- 코틀린도 자바와 마찬가지로 오버로딩이 가능하다.
- 기본 아규먼트(Named argument)는 읽기 좋은 코드를 만드는 아주 유용한 방법.

## 기본 아규먼트를 통한 함수 변경

- 기본 아규먼트는 기본값을 가지는 파라미터다.

  ```kotlin
  //defaultarguments.kts

  fun greet(name: String, msg: String = "Hello"): String = "$msg $name"
  println(greet("Eve")) // Hello Eve
  println(greet("Eve", "Howdy")) // Howdy Eve
  ```

- 일반 파라미터는 값이 항상 필요하기 때문에 호출하는 쪽에서는 기본 아규먼트에도 값을 무조건 넣어줘야한다.
- 함수에 명시적 아규먼트를 사용한다면 호출하는 쪽에서 생략할 수 있다.
- 기본 아규먼트를 효과적으로 만들기 위해서는 맨 마지막에 사용하는 것이 좋고, 람다표현식이 파라미터로 들어오는 경우는 람다표현식 앞에서 사용하면 된다.
- 기본 아규먼트는 값일 필요가 없고 표현식이 올 수도 있다.

  ```kotlin
  //defaultcompute.kts

  fun greet(name: String, msg: String = "Hi ${name.length}") = "msg $name"
  ```

## 명시적 아규먼트(Named Argument)를 이용한 가독성 향상

- 잘못 짠 코드는 성인구자도 폭군으로 만든다 고로 가독성은 매우 중요하다.
- 명시적 아규먼트는 가독성에 좋다.
  ```kotlin
  namedarguments.kts
  createPerson(name = "Jake", age = 12, weight = 43, height = 152)
  ```

# 다중인자와 스프레드

- 다중인자 기능은 함수가 한 번에 여러 개의 인자를 받을 때 타입 안정성을 제공해주는 기능이다.

## 여러 개의 인자

- 코틀린 함수들은 많은 수의 인자를 받을 수 있다.

  ```kotlin
  //vararg.kts

  fun max(vararg numbers: Int): Int {
  	var large = Int.MIN_VALUE}
  	for(number in numbers) {
  		large = if(number > large) nubmer else large
  	}
  	return large
  }

  println(max(1, 5, 2)) // 5
  println(max(1, 5, 2, 12, 7, 3)) // 12
  ```

- vararg 키워드는 함수에서 하나의 파라미터에서만 사용할 수 있다.
- 파라미터가 여러개 일 경우 vararg 파라미터를 마지막에 넣는것을 강력하게 추천한다.
- vararg를 마지막에 사용하지 않는다면, 함수를 호출할 때 반드시 명시적 인자를 사용해야 한다.
- 권장사항
  - vararg는 마지막에 두어서 호출 시 명시적 인자를 필수적으로 사용할 필요가 없도록 만들자.
  - 마지막 파라미터가 람다 표현식일 경우 마지막 바로 전에 둔다. 이에 대해서는 후에 다룬다.

## 스프레드 연산자

- 함수가 다중 인자를 받을수 있도록 정의되어 있을때 배열이나 리스트를 직접 받을 수 없다. 이럴 때 스프레드 연산자가 필요하다.

  ```kotlin
  // vararg.kts

  val values = intArrayOf(1, 21, 3)

  println(max(values)) //ERROR
  //type mismatch: inferred type is IntArray but Int was expected

  println(max(values[0]), values[1], values[2]) //장황하고 부끄러움
  println(max(*values)) //21
  ```

- 배열이 있으면 스프레드를 사용할 수 있지만 보통 배열보다 리스트를 많이 사용한다.
- 리스트에 직접 스프레드 연산자는 적용할 수 없다.
- 리스트를 배열로 변환 후 적용가능하다.

  ```kotlin
  //vararg.kts

  println(max(*listOf(1, 4, 18, 12).toIntArray())) // 18
  ```

# 구조분해

- 객체에서 값을 추출해 변수로 넣는 작업이다. 코틀린의 구조분해 기능은 Javascript와 유사하다.

  ```kotlin
  //destructuring.kts

  /** Triple은 튜플을 구현하는 코틀린 스탠다드 라이브러리 */
  fun getFullName() = Triple("J", "Q", "A")

  val result = getFullName()
  val first = result.first
  val middle = result.second
  val last = result.third
  println("$first $middle $last") //J Q A

  val (first, _, last) = getFullName()
  println("$first $last") //J A
  ```

- \_ 언더스코어를 쓰면 생략 가능하다.

# 정리

- 코틀린은 메소드를 만들도록 강요하지 않는다.
- 최상위 함수를 만들 수 있다.
- 컴파일러는 단일 표현식이면서 블록이 없느 함수의 경우 리턴타입을 추론해준다.
- 파라미터를 정의할 땐 항상 타입이 필요하다.
- 기본인자 기능은 함수를 확장하기 쉽게 해준다.
- vararg는 타입 안정성을 제공하면서 여러개의 인자를 넘기는 것을 아주 유연하게 해준다.
- 스프레드 연산자는 vararg 파라미터에 배열을 넘기는 것을 쉽게 만들어 준다.
- 명시적 인자를 사용하는 것은 코드의 가독성을 높여준다.
- 명시적 인자를 사용한다면 코드 자체가 문서화 된다.
- 구조분해는 코드의 방해요소를 줄여주고, 코드를 간결하게 만들어 준다.

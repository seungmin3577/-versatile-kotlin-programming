# Chapter6.오류를 예방하는 타입 안정성

- 코틀린의 모든 클래스는 Java의 Object 클래스처럼 Any 클래스에서 상속을 받는다.
- 제네릭 파라미터 타입의 공변성?, 반공변성?
- 레이파이드(reified) ?
- Any와 Nothing 클래스를 배우고 null 가능 참조와 연관된 연산자, 스마트 캐스트의 장점을 배운다.

# Any와 Nothing 클래스

- 코틀린의 Any클래스는 Java의 Object에 대응되는 클래스이다.
- Nothing은 함수가 아무것도 리턴하지 않을 경우 리턴하는 클래스이다.

## 베이스 클래스 Any

- 코틀린의 모든 클래스는 Any를 상속받았다.
- Any 클래스는 최대한 유연성을 제공한다. 그러니 아주 제한적으로 사용해야만 한다.
- Any의 목적은 변수, 파라미터, 리턴타입을 Any로 정의하도록 하는게 아니고 공통으로 적용되는 메소드를 만들기 위해 존재한다.
  - equals(), hashCode(), toString()같은 함수들
  - let(), run(), apply(), also()같은 확장함수를 갖고 잇음
- Any가 Java 바이트코드에서 Object에 매칭되지만, Any 와 object가 동일일한 것은 아님

### Nothing은 void보다 강력하다.

- Nothing을 함수의 리턴타입으로 사용한다면 절대로 리턴을 하지 않는다는 이야기

```kotlin
fun computeSqurt(n: Double): Doble {
	if(n >= 0) {
		return Math.sqrt(n)
	} else {
		throw RuntimeException("No negativve please")
	}
}
```

- 예외는 Nothing타입을 대표한다.
- Nothing의 유일한 목적은 컴파일러가 프로그램의 타입 무결성을 검증하도록 도와주는 것.

# Null가능 참조

## null은 에러를 유발한다

- 콜렉션을 리턴하는 함수가 실행 시간에 리턴할 게 아무것도 없다면 null이 아니고 빈 콜렉션을 리턴해야 한다
  - null을 리턴한다면 결과를 받는 쪽에서 null로인한 오류가 발생할 수 있고, 이를 피하려면 반드시 null check를 해야하기 때문이다.
- 코틀린은 리턴타입이 String일 경우 null을 리턴하지 못하게 한다.
- 일반적으로 null과 nullable타입은 절대 사용하지 않는편이 좋다.
  - 사용을 할 수 밖에 없는 상황이라면 반드시 NullPointerException이 발생하지 않게 할 수 있도록 조취를 취해야한다.

## Null 가능 타입 사용하기

- null 불가 타입들은 각자 대응하는 null 가능 타입이 존재한다.
- null가능 타입은 타입 이름 뒤에 ?가 붙는다.
- null 가능타입은 컴파일 시 null 불가 타입으로 대체 된다.

## 세이프 콜 연산자

- ?연산자를 이용하면 메소드 호출 또는 객체 속성 접근과 null 체크를 하나로 합칠 수 있다.
- ?연산자를 세이프 콜 연산자라고 한다.
- if 체크를 name?.reversed()?.toUpperCase() 이런식으로 할 수 있다.

## 엘비스 연산자 (Elvis Operator)

- ?: 연산자를 엘비스 연산자라고 한다.
- null 일 경우 값을 초기화 시켜줌
- name?.reversed()?.toUpperCase() ?: "Joker" 이런식으로 사용 가능

## 사용해서는 안될 안전하지 않은 확정 연산자!!

- !! 연산자는 not-null 확정 연산자 이다.
- 특정 참조가 절대 null이 아니란 사실을 알고 있다면 쓸모없는 체크는 할 필요가 없다고 전달하고 String?에서도 String의 메소드와 속성을 사용할 수 있다.
- name!!.reversed().toUpperCase() 로 사용 가능하다 // Not Recommend

## When의 사용

- null 가능 참조로 작업을 할 때 참조의 값에 따라서 다르게 동작하거나 다른 행동을 취해야 한다면 ?.이나 ?: 보다는 when을 사용하는 것을 고려해 보자.
- 세이프 콜이나 엘비스 연산자는 값을 추출 해낼 때 사용하고 when 은 null 가능 참조에 대한 처리를 결정해야 할 때 사용하자.

# 타입 체크와 캐스팅

## 타입 체크

- 기능이냐, 결함이냐라는 것은 Run Time 타입 체크에 대한 끝없는 논쟁이다.
- 임의의 타입을 체크하는 행위는 새로운 타입이 추가 됐을 때 코드를 부서지기 쉽게 만들고 개방-폐쇄 원칙에도 위배된다.
- equals() 함수와 인스턴스의 타입에 기반해서 분기가 이루어지는 when을 사용할 때에는 타입체크를 하는건 아주 유용하다.

## is 사용하기

- Object의 equals()메소드는 참조 기반 비교이다.

```kotlin
// equals.kt
class Animal {
    override operator fun equals(other: Any?) = other is Animal
}

fun main() {
    val greet: Any = "Hello"
    val odie: Any = Animal()
    val toto: Any = Animal()
    println(odie == greet)  // false
    println(odie == toto)   // true
}
```

- is 연산자는 객체가 참조로 특정 타입을 가리키는지 확인한다.
- 위 코드는 other가 Animal 클래스인지 확인한다.
- is 연산자는 모든 타입의 참조에 사용될 수 있다.
- is 연산자를 ‘부정’과 함께 사용할 수 도 있다 ex) !is

### 스마트 캐스트

```kotlin
// smartcast.kt
class Animal(val age: Int) {
    override operator fun equals(other: Any?): Boolean {
        return if (other is Animal) age  == other.age else false
        // return other is Animal && age == other.age <- refactor
    }
}

fun main () {
    val odie = Animal(2)
    val toto = Animal(2)
    val butch = Animal(3)
    println(odie == toto) // true
    println(odie == butch) // false
}
```

- equals() 함수안에서 우리는 캐스트 없이 other.age라고 바로 사용할 수 있다.
- is연산자로 체크를 했기때문에 캐스트를 할 필요가 없다.
- 코틀린 객체는 null 참조가 아니라고 판별하면 null 가능 타입이 null 불가 타입으로 자동으로 캐스팅 된다.

## when과 함께 타입 체크와 스마트 캐스트 사용하기

- when 명령문 또는 표현식에 is 나 !is, 스마트 캐스팅을 사용할 수 있다.

## 명시적 타입 캐스팅

- 명시적 타입캐스팅은 컴파일러가 타입을 확실하게 결정할 수 없어 스마트 캐스팅을 하지 못할 경우우에만 사용해야 한다.
- 코틀린은 명시적 타입 캐스트를 위해서 as와 as? 2가지 연산자를 제공한다.

```kotlin
// unsafecast.kt
fun main () {
    for (id in 1..2) {
        /**
         * println("Message length: ${(fetchMessage(id) as String).length}")
         *
         * Exception in thread "main" java.lang.ClassCastException: class java.lang.StringBuilder cannot be cast to class java.lang.String (java.lang.StringBuilder and java.lang.String are in module java.base of loader 'bootstrap')
		     *  at UnsafecastKt.main(unsafecast.kt:3)
		     *  at UnsafecastKt.main(unsafecast.kt)
         */

        println("Message length: ${(fetchMessage(id) as? String)?.length ?: "---}") //Safe!
    }
}

fun fetchMessage(id: Int): Any = if(id == 1) "Record found" else StringBuilder("data not found")
```

- as 연산자는 캐스팅에 실패하면 죽는 반면 as?연산자는 null을 할당한다.
- 실무 권장사항
  - 가능한 스마트 캐스트를 사용해라
  - 스마트 캐스트가 불가능한 경우에만 안전한 캐스트 연산자를 사용해라
  - 어플리케이션이 불타거나 무너지는걸 보고 싶다면 안전하지 않은 캐스트 연산자를 사용해라

## 제네릭: 파라미터 타입의 가변성과 제약사항

```kotlin
//copy.kt

fun main() {
    val fruitsBasket1 = Array<Fruit>(3) {_ -> Fruit()}
    val fruitsBasket2 = Array<Fruit>(3) {_ -> Fruit()}
    copyFromTo(fruitsBasket1, fruitsBasket2)

    /**
     * val fruitsBasket1 = Array<Fruit>(3) {_ -> Fruit()}
     * val fruitsBasket2 = Array<Banana>(3) {_ -> Fruit()}
     * copyFromTo(fruitsBasket1, fruitsBasket2) //ERROR: type mismatch
     */

}

fun copyFromTo(from: Array<Fruit>, to: Array<Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
```

- 코틀린은 Array<Fruit>자리에 Array<Banana>를 전달하지 못하도록 막는다.
- 코틀린에서 from 파라미터는 파라미터의 값을 읽기만 하기 때문에 Fruit이나 Fruit의 하위 클래스가 전달되더라도 아무런 위험이 없다. 이런것을 타입이나 파생 타입에 접근하기 위한 파라미터 타입의 공변성이라고 이야기 한다.
- 아래 코드가 copyFromTo()안에 있다면 컴파일이 실패할 것이다.
  ```kotlin
  from[i] = Fruit() // ERROR
  from.set(i, to[i]) // ERROR
  ```
- from에서는 읽기만 하고 to에 값을 설정하는 경우에만 from 파라미터 위치에 Array<Banana>, Array<Orange>, Array<Fruit>을 전달할 수 있다.
- Array<T> 클래스는 T타입의 객체를 읽고, 쓰는 메소드 모두를 가지고 있다.
  - Array<T>를 사용하는 모든 함수는 읽고, 쓰는 두타입의 메소드를 사용할 수 있다.
  - 공변성을 사용하기 위해 어떤 값도 추가하거나 변경하지 않겠다는 약속을 해야 한다.
  - 이런 제네릭 클래스를 사용하는 관점에서 공변성을 이용하는 걸 `사용처 가변성(use-site variance)` 혹은 `타입 프로젝션` 이라고 부른다.
- 사용처 가변성은 클래스를 공변성으로 사용하려 할 때 유용하다.
- 제네릭 타입을 사용할 때가 아니라 선언할 때 공변성을 사용한다고 지정하는 것을 `선언처 가변성 (declation-site variance)` 라고 부른다
  - 선언처 가변성의 좋은 예제는 List<out T> 로 되어있는 List 인터페이스의 정의에서 찾아볼 수 있다.
  - 선언 가변성은 적용된 파라미터에만 사용 가변성과 같이 작용한다
- 공변성을 사용하면 컴파일러에게 자식 클래스를 부모클래스의 자리에 사용할 수 있게 요청할 수 있다.

## 반공변성 사용하기

```kotlin
//copyinner.kt

fun main () {
    val bananaBasket = Array<Banana>(3) {_ -> Banana()}
    val things = Array<Any>(3) {_ -> Fruit()}
    copyFromTo(bananaBasket, things) // ERROR: type mismatch
}

fun copyFromTo (from: Array<out Fruit>, to: Array<Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
```

- to 파라미터에 Array<Fruit>을 전달하면 아무런 문제도 없다.

```kotlin
//copyin.kt

fun main () {
    val bananaBasket = Array<Banana>(3) {_ -> Banana()}
    val things = Array<Any>(3) {_ -> Fruit()}
    copyFromTo(bananaBasket, things) // OK
}

fun copyFromTo (from: Array<out Fruit>, to: Array<in Fruit>) {
    for (i in 0 untill from.size) {
        to[i] = from[i]
    }
}
```

- in 키워드는 메소드가 파라미터에 값을 설정할 수 있게 만들고, 값을 읽을 수 없게 만든다.
- <in T>로 정의되면 전체적으로 파라미터 타입을 받을 수만 있고 리턴하거나 다른곳으로 보낼 수는 없는 반공변성으로 특정된다.
- `제네릭 함수와 클래스를 디자인하는 것은 쉬운작업이 아니고 타입, 변수, 결과에 대해서 충분한 시간을 들여서 생각을 해야 한다.`

## Where를 사용한 파라미터 타입 제한

```kotlin
// closeerr.kt

fun <T> useAndClose(input: T) {
    input.close() //ERROR: unresolved reference: close
}

fun main() {

}
```

- 위 예제 코드의 타입 T는 close() 메소드를 서포트해야 한다.
- 하지만 어떤 타입은 close() 메소드가 없다 그래서 호출에 실패한다.

```kotlin
//closable.kt

import java.io.StringWriter

//AutoCloseable is interface
fun <T: AutoCloseable> useAndClose(input: T) {
    input.close() //Ok
}

fun main() {
    val writer = java.io.StringWriter()
    writer.append("hello ")
    useAndClose(writer)
    println(writer) // hello
}
```

- useAndClose()함수는 T를 파라미터 타입으로 사용하지만 모든 타입이 되는게 아니라 AutoCloseable 인터페이스를 구현한 클래스만이 가능하다.
- 하나의 제약조건을 넣기 위해서 파라미터 타입 뒤에 콜론을 넣은 후 제약조건을 정의하면 된다.
- 여러개의 제약조건은 where를 사용해야 한다.

```kotlin
//where.kt

import java.io.StringWriter

fun <T> useAndClose(input: T)
where
    T: AutoCloseable,
    T: Appendable
{
    input.append("there")
    input.close()
}

fun main() {
    val writer = java.io.StringWriter()
    writer.append("hello ")
    useAndClose(writer)
    println(writer) // hello there
}
```

- AutoCloseable과 Appendable을 모두 구현한 StringWriter의 인스턴스를 넘겨줬기 때문에 가능
- StringWriter 뿐아니라 두 제약조건을 만족하는 클래스의 인스턴스라면 어떤 인스턴스도 전달할 수 있다.

## 스타 프로젝션

- 코틀린의 제네릭과 Java의 제네릭의 차이점
  - 선언처 가변성
  - 파라미터 타입을 정의하는 스타 프로젝션<\*>은 제네릭 읽기전용 타입과 raw타입을 위한 코틀린의 기능

```kotlin
//start.kt

fun printValues(values: Array<*>) {
    for(value in values) {
        println(value)
    }
    //values[0] = vbalues[1] //ERROR
}

fun main() {
    printValues(arrayOf(1, 2)) //1\n2
}
```

- 스타 프로젝션은 타입에 대해 정확히는 알 수 없지만 타입안정성을 유지하면서 파라미터를 전달할 때 사용된다.
- 스타 프로젝션은 읽는 것만 허용하고 쓰는 것은 허용하지 않는다.
- 함수는 Array<\*>을 파라미터로 받는다. 그리고 함수 내에서 어떠한 변경도 허용되지 않는다.
- 여기서 사용된 스타 프로젝션<\*>은 out T와 동일하지만 더 간결하게 작성할 수 있다.

## 구체화된 타입 파라미터

```kotlin
//reifiedtype.kt

abstract class Book(val name: String)
class Fiction(name: String): Book(name)
class NonFiction(name: String): Book(name)

inline fun <T>  legacyFindFirst(books: List<Book>, ofClass: Class<T>) :T {
    val selected = books.filter {book -> book is T}
    if(selected.size == 0) {
        throw RuntimeException("Not found")
    }
    return ofClass.cast(selected[0])
}

inline fun <reified T> findFirst(books: List<Book>): T {
    val selected = books.filter {book -> book is T}
    if(selected.size == 0) {
        throw RuntimeException("Not found")
    }
    return selected[0] as T
}

fun main () {
    val books: List<Book> = listOf(Fiction("Moby Dick"), NonFiction("Learn to Code"), Fiction("LOTR"))

}
```

- 구체화된 타입 파라미터는 `클러터`를 지우는데 유용하다
- 구체화된 타입 파라미터는 잠재적인 코드의 오류를 제거하는 데에도 도움이 된다.
- Reified 타입 파라미터는 함수에 추가적인 클래스 정보를 전달하지 않도록 만들어 준다.
- 코드에서 캐스팅을 안전하게 하는 데 도움을 주고 컴파일 시간 안정성을 확보한 채로 리턴타입을 커스터 마이징할 수 있게 해준다.
- 대표적을으로 listOf<T>와 mutableListOf<T>() 함수, parse<T>등이 있다.

# Deep하게 더 볼것

---

- inline function
- reified
- 공변성, 반공변성

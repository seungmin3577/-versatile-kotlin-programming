# 델리게이션을 통한 확장

- 상속과 델리게이션 모두 객체지향 프로그래밍의 디자인 방식
- 상속은 강력하고 자주 사용된다 → 강력하게 묶이고 수정할 수 없음
- 델리게이션은 상속보다는 유연하다
- 상속 → 부모 자식 관계, 부모를 바꿀 수 없음
- 델리게이션 → 자식간에 친구 관계, 서로 다른 친구를 사귈 수 있음
- 자바는 상속에 대해 많은 지원을 했고 상대적으로 델리게이션에 대해서는 지원이 약했음

# 상속 대신 델리게이션을 써야 하는 상황

- 클래스의 객체가 다른 클래스의 객체가 들어갈 자리에 쓰여야하면 상속을 사용한다
- 클래스의 객체가 단순히 다른 클래스의 객체를 사용만 해야 하면 델리게이션을 사용하자

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9eeac1a6-560d-468e-8a43-c2f062c194df/Untitled.png)

위 그림에서 상속과 델리게이션의 차이점을 볼 수 있다

- 베이스 클래스에서 상속받은 인스턴스를 자식 클래스에서 마음대로 바꾸려하는 행동은 오류를 일으킬 수 있음
- 부모 클래스의 메소드를 오버라이드할 때 베이스 클래스의 외부 동작을 유지해야 함 (LSP - 리스코프 치환 원칙) → 엄청난 제약 사항
- 클래스의 구현을 새로 하거나 한 클래스의 인스턴스를 ‘개는 동물이다’와 같이 포함 관계에 있는 다른 클래스로 대체할 땐 상속을 사용하자
- 오직 다른 객체의 구현을 재사용하는 경우라면 델리게이션을 사용하자

# 델리게이션을 사용한 디자인

## 잘못된 경로로의 상속

```kotlin
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
```

- Manager가 JavaProgrammer나 특정 언어의 프로그래머라고 한적이 없지만 상속이 그렇게 만든다.
- 원래 의도한 바는 모든 Worker에게 Manager가 의존하는 것이다.

## 코틀린의 by 키워드를 사용한 델리게이션

- 1, 2번 Manger는 중복된 메소드 호출과 [DRY](https://johngrib.github.io/wiki/dry-principle/)(Don't repeat yourself), OCP 원칙 위반으로 지저분해졌다.
- 코틀린의 by 키워드의 왼쪽에는 인터페이스가, 오른쪽엔 해당 인터페이스를 구현한 클래스가 필요하다.
- 상속과의 주요한 차이점이 있다.
  - Manager클래스는 JavaProgrammer 클래스를 상속받지 않는다.
  - 상속을 사용한 솔루션에서 work()같은 메소드를 호출하는 것은 베이스 클래스로 요청을 넘긴다

# 파라미터에 위임하기

- 아래 코드에서 Mager의 인스턴스가 명시적으로 생성된 JavaProgrammer의 인스턴스로 델리게이트 하는데 이런 구현엔 두가지 이슈가 있다.
  - Manager 클래스의 인스턴스는 오직 JavaProgrammer의 인스턴스에만 요청할 수 있다.
  - Manager의 인스턴스는 델리게이션에 접근할 수 없다. → Manager 클래스 안에 다른 메소드를 작성하더라도 해당 메소드에서는 델리게이션에 접근할 수 없다는 의미

```jsx
// project2.kt

interface Worker {
	fun work()
	fun takeVacation()
}

class JavaProgrammer: Worker {
	override fun work() = println("...write Java...")
	override fun takeVacation() = println("...code at the beach...")
}

class CSharpProgrammer: Worker {
	override fun work() = println("...write C#...")
	override takeVacation() = println("...branch at the ranch...")
}

class Manager(val staff: Worker): Worker by staff {
	fun metting() = println("organizing meeting with ${staff.javaClass.simpleName}")
}

fun main() {
	val doe = Manager(CSharpProgrammer())
	val roe = Manager(JavaProgrammer())
	doe.work() //...write C#...
	doe.meeting() //organizing meeting with CSharpProgrammer
	roe.work() //...write Java...
	roe.meeting() // organizing meeting with Java
}
```

# 메소드 충돌 관리

- override 키워드 사용 가능
- 두개의 델리게이션 사용 시 두 델리게이션에 같은 메소드가 있으면 override 해서 구현해줘야한다.

```kotlin
// project3.kt

interface Worker {
	fun work()
	fun takeVacation()
	fun fileTimeSheet() = println("Why Really?")
}

class JavaProgrammer: Worker {
	override fun work() = println("...write Java...")
	override fun takeVacation() = println("...code at the beach...")
}

class CSharpProgrammer: Worker {
	override fun work() = println("...write C#...")
	override takeVacation() = println("...branch at the ranch...")
}

interface Assistant {
	fun doChores()
	fun fileTimeSheet() = println("No escape from that")
}

class DepartmentAssistant: Assistant {
	override fun doChores() = println("routine stuff")
}

class Manager(val staff: Worker, val assistant: Assistant): Worker by staff, Assistant by assistant {
	override fun takeVacation() = println("of course")
	override fun fileTimeSheet()
		print("manually forwarding this...")
		assistant.fileTimeShee()
	}
}

fun main() {
	val doe = Manager(CSharpProgrammer(), DepartmentAssistant())
	doe.work() //...write C#...
	doe.takeVacation() //of course
	doe.doChores() //routine stuff
	doe.fileTimeShee() //manually forwarding this...No escape from that

}
```

# 델리게이션의 주의사항

- 델리게이션의 목적은 Manager가 Worker를 **이용**하는것, 하지만 코틀린의 델리게이션 구현의 부작용으로 Manager는 Worker로 **취급**된다.
- 파라미터로 val을 사용하는것 권장 manger.staff를 변경하더라도 참조는 변경되지 않음

# 변수와 속성 델리게이션

## 변수 델리게이션

```kotlin
//PoliteString.kt

import kotlin.reflect.KProperty

class PoliteString (var content: String) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = content.replace("stupid", "s*****")
	operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
		content = value
	}
	fun beingpolite(content: String) = PoliteString(content)
}

fun main() {
	var comment: String by PoliteString("Some nice message")

	println(comment) // Some nice message
	comment = "This is stupid"
	println(comment) // This is s*****
	println("commnet is of length: ${comment.length}) // comment is of length: 14

	var comment2: String by beingpolite("Some nice message")
}
```

## 속성 델리게이션

```kotlin
//postcomment.kt

import kotlin.reflect.KProperty
import kotlin.collections.MutableMap

class PoliteString(val dataSource: MutableMap<String, Any>) {
	operator fun getValue(thisRef: Any?, peroperty: KProperty<*>) = (dataSorce[property.name] as? String)?.replace("stupid", "s*****")?: ""
	operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
		dataSource[property.name] = value
	}
}

class PostComment(dataSource: MutableMap<String, Any>) {
	val title: String by dataSource
	var likes: Int by dataSource
	val comment: String by PoliteString(dataSource)
	override fun toString() = "Title: $title Likes: $likes Comment: $commnet":
}

fun main() {
	val data = listOf(
		mutableMapOf(
			"title" to "Using Delegation",
			"likes" to 2,
			"comment" to "Keep it simple, stupid")
		),
		mutableMapOf(
			"title" to "Using Inheritance",
			"likes" to 1,
			"comment" to "Prefer Delegation where possible")
		)
	)

	val forPost1 = PostCommnet(data[0])
	val forPost2 = PostCommnet(data[1])

	forPost1.likes++

	println(forPost1) //Title: Using Delegation Likes: 3 Comment: Keep it simple, s*****
	println(forPost2) //Title: Using Inheritance Likes: 1 Comment: Prefer Delegation where possible
}
```

# 빌트인 스탠다드 델리게이션

- observable 델리게이션은 속성의 값이 변하는 것을 지켜보게 해주는 유용한 기능
- Vetoable 델리게이션은 기본 규칙이나 비즈니스 로직에 기반한 속성이 변경되는 것을 막아준다

```kotlin
//shortcircuit.kt

fun getTemperature(city: String): Double {
	println("fetch from webservice for $city")
	return 30.0
}

fun main() {
	val showTemperature = false
	val city = "Boulder"

	if(showTemperature && getTemperature(city) > 20) //(nothing here)
		println("Warm")
	else
		println("Nothing to report") //Nothing to report
}
```

## 조금 게을러도 괜찮다

```kotlin
//eagerevaluation.kt

fun getTemperature(city: String): Double {
	println("fetch from webservice for $city")
	return 30.0
}

fun main() {
	val showTemperature = false
	val city = "Boulder"

	val temperature = getTemperature(city) //fetch from webservice
	if(showTemperature && temperature > 20)
		println("wWarm")
	else
		println("Nothing to report") //Nothing to report
}
```

```kotlin
//lazyevaluation.kt

fun getTemperature(city: String): Double {
	println("fetch from webservice for $city")
	return 30.0
}

fun main() {
	val showTemperature = false
	val city = "Boulder"

	val temperature by lazy {getTemperature(city)}
	if(showTemperature && temperature > 20) //(nothing here)
		println("wWarm")
	else
		println("Nothing to report") //Nothing to report
}
```

## 옵저버블 델리게이션

- Kotlin.properties.Delegates는 observable() 함수를 가진다
- observable은 연관된 변수나 속성의 변화를 가로채는 ReadWriteProperty 델리게이션을 만들어 변화가 발생하면 observable() 함수에 등록한 이벤트 핸들러를 호출한다
- 이벤트 핸들러는 속성, 이전 값, 새로운 값에 대한 메타데이터를 가지고 있는 Kproperty 타입의 파라미터 3개를 받고 아무것도 리턴하지 않는다

```kotlin
//observe.kt

import Kotlin.properties.Delegates.observable

fun main() {
	var count by observable(0) {
		property, oldValue, newValue -> println("Property: $property old: $oldValue new: $newValue")
	}

	println("The value of count is: $count") //The value of count is: 0
	count++
	println("The value of count is: $count") //The value of count is: 1
	count--
	println("The value of count is: $count") //The value of count is: 0
}
```

## 거부권을 연습하자

```kotlin
// veto.kt
import kotlin.properties.Delegates.vetoable

fun main() {
	var count by vetoable(0) {_, oldValue, newValue -> newValue > oldValue}

	println("The value of count is: $count") //The value of count is: 0
	count++
	println("The value of count is: $count") //The value of count is: 1
	count--
	println("The value of count is: $count") //The value of count is: 1
}

```

# 정리

- 모범적인 사례들을 보면 상속보다는 델리게이션을 사용해야한다고 제안한다.
- by 키워드를 사용하면 getValue() 메소드와 setValue()메소드를 구현한 모든 객체에 델리게이션을 사용할 수 있다.
- 빌트인 스탠다드 델리게이션도 사용할 수 있다 (ex. lazy delegation)

# References

[DRY 원칙](https://johngrib.github.io/wiki/dry-principle/)

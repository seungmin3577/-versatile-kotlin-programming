# Chapter2. Java개발자를 위한 코틀린 필수 사항

- 코틀린은 세미콜론, 타입 정의, 클래스 등 다른 언어에서는 필수적인 것들을 몇 가지 선택사항(Optional)으로 만듦.
- 코틀린 변수를 만들 때 뮤터블(Mutable, 변경가능)인지 이뮤터블(immutable, 변경 불가능)인지를 결정해야 한다.
- 문자열 표현식과 멀티라인 스트링을 사용할 때 스트링 탬플릿을 제공해서 고통을 줄여준다.
- 뮤터블 변수의 사용을 줄이기 위해서 명령문 보다는 표현식을 선호한다.

# 더 적은 타이핑

- 세미콜론은 생략해도 된다.
- 변수 타입 지정은 생략해도 된다. (타입 추론이 가능하다.)

  ```kotlin
  // typeinference.kts

  val greet = "hello"
  println(greet) // hello
  println(greet::class) // class kotlin.String
  println(greet.javaClass) // class java.lang.String
  ```

- 클래스와 함수는 생략 가능하다

  ```kotlin
  //standalone.kts

  fun nofluff() {
  	println("nofluff called...")
  	throw RuntimeException("oops")
  }

  println("not in a function, calling nofluff()")

  try {
  	nofluff()
  } catch(ex: Exception) {
  	val stackTrace = ex.getStackTrace()
  	println(stackTrace[0])
  	println(stackTrace[1])
  }

  /**
   * Result
   * not in a function, calling nofluff()
   * nofluff called...
   * Standalone.nofluff(standalone.kts:4)
   * Standalone.<init>(standalone.kts:10)
   * */
  ```

- Try-Catch는 선택사항이다.

# 현명한 경고

<애자일 Practice>

> 경고를 오류처럼 다루는 것이 올바른 소프트웨어 개발 습관이다.

# var보다는 val

- val은 Java의 final과 비슷하다.
- val로 정의한 변수의 값을 바꾸거나 재할당하려는 시도를 하면 컴파일 오류가 발생한다.
- 뮤터빌리티는 코트를 추론하기 어렵게 만든다.
- 뮤터블한 코드는 오류가 발생할 가능성이 더 높다.
- val은 참조에 대한 이뮤터빌리티만 보장해 준다, 객체의 변화는 방지할 수 없다.
- 가능하면 var보다는 val을 사용하자.

# 향상된 동일성 체크

- Java의 equals() 메소드 또는 코트린의 ==연산자는 값을 비교한다. '구조상의 동일성' 이라고 부른다
- Java의 == 연산자 또는 코틀린의 === 연산자는 참조 대상을 비교한다. '참조상의 동일성'이라고 부른다. 참조상의 동일성은 참조를 비교하고 두 비교대상이 같은 객체를 참조하는 경우 true를 반환한다. 코틀린의 === 연산자는 Java의 == 연산자와 일치한다.
- 코틀린의 구조상의 동일성 연산자 == 는 null 참조를 안전하게 다룬다.
  ```kotlin
  println("hi" == "hi") // true
  println("hi" == "Hi") // false
  println(null == "hi") // false
  println("hi" == null) // false
  println(null == null) // true
  ```

# 문자열 템플릿

- 큰따옴표 문자열 안에서 $ 심볼을 변수 앞에 붙여주면 어떤 변수라도 문자열 안에 들어간다
- 변수 하나보다 더 복잡한 명령문이라면 ${}로 감싸서 사용할 수 있다.

```kotlin
// stringtemplate.kts
val price = 12.25
val taxRate = 0.08
val output = "The amount $price after tax comes to $${price * (1 + taxRate)}"
val disclaimer = "The amount is in US$, that's right in \$only"
println(output) // The amount 12.25 after tax comes to $13.23
println(disclaimer) // The amount is in US$, that's right in $only

// mutateconfusion.kts
var factor = 2
fun dobleIt(n: Int) = n * factor
var message = "The factor is $factor"
factor = 0
println(doubleIt(2)) // 0
println(message) // The factor is 2
```

# Raw 문자열

- 코틀린에서는 이스케이프 문자를 사용하는 대신 시작과 끝에서 큰따옴표를 세개를 이용해 raw 문자열을 사용할 수 있다.
- 줄밖무 문자를 포함할 수 있고, 문자열 템플릿으로 사용될 수 있다. + 연산자 없이 멀티라인 문자열을 만들어보자

```kotlin
//memo.kts

val name = "Eve"
val memo = """Dear $name, a quick reminder about the
~party we have scheduled next Tuesday at
~the 'low Ceremony Cafe' at Noon. | Please plan to ..."""

println(memo.trimMargin("~")) // default |
```

# 표현식은 많이, 명령문은 적게

- 명령문은 많은 부작용을 가지고 있다.
  - 상태가 변한다
  - 변수를 변하게 한다
  - 파일을 작성한다
- 표현식은 결과를 리턴해주고 어떤 상태도 변화시키지 않는다.

- 코틀린은 try-catch도 표현식으로 취급한다.

  ```kotlin
  fun tryExpr(blowup: Boolean): Int {
  	return try {
  		if(blowup) {
  			throw RuntimeException("fail")
  		}
  		2
  	} catch(ex: Exception) {
  		4
  	} finally {
  		//...
  	}
  }

  println(tryExpr(false)) // 2
  println(tryExpr(true)) //4
  ```

# 정리

- 코틀린은 기초적인 프로그래밍 작업에서 사용되는 관용적인 코드 대부분을 없애버렸다.
- 같은 작업을 한다면 다른 언어보다 코틀린을 사용할 때 더 적은 코드를 타이핑 하는 것으로 완료할 수 있다.
- 세미콜론은 선택사항이다.
- 변수를 선언할때 타입 추론을 사용한다.
- 예외처리를 강요하지도 않는다.
- 오류로부터 보호해주기 위해서 현명한 경고를 해준다.
- Immutability와 mutability를 미리 선택하기 때문에 안정성도 올라간다.
- 비교를 할 때 Nullpoint Exception이 발생하지 않는다.
- 문자열 템플릿과 멀티라인 무자열은 문자열을 표현식으로 만들어야 하는 수고를 줄여준다.
- 다른 언어들에 비해 명령문 보다 표현식을 제공해 준다.

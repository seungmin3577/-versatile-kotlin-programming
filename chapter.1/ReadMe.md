# Chapter1. 코틀린 시작하기

# 코틀린의 특징과 기원

- 코틀린은 러시아 상트페테부르크 근처의 섬이름에서 가져왔다.
- 코틀린은 멀티 플랫폼을 지원한다.
- 코틀린은 Java와 유사한 문법을 가진다.
- 델리게이션 (delegation, 위임)은 코드를 재사용할때 상속보다 더 좋은 디자인을 할 수 있도록 도와준다.
- 정적 타입체크를 완벽하게 지원한다.
- 코루틴(Coroutine)과 컨티뉴에이션(Continuation)으로 비동기 프로그래밍 영역에서 혁신을 이끌고 있다.

# 코틀린을 사랑할 수 밖에 없는 이유

- Java 바이트코드로 컴파일 가능, JS로 트랜스파일 가능
- 코틀린 네이티브를 이용하면 네이티브 바이너리도 될 수 있다.
- 타입 추론이 가능하다.
- 강력한 정적 타입을 지원한다.
- 다양한 프로그래밍 패러다임을 지원한다.
  - 비동기 프로그래밍
  - 스크립트
  - 객체지향 프로그래밍
  - 함수형 프로그래밍
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dd887203-26d8-4fbc-a7de-b6d64c87550b/Untitled.png)

# 왜 코틀린을 선택해야 하는가?

- `"더 적은것이 낫다"` 라는 철학으로 보일러 플레이트 코드를 덜 쓰도록 해준다.
- 명령형 프로그래밍과 함수형 프로그래밍을 섞어서 쓸 수 있게 해준다.
- 다른 정적 타입 언어들과 비교했을 때 훨 씬 많은 `컴파일 시간 안정성(compile-time-safety)`을 제공한다.
- `코루틴`은 다른 JDK에서 사용 가능한 언어들에 비해 비동기 프로그래밍을 쉽게 만들 뿐만아니라 높은 성능을 제공한다.
- 안드로이드 공식 언어이기 때문에 안드로이드 개발에 가장 좋은 선택이다.

# 코틀린 사용하기

## 코틀린 SDK 설치하기

IntelliJ IDEA를 사용중이라면 코틀린이 함께 설치 된다.

IntelliJ가 없다면 [JetBrains](https://www.jetbrains.com/ko-kr/idea/download) 에서 Community 에디션을 다운받아 설치 해 보자.

코틀린 웹페이지에서 다운로드 컴파일러 링크를 클릭하면 컴파일러만 설치할 수 있다.

Homebrew나 다른 프로그램을 사용할 수 있다.

1.6버전 이상의 JDK도 설치가 되어있어야 한다.

## 설치 확인

```bash
$ kotlinc-jvm -version

info: kotlinc-jvm 1.5.31 (JRE 11.0.13+8-LTS)
```

## 바이트코드로 컴파일하고 실행하기

```kotlin
//Hello.kt
fun main() println("Hello World")
```

```bash
# Hello.kt → Java ByteCode로 Compile
$ java -classpath Hello.jar HelloKt

# Hello.jar 실행
$ java -jar Hello.jar
```

## Script로 실행하기

```kotlin
//listktsfiles.kts
java.io.File(".")
	.walk()
	.filter { file -> file.extension == "kts" }
	.forEach { println(it) }
```

```bash
# kotlin script 실행하기
$ kotlinc-jvm -script listktsfiles.kts
```

## 다른 타깃으로 컴파일하기

- 코틀린은 여러 개의 타깃으로 컴파일이 되는 몇 안 되는 언어 중에 하나이다.
- Android
- Javascript
- Native
- WebAssembly

# 정리

- 코틀린은 선택의 언어이다.
- 다양한 프로그래밍 패러다임을 지원한다.
- 다양한 플랫폼을 지원한다.
- Java binary 파일로 컴파일도 할 수 있고 javascript로 트랜스파일할 수도 있다.

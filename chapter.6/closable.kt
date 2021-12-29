
import java.io.StringWriter

fun <T: AutoCloseable> useAndClose(input: T) {
    input.close() //Ok
}

fun main() {
    val writer = java.io.StringWriter()
    writer.append("hello ")
    useAndClose(writer)
    println(writer) // hello
}
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
    println(writer)
}
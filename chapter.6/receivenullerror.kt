

import kotlin.text.reversedfun main () {
    println("Nickname for William is ${nickName("William")}")
    println("Nickname for William is ${nickName("Venkat")}")
    // println("Nickname for William is ${nickName(null)}") //error: null can not be a value of a non-null type String
}

fun nickName(name: String?): String? {
    if(name == "William") {
        return "Bill"
    }
    return name.reversed()
}
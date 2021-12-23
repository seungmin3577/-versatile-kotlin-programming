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
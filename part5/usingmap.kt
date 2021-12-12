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

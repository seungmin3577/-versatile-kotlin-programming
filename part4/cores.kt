fun systemnInfo(): String {
    val numberOfCores = Runtime.getRuntime().availableProcessors()
    return when (numberOfCores) {
        1 -> "1 core, packing this onte to the museum"
        in 2..16 -> "You han $numberOfCores cores"
        else -> "numberOfCores cores!, I want your machine"
    }
}

fun reFactorSystemnInfo(): String  = when (val numberOfCores = Runtime.getRuntime().availableProcessors()) {
        1 -> "1 core, packing this onte to the museum"
        in 2..16 -> "You han $numberOfCores cores"
        else -> "numberOfCores cores!, I want your machine"
    }

fun main() {
    systemnInfo()
    reFactorSystemnInfo()
}
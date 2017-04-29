package org.example.fpexercises

typealias BandMap = Map<String, Any>

fun main(args: Array<String>) {
    doFunctionCombo()
}

@Suppress("ConvertLambdaToReference", "unused")
private fun doMap() {
    // Transform each element in the array from a String to Hash Code.
    val codeNames = arrayOf("Mary", "Isla", "Sam").map { it.hashCode() }

    codeNames.forEach { println("Code Name: $it") }
}

@Suppress("unused")
private fun doReduce() {
    // Combine all elements in the List into a single result.
    val sum = (0..4).toList().reduce { a, x -> a + x }

    println("Sum: $sum")
}

@Suppress("unused")
private fun doHigherOrderFunctions() {
    val people = arrayOf(
            mapOf("name" to "Mary", "height" to 160),
            mapOf("name" to "Isla", "height" to 80),
            mapOf("name" to "Sam")
    )
    // Do the following to get the average height:
    //   1. Apply a filter that only gets the maps that contain the "height" key which are returned as a new List.
    //   2. Do a sum of height (key in each map) and return it as a new Int.
    //   3. Count the number of maps in people that contain the "height" key.
    //   4. Divide the sum (step 2) by the count (step 3) to get the average height.
    val avgHeight = people.filter { "height" in it }.sumBy { it["height"] as Int } / people.count { "height" in it }

    println("Average Height: $avgHeight")
}

@Suppress("unused")
private fun doRecursion() {
    fun zero(str: String): String {
        var result = ""

        if (str[0] == '0') result = str.slice(1..str.length - 1)
        return result
    }

    fun one(str: String): String {
        var result = ""

        if (str[0] == '1') result = str.slice(1..str.length - 1)
        return result
    }

    // Tail recursion occurs when a function calls itself, and some optimisations are done to prevent the stack from
    // being overloaded (too many function calls). Note in Kotlin that tail recursion only works if the defined
    // function returns itself in the last line of the block and the "tailrec" keyword is applied.
    tailrec fun matchesRules(str: String, rules: List<(String) -> String>): Boolean {
        if (rules.isEmpty()) return true
        else if (rules[0](str).isEmpty()) return false
        else return matchesRules(str.slice(1..str.length - 1), rules.slice(1..rules.size - 1))
    }

    val rules = listOf(::zero, ::one, ::one)
    val str = "0111"

    println("Matching rules zero one one to $str...")
    println("Matches Rules: ${matchesRules(str, rules)}")
}

private fun doFunctionCombo() {
    fun changeCountryToCanada(band: BandMap): BandMap {
        val result = band.toMutableMap()

        result["country"] = "canada"
        return result
    }

    fun stripPunctuationFromName(band: BandMap): BandMap {
        val result = band.toMutableMap()

        result["name"] = (result["name"] as String).replace(".", "")
        return result
    }

    fun capitalizeNames(band: BandMap): BandMap {
        val result = band.toMutableMap()

        result["name"] = (result["name"] as String).toTitleCase()
        return result
    }

    fun pipelineEach(bands: Array<BandMap>, functions: List<(BandMap) -> BandMap>): Array<BandMap> {
        val result = bands

        (0..bands.size - 1).forEach { pos ->
            // Call each function to create the result.
            functions.forEach { f -> result[pos] = f(result[pos]) }
        }
        return result
    }

    val bands = arrayOf(
            mapOf("name" to "sunset rundown", "country" to "UK", "active" to false),
            mapOf("name" to "zolnata", "country" to "Germany", "active" to false),
            mapOf("name" to "a silver mt. zion", "country" to "Spain", "active" to true)
    )
    // Creates a List containing references to the changeCountryToCanada, stripPunctuationFromName, and capitalizeNames
    // functions which will be invoked (called) later.
    val functions = listOf(::changeCountryToCanada, ::stripPunctuationFromName, ::capitalizeNames)

    pipelineEach(bands, functions).forEach { println("Band: $it") }
}

private fun String.toTitleCase(): String = split(' ').map { "${it[0].toUpperCase()}${it.slice(1..it.length - 1)}" }.
        reduce { a, s -> "$a $s" }
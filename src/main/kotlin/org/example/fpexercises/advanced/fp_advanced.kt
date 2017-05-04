@file:Suppress("unused")

package org.example.fpexercises.advanced

typealias BandMap = Map<String, Any>

private fun changeCountryToCanada(band: BandMap): BandMap {
    val result = band.toMutableMap()

    result["country"] = "canada"
    return result
}

private fun stripPunctuationFromName(band: BandMap): BandMap {
    val result = band.toMutableMap()

    result["name"] = (result["name"] as String).replace(".", "")
    return result
}

private fun capitalizeNames(band: BandMap): BandMap {
    val result = band.toMutableMap()

    result["name"] = (result["name"] as String).toTitleCase()
    return result
}

private fun pipelineEach(bands: Array<BandMap>, functions: List<(BandMap) -> BandMap>): Array<BandMap> {
    val result = bands

    (0..bands.size - 1).forEach { pos ->
        // Call each function to create the result.
        functions.forEach { f -> result[pos] = f(result[pos]) }
    }
    return result
}

fun doFunctionCombo() {
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

fun String.toTitleCase(): String = split(' ').map {
    "${it[0].toUpperCase()}${it.slice(1..it.length - 1)}"
}.reduce { a, s -> "$a $s" }
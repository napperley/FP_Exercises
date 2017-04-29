package org.example.fpexercises

fun main(args: Array<String>) {
    doMap()
}

@Suppress("ConvertLambdaToReference")
private fun doMap() {
    // Transform each element in the array from a String to Hash Code.
    val codeNames = arrayOf("Mary", "Isla", "Sam").map { it.hashCode() }

    codeNames.forEach { println("Code Name: $it") }
}
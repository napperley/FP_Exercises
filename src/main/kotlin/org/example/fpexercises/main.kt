package org.example.fpexercises

fun main(args: Array<String>) {
    doReduce()
}

@Suppress("ConvertLambdaToReference", "unused")
private fun doMap() {
    // Transform each element in the array from a String to Hash Code.
    val codeNames = arrayOf("Mary", "Isla", "Sam").map { it.hashCode() }

    codeNames.forEach { println("Code Name: $it") }
}

private fun doReduce() {
    // Combine all elements in the List into a single result.
    val sum = (0..4).toList().reduce { a, x -> a + x }

    println("Sum: $sum")
}
package org.example.fpexercises

fun main(args: Array<String>) {
    doHigherOrderFunctions()
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
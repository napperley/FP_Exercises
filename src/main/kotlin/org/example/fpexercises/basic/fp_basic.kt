@file:Suppress("unused")

package org.example.fpexercises.basic

fun doPureFunction() {
    val x = 5
    val y = 7

    fun add(x: Int, y: Int) = x + y
    println("Adding $x + $y: ${add(5, 7)}")
}

@Suppress("ConvertLambdaToReference")
fun doMap() {
    // Transform each element in the array from a String to Hash Code.
    val codeNames = arrayOf("Mary", "Isla", "Sam").map { it.hashCode() }

    codeNames.forEach { println("Code Name: $it") }
}

fun doReduce() {
    // Combine all elements in the List into a single result.
    val sum = (0..4).toList().reduce { a, x -> a + x }

    println("Sum: $sum")
}

fun doFilter() {
    print("Numbers divisible by 2: ")
    // Create a new List containing all elements where the predicate in the filter evaluates to "true", and print the
    // contents of the List to the screen.
    println((1..20).filter { it % 2 == 0 })
}

fun doHigherOrderFunctions() {
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

fun doForEach() {
    println("For Loop...")
    (1..10).forEach { println("Num: $it") }
}

fun doLooping() {
    // Emulates a basic for loop. Function takes an accumulator ("a").
    tailrec fun sumRange(start: Int, end: Int, a: Int): Int {
        if (start > end) return a
        else return sumRange(start + 1, end, a + start)
    }

    println("Sum Range: ${sumRange(1, 10, 0)}")
}
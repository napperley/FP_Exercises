@file:Suppress("unused")

package org.example.fpexercises.intermediate

private fun zero(str: String): String {
    var result = ""

    if (str[0] == '0') result = str.slice(1..str.length - 1)
    return result
}

private fun one(str: String): String {
    var result = ""

    if (str[0] == '1') result = str.slice(1..str.length - 1)
    return result
}

fun doRecursion() {
    val str = "0111"
    val rules = listOf(::zero, ::one, ::one)

    // Tail recursion occurs when a function calls itself, and some optimisations are done to prevent the stack from
    // being overloaded (too many function calls). Note in Kotlin that tail recursion only works if the defined
    // function returns itself in the last line of the block and the "tailrec" keyword is applied.
    tailrec fun matchesRules(str: String, rules: List<(String) -> String>): Boolean {
        if (rules.isEmpty()) return true
        else if (rules[0](str).isEmpty()) return false
        else return matchesRules(str.slice(1..str.length - 1), rules.slice(1..rules.size - 1))
    }

    println("Matching rules zero one one to $str...")
    println("Matches Rules: ${matchesRules(str, rules)}")
}

fun doReuse() {
    fun validateValue(value: String, type: String, parseFunc: (String) -> Boolean) {
        if (parseFunc(value)) println("Valid: $type")
        else println("Invalid: $type")
    }

    validateValue(value = "123-45-6789", type = "SSN") { value ->
        Regex(pattern = """/^\d{3}-\d{2}-\d{4}$/""").matches(value)
    }
    validateValue(value = "(123)456-7890", type = "Phone") { value ->
        Regex(pattern = """/^\(\d{3}\)\d{3}-\d{4}$/""").matches(value)
    }
}

fun doClosure() {
    fun makeAddr(constValue: Int): (Int) -> Int {
        return fun(value: Int) = constValue + value
    }

    val add10 = makeAddr(10)
    println("Closure...")
    println(add10(20))
    println(add10(30))
    println(add10(40))
}
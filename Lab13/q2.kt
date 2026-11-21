fun greet(name: String, greeting: String = "Hello") {
    println("$greeting, $name!")
}

fun createProfile(name: String, age: Int, city: String = "Unknown") {
    println("Name: $name | Age: $age | City: $city")
}

fun printNumbers(vararg numbers: Int) {
    println("Received ${numbers.size} numbers:")
    for (n in numbers) {
        print("$n ")
    }
    println()
    println("Sum = ${numbers.sum()}")
}

fun applyToList(list: List<Int>, operation: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for (item in list) {
        result.add(operation(item))
    }
    return result
}

fun main() {
    println("--- 1. Default parameters ---")
    greet("Akshaya")                 // uses default greeting
    greet("Akshaya", "Welcome")      // overrides default

    println("\n--- 2. Named arguments ---")
    createProfile(age = 20, name = "Akshaya", city = "Delhi")   // out of order
    createProfile(name = "Rahul", age = 22)                     // city uses default

    println("\n--- 3. Vararg ---")
    printNumbers(1, 2, 3)
    printNumbers(10, 20, 30, 40, 50)

    println("\n--- 4. Higher-order function ---")
    val nums = listOf(1, 2, 3, 4, 5)
    val squares = applyToList(nums) { it * it }
    val doubled = applyToList(nums) { it * 2 }
    println("Original: $nums")
    println("Squares:  $squares")
    println("Doubled:  $doubled")
}
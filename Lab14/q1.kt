data class Student(val name: String, val percentage: Double, val grade: String)

fun main() {

    println("===== Part 1: Collections =====")

    val readOnlyList: List<String> = listOf("Apple", "Banana", "Cherry")
    val mutableList: MutableList<String> = mutableListOf("Apple", "Banana", "Cherry")

    println("Read-only list: $readOnlyList")
    println("Contains Banana? ${readOnlyList.contains("Banana")}")
    println("Element at index 1: ${readOnlyList[1]}")

    mutableList.add("Mango")
    mutableList.remove("Apple")
    println("Mutable list after add/remove: $mutableList")

    val readOnlySet: Set<Int> = setOf(1, 2, 3, 3, 2)
    val mutableSet: MutableSet<Int> = mutableSetOf(1, 2, 3)

    println("Read-only set: $readOnlySet")
    mutableSet.add(4)
    mutableSet.add(2)
    mutableSet.remove(1)
    println("Mutable set after add/remove: $mutableSet")
    println("Set contains 3? ${mutableSet.contains(3)}")

    val readOnlyMap: Map<String, Int> = mapOf("one" to 1, "two" to 2)
    val mutableMap: MutableMap<String, Int> = mutableMapOf("one" to 1, "two" to 2)

    println("Read-only map: $readOnlyMap")
    println("Get 'two': ${readOnlyMap["two"]}")
    mutableMap["three"] = 3        // add
    mutableMap.remove("one")       // remove
    println("Mutable map after add/remove: $mutableMap")
    println("Map contains key 'three'? ${mutableMap.containsKey("three")}")

    println("\n===== Part 2: map, filter, reduce =====")

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Original list: $numbers")

    val squares = numbers.map { it * it }
    println("After map (square): $squares")

    val evenSquares = squares.filter { it % 2 == 0 }
    println("After filter (even only): $evenSquares")

    val sum = evenSquares.reduce { acc, n -> acc + n }
    println("After reduce (sum): $sum")

    val words = listOf("kotlin", "java", "go", "clojure")
    val upper = words.map { it.uppercase() }
    println("Uppercase: $upper")
    val longWords = upper.filter { it.length > 4 }
    println("Length > 4: $longWords")
    val joined = longWords.reduce { acc, s -> "$acc-$s" }
    println("Joined with reduce: $joined")

    println("\n===== Part 3: sortedBy and groupBy =====")

    val students = listOf(
        Student("Akshaya", 88.5, "A"),
        Student("Rahul", 72.0, "B"),
        Student("Priya", 95.2, "A"),
        Student("Aman", 65.8, "C"),
        Student("Neha", 79.4, "B")
    )

    println("Sorted by percentage (ascending):")
    students.sortedBy { it.percentage }.forEach { println("  $it") }

    println("Sorted by percentage (descending):")
    students.sortedByDescending { it.percentage }.forEach { println("  $it") }

    println("Grouped by grade:")
    val grouped = students.groupBy { it.grade }
    for ((grade, list) in grouped) {
        println("  Grade $grade -> ${list.map { it.name }}")
    }
}
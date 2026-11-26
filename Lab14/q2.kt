fun main() {

    println("===== Scenario 1: Nullable vs Non-nullable =====")

    var nonNullable: String = "Kotlin"
    var nullable: String? = "Kotlin"

    println("Non-nullable: $nonNullable")
    println("Nullable: $nullable")

    nullable = null                 
    println("Nullable after assigning null: $nullable")


    println("Uncommenting the lines above gives compile-time errors.")

    println("\n===== Scenario 2: Safe Call Operator (?.) =====")

    val name1: String? = "Akshaya"
    val name2: String? = null

    println("name1?.length = ${name1?.length}")          // 7
    println("name2?.length = ${name2?.length}")          // null, no crash
    println("name1?.uppercase() = ${name1?.uppercase()}")
    println("name2?.uppercase() = ${name2?.uppercase()}") // null

    println("\n===== Scenario 3: Elvis Operator (?:) =====")

    val city1: String? = "Gurugram"
    val city2: String? = null

    val result1 = city1 ?: "Unknown City"
    val result2 = city2 ?: "Unknown City"
    println("city1 -> $result1")   // Gurugram
    println("city2 -> $result2")   // Unknown City

    val len = name2?.length ?: 0   // safe call + Elvis together
    println("Length of null name with default: $len")

    println("\n===== Scenario 4: !! Operator with try-catch =====")

    val safeValue: String? = "Hello"
    println("Length using !! on non-null value: ${safeValue!!.length}")

    val nullValue: String? = null
    try {
        val length = nullValue!!.length
        println("Length: $length")
    } catch (e: NullPointerException) {
        println("Caught NullPointerException: the value was null")
    }

    println("\nProgram finished normally.")
}
fun main() {
    println("Hello Kotlin!!!")

    print("\nEnter department code (IT / HR / other): ")
    val deptCode = readln().trim().uppercase()
    print("Enter years of experience: ")
    val years = readln().toInt()
    print("Enter salary: ")
    val salary = readln().toDouble()

    val bonusPercent = when {
        deptCode == "IT" && years > 5 -> 20
        deptCode == "HR" && years > 3 -> 15
        else -> 10
    }
    val bonus = salary * bonusPercent / 100
    println("Department: $deptCode, Experience: $years yrs")
    println("Bonus: $bonusPercent% = $bonus")

    // Part 3: Average of the digits of an integer
    print("\nEnter an integer: ")
    val number = readln().toInt()
    val digits = Math.abs(number).toString()
    val sum = digits.sumOf { it.digitToInt() }
    val average = sum.toDouble() / digits.length
    println("Digits: $digits, Sum: $sum, Average: $average")
}
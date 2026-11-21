class Student(
    private var rollNo: Int,
    private var name: String,
    private var course: String,
    private var percentage: Double
) {
    fun acceptData() {
        print("Enter roll number: ")
        rollNo = readln().toInt()
        print("Enter name: ")
        name = readln()
        print("Enter course: ")
        course = readln()
        print("Enter percentage: ")
        percentage = readln().toDouble()
    }

    fun displayData() {
        println("------------------------------")
        println("Roll No    : $rollNo")
        println("Name       : $name")
        println("Course     : $course")
        println("Percentage : ${"%.2f".format(percentage)}%")
        println("------------------------------")
    }
}

fun main() {
    val s1 = Student(101, "Akshaya", "B.Tech CSE", 85.5)
    println("Student 1 (initial data):")
    s1.displayData()

    println("\nUpdate Student 1:")
    s1.acceptData()
    s1.displayData()

    val s2 = Student(0, "", "", 0.0)
    println("\nEnter details for Student 2:")
    s2.acceptData()
    s2.displayData()
}
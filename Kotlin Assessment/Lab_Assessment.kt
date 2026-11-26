data class Course(
    val code: String,
    val title: String,
    val credits: Int,
    val department: String,
    val instructor: String? = null // Nullable property handled safely
)

data class Student(
    val id: String,
    val name: String,
    val email: String?,
    val enrolledCourses: List<Course> = emptyList()
)

fun Student.totalCredits(): Int = enrolledCourses.sumOf { it.credits }

fun Course.formattedDetails(): String {
    val prof = instructor ?: "TBA" // Elvis operator for null safety
    return "[$code] $title ($credits Credits) - Dept: $department | Instructor: $prof"
}

fun List<Student>.filterByMinCredits(minCredits: Int): List<Student> =
    this.filter { it.totalCredits() >= minCredits }

@DslMarker
annotation class RegistryDsl

@RegistryDsl
class CourseBuilder {
    var code: String = ""
    var title: String = ""
    var credits: Int = 0
    var department: String = "General"
    var instructor: String? = null

    fun build(): Course = Course(code, title, credits, department, instructor)
}

@RegistryDsl
class StudentBuilder {
    var id: String = ""
    var name: String = ""
    var email: String? = null
    private val courses = mutableListOf<Course>()

    fun course(block: CourseBuilder.() -> Unit) {
        courses.add(CourseBuilder().apply(block).build())
    }

    fun build(): Student = Student(id, name, email, courses)
}

@RegistryDsl
class UniversityRegistry {
    val students = mutableListOf<Student>()

    fun student(block: StudentBuilder.() -> Unit) {
        students.add(StudentBuilder().apply(block).build())
    }

    fun generateReport(header: String, block: ReportBuilder.() -> Unit): String {
        return ReportBuilder(header, students).apply(block).build()
    }
}

@RegistryDsl
class ReportBuilder(private val header: String, private val students: List<Student>) {
    private val output = StringBuilder()

    init {
        output.appendLine("=========================================")
        output.appendLine(header.uppercase())
        output.appendLine("=========================================")
    }

    fun includeStudentSummaries() {
        output.appendLine("\n--- Enrolled Students ---")
        students.forEach { student ->
            val contact = student.email ?: "No Email Provided"
            output.appendLine("${student.name} (${student.id}) | Email: $contact | Total Credits: ${student.totalCredits()}")
            student.enrolledCourses.forEach { course ->
                output.appendLine("   • ${course.formattedDetails()}")
            }
        }
    }

    fun includeDepartmentAnalytics() {
        output.appendLine("\n--- Department Credit Breakdown ---")
        // 2. Functional Transformations: flatMap, groupBy, map, reduce
        val allCourses = students.flatMap { it.enrolledCourses }
        val groupedByDept = allCourses.groupBy { it.department }

        groupedByDept.forEach { (dept, courses) ->
            val totalDeptCredits = courses
                .map { it.credits }
                .reduce { acc, credits -> acc + credits }
            output.appendLine("$dept: ${courses.size} enrollments, $totalDeptCredits total credits")
        }
    }

    fun build(): String = output.toString()
}

fun universityRegistry(block: UniversityRegistry.() -> Unit): UniversityRegistry =
    UniversityRegistry().apply(block)

fun main() {
    // Configuring domain using the DSL
    val registry = universityRegistry {
        student {
            id = "S101"
            name = "Aarav Tanwar"
            email = "aarav.s@university.edu"
            course {
                code = "CS301"
                title = "Data Structures & Algorithms"
                credits = 4
                department = "Computer Science"
                instructor = "Dr. Verma"
            }
            course {
                code = "MA201"
                title = "Linear Algebra"
                credits = 3
                department = "Mathematics"
                // instructor intentionally left null to demonstrate null safety
            }
        }

        student {
            id = "S102"
            name = "Akshaya Tanwar"
            email = null // Nullable email
            course {
                code = "CS305"
                title = "Operating Systems"
                credits = 4
                department = "Computer Science"
                instructor = "Prof. Iyer"
            }
            course {
                code = "CS301"
                title = "Data Structures & Algorithms"
                credits = 4
                department = "Computer Science"
                instructor = "Dr. Verma"
            }
        }
    }

    val report = registry.generateReport("Semester Course Registration Report") {
        includeStudentSummaries()
        includeDepartmentAnalytics()
    }

    println(report)

    // Demonstrating standalone collection transformations (filter & map)
    val honorsEligible = registry.students
        .filterByMinCredits(8)
        .map { it.name }

    println("Students eligible for Honors (8+ credits): $honorsEligible")
}
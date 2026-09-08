package main

import (
	"fmt"
	"slices"
)

func main() {

	students := []string{"Nikita", "Shweta", "Nitya"}
	fmt.Println("Initial slice:", students)

	students = append(students, "Mehak")
	fmt.Println("After adding Mehak:", students)

	students = slices.Delete(students, 1, 2)
	fmt.Println("After removing index 1:", students)

	students[1] = "Dev"
	fmt.Println("After updating index 1:", students)

	fmt.Println("Implementing maps")
	marks := map[string]int{
		"Maths":    85,
		"Physics":  78,
		"Computer": 92,
	}
	fmt.Println("\nInitial map:", marks)

	marks["English"] = 88
	fmt.Println("After inserting English:", marks)

	delete(marks, "Physics")
	fmt.Println("After deleting Physics:", marks)

	mark, exists := marks["Maths"]
	if exists {
		fmt.Println("Lookup Maths:", mark)
	} else {
		fmt.Println("Maths not found")
	}
}

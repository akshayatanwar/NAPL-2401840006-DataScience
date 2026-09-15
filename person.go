package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Person struct {
	Name   string
	Age    int
	Job    string
	Salary float64
}

// ReadInput reads a Person's details from user input.
// Uses a pointer receiver so the changes persist on the original struct.
func (p *Person) ReadInput(reader *bufio.Reader) {
	fmt.Print("Enter Name: ")
	name, _ := reader.ReadString('\n')
	p.Name = strings.TrimSpace(name)

	fmt.Print("Enter Age: ")
	ageStr, _ := reader.ReadString('\n')
	age, _ := strconv.Atoi(strings.TrimSpace(ageStr))
	p.Age = age

	fmt.Print("Enter Job: ")
	job, _ := reader.ReadString('\n')
	p.Job = strings.TrimSpace(job)

	fmt.Print("Enter Salary: ")
	salaryStr, _ := reader.ReadString('\n')
	salary, _ := strconv.ParseFloat(strings.TrimSpace(salaryStr), 64)
	p.Salary = salary
}

// Display prints all fields of a Person in a formatted way.
func (p Person) Display() {
	fmt.Println("----------------------")
	fmt.Printf("Name:   %s\n", p.Name)
	fmt.Printf("Age:    %d\n", p.Age)
	fmt.Printf("Job:    %s\n", p.Job)
	fmt.Printf("Salary: %.2f\n", p.Salary)
	fmt.Println("----------------------")
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	var p1, p2 Person

	fmt.Println("Enter details for Person 1:")
	p1.ReadInput(reader)

	fmt.Println("\nEnter details for Person 2:")
	p2.ReadInput(reader)

	fmt.Println("\n--- Person 1 Details ---")
	p1.Display()

	fmt.Println("\n--- Person 2 Details ---")
	p2.Display()
}

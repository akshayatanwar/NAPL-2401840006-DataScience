package main

import (
	"fmt"
	"math"
	"strings"
)

// Error 1: Division by zero
func divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, fmt.Errorf("division by zero is not allowed")
	}
	return a / b, nil
}

// Error 2: Negative square root
func squareRoot(n float64) (float64, error) {
	if n < 0 {
		return 0, fmt.Errorf("cannot calculate square root of a negative number")
	}
	return math.Sqrt(n), nil
}

// Error 3: Empty input
func checkInput(input string) (string, error) {
	if strings.TrimSpace(input) == "" {
		return "", fmt.Errorf("input cannot be empty")
	}
	return input, nil
}

func main() {
	result, err := divide(10, 2)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Division result:", result)
	}

	root, err := squareRoot(-4)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Square root:", root)
	}

	input, err := checkInput("")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Input:", input)
	}
}

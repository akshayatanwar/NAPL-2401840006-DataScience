package main

import (
	"fmt"

	"main.go/mathutil"
)

func main() {
	text := "Hello Go"

	fmt.Println("Original:", text)
	fmt.Println("Reversed:", mathutil.Reverse(text))
	fmt.Println("Vowels:", mathutil.CountVowels(text))

	fmt.Println("Factorial of 5:", mathutil.Factorial(5))
	fmt.Println("2 raised to 5:", mathutil.Power(2, 5))
}

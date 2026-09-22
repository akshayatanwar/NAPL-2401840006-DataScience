package main

import "fmt"

type Student struct {
	X string
	Y float32
}

func main() {
	x := 42
	ptr := &x
	fmt.Println("Value of x:", x)
	fmt.Println("Address of x:", ptr)
	fmt.Println("Value via pointer (*ptr):", *ptr)

	y := 10
	fmt.Println("\nBefore modification, y =", y)
	doubleValue(&y)
	fmt.Println("After modification, y =", y)

	p := new(Student)
	fmt.Println("\nInitial struct:", *p)
	p.X = "Akshaya"
	p.Y = 8.5
	fmt.Println("After modifying fields:", *p)
}

func doubleValue(n *int) {
	*n = *n * 2
}

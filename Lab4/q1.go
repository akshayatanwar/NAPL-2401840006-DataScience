package main

import (
	"fmt"
	"sync"
	"time"
)

type Result struct {
	Task   string
	Values []int
}

func squares(n int, ch chan<- Result, wg *sync.WaitGroup) {
	defer wg.Done()
	fmt.Println("[Squares]   goroutine started")

	res := make([]int, 0, n)
	for i := 1; i <= n; i++ {
		res = append(res, i*i)
	}
	time.Sleep(200 * time.Millisecond)

	fmt.Println("[Squares]   goroutine completed")
	ch <- Result{"Squares", res}
}

func cubes(n int, ch chan<- Result, wg *sync.WaitGroup) {
	defer wg.Done()
	fmt.Println("[Cubes]     goroutine started")

	res := make([]int, 0, n)
	for i := 1; i <= n; i++ {
		res = append(res, i*i*i)
	}
	time.Sleep(100 * time.Millisecond)

	fmt.Println("[Cubes]     goroutine completed")
	ch <- Result{"Cubes", res}
}

func fibonacci(n int, ch chan<- Result, wg *sync.WaitGroup) {
	defer wg.Done()
	fmt.Println("[Fibonacci] goroutine started")

	res := make([]int, 0, n)
	a, b := 0, 1
	for i := 0; i < n; i++ {
		res = append(res, a)
		a, b = b, a+b
	}
	time.Sleep(300 * time.Millisecond)

	fmt.Println("[Fibonacci] goroutine completed")
	ch <- Result{"Fibonacci", res}
}

func main() {
	const n = 10

	ch := make(chan Result, 3)
	var wg sync.WaitGroup

	fmt.Println("main: launching goroutines...")

	wg.Add(3)
	go squares(n, ch, &wg)
	go cubes(n, ch, &wg)
	go fibonacci(n, ch, &wg)

	go func() {
		wg.Wait()
		close(ch)
	}()

	fmt.Println("main: waiting for results...")
	for r := range ch {
		fmt.Printf("main: received %-10s -> %v\n", r.Task, r.Values)
	}

	fmt.Println("main: all tasks finished")
}

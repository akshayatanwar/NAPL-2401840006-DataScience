package main

import (
	"strings"
	"testing"
)

func TestDivide(t *testing.T) {
	// Valid input
	result, err := divide(10, 2)
	if err != nil {
		t.Fatalf("Unexpected error: %v", err)
	}
	if result != 5 {
		t.Errorf("Expected 5, got %v", result)
	}

	// Division by zero
	_, err = divide(10, 0)
	if err == nil {
		t.Fatal("Expected division by zero error")
	}
	if !strings.Contains(err.Error(), "division by zero") {
		t.Errorf("Unexpected error message: %v", err)
	}
}

func TestSquareRoot(t *testing.T) {
	// Valid input
	result, err := squareRoot(25)
	if err != nil {
		t.Fatalf("Unexpected error: %v", err)
	}
	if result != 5 {
		t.Errorf("Expected 5, got %v", result)
	}

	// Negative input
	_, err = squareRoot(-4)
	if err == nil {
		t.Fatal("Expected negative square root error")
	}
	if !strings.Contains(err.Error(), "negative number") {
		t.Errorf("Unexpected error message: %v", err)
	}
}

func TestCheckInput(t *testing.T) {
	// Valid input
	result, err := checkInput("Hello")
	if err != nil {
		t.Fatalf("Unexpected error: %v", err)
	}
	if result != "Hello" {
		t.Errorf("Expected Hello, got %v", result)
	}

	// Empty input
	_, err = checkInput("")
	if err == nil {
		t.Fatal("Expected empty input error")
	}
	if !strings.Contains(err.Error(), "empty") {
		t.Errorf("Unexpected error message: %v", err)
	}

	// Whitespace-only input
	_, err = checkInput("   ")
	if err == nil {
		t.Fatal("Expected error for whitespace-only input")
	}
}

open System

let rec readMark (n: int) : float =
    printf "Enter marks for subject %d (0-100): " n
    match Double.TryParse(Console.ReadLine()) with
    | true, m when m >= 0.0 && m <= 100.0 -> m
    | _ ->
        printfn "Invalid input. Please enter a number between 0 and 100."
        readMark n

let division (percentage: float) : string =
    match percentage with
    | p when p >= 60.0 -> "First Division"
    | p when p >= 50.0 -> "Second Division"
    | p when p >= 40.0 -> "Third Division"
    | _ -> "Fail"

let marks = [ 1 .. 4 ] |> List.map readMark

let total = List.sum marks
let percentage = total / 4.0   // each subject is out of 100

printfn "\nTotal marks : %.2f / 400" total
printfn "Percentage  : %.2f%%" percentage
printfn "Result      : %s" (division percentage)
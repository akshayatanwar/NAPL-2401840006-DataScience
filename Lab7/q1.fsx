
open System

let task1 =
    async {
        printfn "Task 1 started"
        do! Async.Sleep(2000)
        printfn "Task 1 completed"
        return "Result from Task 1"
    }

let task2 =
    async {
        printfn "Task 2 started"
        do! Async.Sleep(1000)
        printfn "Task 2 completed"
        return "Result from Task 2"
    }

let task3 =
    async {
        printfn "Task 3 started"
        do! Async.Sleep(1500)
        printfn "Task 3 completed"
        return "Result from Task 3"
    }


let tasks = [| task1; task2; task3 |]

let results =
    tasks
    |> Async.Parallel
    |> Async.RunSynchronously


printfn "\nAll tasks completed!"
printfn "Collected Results:"

results
|> Array.iter (printfn "%s")

printfn "\nProgram finished."

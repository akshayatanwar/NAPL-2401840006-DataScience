
let isPrime (n: int) : bool =
    let rec hasNoDivisor d =
        if d * d > n then true            
        elif n % d = 0 then false         
        else hasNoDivisor (d + 1)        
    n > 1 && hasNoDivisor 2
let testSets =
    [ [ 2; 3; 4; 5 ]
      [ 1; 0; -7; 9 ]
      [ 17; 25; 97; 100 ] ]

testSets
|> List.iteri (fun i set ->
    printfn "Test set %d:" (i + 1)
    set
    |> List.iter (fun n ->
        printfn "  %d -> %s" n (if isPrime n then "Prime" else "Not prime")))
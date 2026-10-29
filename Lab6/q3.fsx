open System
open System.Text.RegularExpressions

let reverse (s: string) : string =
    s |> Seq.rev |> Seq.toArray |> String

let isPalindrome (s: string) : bool =
    let cleaned =
        s.ToLower()
        |> Seq.filter Char.IsLetterOrDigit
        |> Seq.toArray
        |> String
    cleaned = reverse cleaned

let transform (s: string) : string =
    match s with
    | _ when s <> "" && Seq.forall Char.IsLetter s -> s.ToUpper()
    | _ when s <> "" && Seq.forall Char.IsDigit s -> (bigint.Parse s * 2I).ToString()
    | _ -> s

let (|ValidEmail|InvalidEmail|) (s: string) =
    if Regex.IsMatch(s, @"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$")
    then ValidEmail
    else InvalidEmail

let checkEmail (s: string) : string =
    match s with
    | ValidEmail -> "Valid email"
    | InvalidEmail -> "Invalid email"

printfn "=== Reverse and Palindrome ==="
[ "madam"; "hello"; "A man, a plan, a canal: Panama" ]
|> List.iter (fun s ->
    printfn "\"%s\" -> reversed: \"%s\", palindrome: %b" s (reverse s) (isPalindrome s))

printfn "\n=== Transform ==="
[ "hello"; "1234"; "abc123" ]
|> List.iter (fun s -> printfn "\"%s\" -> \"%s\"" s (transform s))

printfn "\n=== Email Validation ==="
[ "student@example.com"; "john.doe@college.edu.in"; "not-an-email@" ]
|> List.iter (fun s -> printfn "\"%s\" -> %s" s (checkEmail s))
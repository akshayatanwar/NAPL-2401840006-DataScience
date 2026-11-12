
[<AbstractClass>]
type Shape() =
    abstract member Area : unit -> float

type Circle(radius: float) =
    inherit Shape()

    override this.Area() =
        3.14 * radius * radius

type Rectangle(length: float, breadth: float) =
    inherit Shape()

    override this.Area() =
        length * breadth

let circle = Circle(5.0)
let rectangle = Rectangle(10.0, 4.0)

printfn "Circle Area: %f" (circle.Area())
printfn "Rectangle Area: %f" (rectangle.Area())

let shapes : Shape list =
    [circle :> Shape; rectangle :> Shape]

printfn "\nPolymorphism:"

for shape in shapes do
    printfn "Area: %f" (shape.Area())

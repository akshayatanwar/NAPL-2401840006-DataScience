#r "nuget: Microsoft.Data.Sqlite"
#r "nuget: MongoDB.Driver"

open System
open Microsoft.Data.Sqlite
open MongoDB.Bson.Serialization.Attributes
open MongoDB.Driver

type Student = {
    Id: int
    Name: string
    Grade: string
}

module SqliteCrud =
    let connectionString = "Data Source=students.db"

    let printStudents (conn: SqliteConnection) (step: string) =
        printfn "\n[SQLite] --- State after %s ---" step
        use cmd = conn.CreateCommand()
        cmd.CommandText <- "SELECT Id, Name, Grade FROM Students"
        use reader = cmd.ExecuteReader()
        
        let mutable hasRows = false
        while reader.Read() do
            hasRows <- true
            printfn "Id: %d | Name: %s | Grade: %s" (reader.GetInt32(0)) (reader.GetString(1)) (reader.GetString(2))
        
        if not hasRows then printfn "(No records found)"

    let run () =
        printfn "\n================ RUNNING SQLITE CRUD ================"
        use conn = new SqliteConnection(connectionString)
        conn.Open()

        use cmd = conn.CreateCommand()
        cmd.CommandText <- "DROP TABLE IF EXISTS Students; CREATE TABLE Students (Id INTEGER PRIMARY KEY, Name TEXT, Grade TEXT);"
        cmd.ExecuteNonQuery() |> ignore

        let executeQuery sql (id: int) (optName: string option) (optGrade: string option) =
            use c = conn.CreateCommand()
            c.CommandText <- sql
            c.Parameters.AddWithValue("@id", id) |> ignore
            match optName with
            | Some n -> c.Parameters.AddWithValue("@name", n) |> ignore
            | None -> c.Parameters.AddWithValue("@name", DBNull.Value) |> ignore
            match optGrade with
            | Some g -> c.Parameters.AddWithValue("@grade", g) |> ignore
            | None -> c.Parameters.AddWithValue("@grade", DBNull.Value) |> ignore
            c.ExecuteNonQuery() |> ignore

        executeQuery "INSERT INTO Students (Id, Name, Grade) VALUES (@id, @name, @grade)" 101 (Some "Aarav Sharma") (Some "A")
        executeQuery "INSERT INTO Students (Id, Name, Grade) VALUES (@id, @name, @grade)" 102 (Some "Priya Patel") (Some "B")
        printStudents conn "CREATE"

        printfn "\n[SQLite] --- READ Operation (Id = 101) ---"
        use readCmd = conn.CreateCommand()
        readCmd.CommandText <- "SELECT Id, Name, Grade FROM Students WHERE Id = 101"
        use reader = readCmd.ExecuteReader()
        if reader.Read() then
            printfn "Found: Id: %d, Name: %s, Grade: %s" (reader.GetInt32(0)) (reader.GetString(1)) (reader.GetString(2))

        use updateCmd = conn.CreateCommand()
        updateCmd.CommandText <- "UPDATE Students SET Grade = 'A+' WHERE Id = 102"
        updateCmd.ExecuteNonQuery() |> ignore
        printStudents conn "UPDATE (Id = 102)"

        use deleteCmd = conn.CreateCommand()
        deleteCmd.CommandText <- "DELETE FROM Students WHERE Id = 101"
        deleteCmd.ExecuteNonQuery() |> ignore
        printStudents conn "DELETE (Id = 101)"

module MongoCrud =
    [<CLIMutable>]
    type MongoStudent = {
        [<BsonId>]
        Id: int
        Name: string
        Grade: string
    }

    let printStudents (collection: IMongoCollection<MongoStudent>) (step: string) =
        printfn "\n[MongoDB] --- State after %s ---" step
        let students = collection.Find(Builders<MongoStudent>.Filter.Empty).ToList()
        if students.Count = 0 then
            printfn "(No records found)"
        else
            for s in students do
                printfn "Id: %d | Name: %s | Grade: %s" s.Id s.Name s.Grade

    let run () =
        printfn "\n================ RUNNING MONGODB CRUD ================"
        let client = MongoClient("mongodb://localhost:27017")
        let database = client.GetDatabase("UniversityDB")
        let collection = database.GetCollection<MongoStudent>("Students")

        collection.DeleteMany(Builders<MongoStudent>.Filter.Empty) |> ignore
        printStudents collection "Initialization"

        collection.InsertOne({ Id = 101; Name = "Aarav Sharma"; Grade = "A" })
        collection.InsertOne({ Id = 102; Name = "Priya Patel"; Grade = "B" })
        printStudents collection "CREATE"

        printfn "\n[MongoDB] --- READ Operation (Id = 101) ---"
        let filter = Builders<MongoStudent>.Filter.Eq((fun s -> s.Id), 101)
        let result = collection.Find(filter).FirstOrDefault()
        if box result <> null then
            printfn "Found: Id = %d, Name = %s, Grade = %s" result.Id result.Name result.Grade

        let updateFilter = Builders<MongoStudent>.Filter.Eq((fun s -> s.Id), 102)
        let updateDef = Builders<MongoStudent>.Update.Set((fun s -> s.Grade), "A+")
        collection.UpdateOne(updateFilter, updateDef) |> ignore
        printStudents collection "UPDATE (Id = 102)"

        let deleteFilter = Builders<MongoStudent>.Filter.Eq((fun s -> s.Id), 101)
        collection.DeleteOne(deleteFilter) |> ignore
        printStudents collection "DELETE (Id = 101)"

SqliteCrud.run()
MongoCrud.run()
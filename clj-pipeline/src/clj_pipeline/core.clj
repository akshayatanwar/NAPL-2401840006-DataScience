(ns clj-pipeline.core
  (:require [next.jdbc :as jdbc]
            [ring.adapter.jetty :refer [run-jetty]]))

(def ds (jdbc/get-datasource {:dbtype "sqlite" :dbname "students.db"}))

(def students
  [{:id 1 :name "Akshaya"  :marks 78}
   {:id 2 :name "Riya"  :marks 35}
   {:id 3 :name "Miraj" :marks 91}
   {:id 4 :name "Nikita"  :marks 52}])

(def passed (filter #(>= (:marks %) 40) students))

(def total  (reduce + (map :marks passed)))

(defn create-table []
  (try
    (jdbc/execute! ds ["CREATE TABLE IF NOT EXISTS students
                        (id INTEGER PRIMARY KEY, name TEXT, marks INTEGER)"])
    (catch Exception e (println "Error:" (.getMessage e)))))

(defn save-students []
  (try
    (doseq [s passed]
      (jdbc/execute! ds ["INSERT OR REPLACE INTO students (id, name, marks) VALUES (?, ?, ?)"
                         (:id s) (:name s) (:marks s)]))
    "Saved to database"
    (catch Exception e (str "Error: " (.getMessage e)))))

(defn get-students []
  (try
    (pr-str (jdbc/execute! ds ["SELECT * FROM students"]))
    (catch Exception e (str "Error: " (.getMessage e)))))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (case (:uri request)
           "/save"     (save-students)
           "/students" (get-students)
           "/total"    (str "Total marks: " total) 
           "Try /save, /students, or /total")})

(defn -main [& args]
  (create-table)
  (println "Server running at http://localhost:3000")
  (run-jetty handler {:port 3000}))
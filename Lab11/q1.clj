(ns java-interop
  (:import [java.util ArrayList HashMap]))

;; 1. Calling Java Static Methods
(println "Absolute value:" (Math/abs -25))
(println "Square root:" (Math/sqrt 64))
(println "Current time:" (System/currentTimeMillis))

;; 2. Creating Java Objects
(def al (new ArrayList))
(def hm (new HashMap))

;; 3. Invoking Instance Methods
(.add al "Apple")
(.add al "Banana")
(.add al "Mango")

(.put hm "Name" "Rahul")
(.put hm "Age" 20)

(println "\nJava ArrayList:" al)
(println "Java HashMap:" hm)

(println "ArrayList size:" (.size al))
(println "HashMap name:" (.get hm "Name"))

;; 4. Converting Java and Clojure Collections

;; Java ArrayList to Clojure Vector
(def clj-vector (vec al))
(println "\nClojure Vector:" clj-vector)

;; Clojure List to Java ArrayList
(def clj-list '(10 20 30))
(def java-list (java.util.ArrayList. clj-list))
(println "Java List:" java-list)

;; Java HashMap to Clojure Map
(def clj-map (into {} hm))
(println "Clojure Map:" clj-map)

;; Clojure Map to Java HashMap
(def java-map (java.util.HashMap. clj-map))
(println "Java Map:" java-map)
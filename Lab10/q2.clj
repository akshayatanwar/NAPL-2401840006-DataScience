(def coll1 [1 2 3 4 5 6 7 8])
(def coll2 '(2 4 6 8 10 12))

(defn process-sequence [c]
  (let [m (map #(* % %) c)
        f (filter even? c)
        r (reduce + c)
        t (take 4 (drop 1 c))
        th (->> c
                (filter odd?)
                (map #(* % 10))
                (take 3))]
    (println "Original Collection:" c)
    (println "Map (Square):" m)
    (println "Filter (Even):" f)
    (println "Reduce (Sum):" r)
    (println "Take & Drop:" t)
    (println "Threading Macro Chain:" th)
    (println "-----------------------------------")))

(process-sequence coll1)
(process-sequence coll2)
(defproject clj-pipeline "0.1.0"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [com.github.seancorfield/next.jdbc "1.3.939"]
                 [org.xerial/sqlite-jdbc "3.45.3.0"]]
  :main clj-pipeline.core)
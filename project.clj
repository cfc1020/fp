(defproject laba1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.6.0"]
                  [org.clojure/math.numeric-tower "0.0.4"]
                  [clojure-csv/clojure-csv "2.0.1"]
                ]
  :main ^:skip-aot laba1.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

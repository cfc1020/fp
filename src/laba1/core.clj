(ns laba1.core
  (:require [clojure.string :as string]
            [clojure.math.numeric-tower :as math]
            [clojure-csv.core :as csv])
  (:gen-class))

(deftype ClObject [attributes potential])

(defn euclidean_distance
  [a b]
  (math/sqrt (reduce + (map #(math/expt (- %1 %2) 2) a b))))

(defn parse_file
  [in_file]
  (let [data (csv/parse-csv (slurp in_file))]
    (map
      (fn [attributes_list]
        (let [list_without_attributes
          (if (= (re-find #"\D+" (last attributes_list)) nil)
            attributes_list
            (butlast attributes_list))]
          (map #(Float/parseFloat %) list_without_attributes)))
      data)))

(defn adjust_potentials
  [points_list new_core betta distance_function]
  (map
    (fn 
      [point]
      (new ClObject
        (.attributes point)
        (-
          (.potential point)
          (*
            (.potential new_core)
            (let [distance (distance_function (.attributes point) (.attributes new_core))]
              (Math/exp (* (- betta) (math/expt distance 2))))))))
    points_list))

(defn calculate_potential
  [point1 point2 alpha distance_function]
  (let [distance (distance_function point1 point2)]
    (Math/exp (* (- alpha) (math/expt distance 2)))))

(defn points_with_init_potentials
  [points_list alpha distance_function]
  (map
    (fn [point]
      (new ClObject
        point
        (reduce +
          (map
            (fn [another_point]
              (calculate_potential point another_point alpha distance_function))
            points_list))))
    points_list))

(defn estimate_of_cores
  [points_list distance_function]
  (let [ra 1.0
        rb (* ra 1.5)
        eps_below 0.5
        eps_ebove 0.15
        alpha (/ 4 (math/expt ra 2))
        betta (/ 4 (math/expt rb 2))]
    (loop [points_with_potentials (points_with_init_potentials points_list alpha distance_function)
           first_core_potential nil
           founded_cores []]
      (let [
             [core_candidate_index core_candidate]
               (apply max-key #(.potential (second %)) (map-indexed vector points_with_potentials))
           ]
        (if (= first_core_potential nil)
          ; then
          (recur
            (adjust_potentials points_with_potentials core_candidate betta distance_function)
            (.potential core_candidate)
            (cons core_candidate_index founded_cores))
          ; else
          (if (> (.potential core_candidate) (* eps_below first_core_potential))
            ; then
            (recur
              (adjust_potentials points_with_potentials core_candidate betta distance_function)
              first_core_potential
              (cons core_candidate_index founded_cores))
            ; else
            (if (> (.potential core_candidate) (* eps_ebove first_core_potential))
              ; then
              (let [dmin (apply min (map #(distance_function (.attributes core_candidate) (.attributes (nth points_with_potentials %))) founded_cores))]
                (if (>= (+ (/ dmin ra) (/ (.potential core_candidate) first_core_potential)) 1)
                  ; then
                  (recur
                    (adjust_potentials points_with_potentials core_candidate betta distance_function)
                    first_core_potential
                    (cons core_candidate_index founded_cores))
                  ; else
                  (recur
                    (assoc (apply vector points_with_potentials) core_candidate_index (new ClObject (.attributes core_candidate) 0))
                    first_core_potential
                    founded_cores)))
              ; else
              founded_cores)))))))

(defn -main
  [in_file]
  (print 
    (str (string/join " , "
      (estimate_of_cores (parse_file in_file) euclidean_distance)) 
    "\n")))

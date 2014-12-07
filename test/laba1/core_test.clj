(ns laba1.core-test
  (:require [clojure.test :refer :all]
            [laba1.core :refer :all]))

(deftest euclidean_distance_test
  (testing
    (is (= (euclidean_distance [1, 1] [1, 2]) 1))
    (is (= (euclidean_distance [0, 1] [0, 5]) 4))))

(deftest hamming_distance_test
  (testing
    (is (= (hamming_distance [1, 1] [1, 2]) 1))
    (is (= (hamming_distance [0, 1] [2, 3]) 2))))

(deftest manhattan_distance_test
  (testing
    (is (= (manhattan_distance [1, 1] [1, 2]) 1))
    (is (= (manhattan_distance [0, 1] [2, 3]) 4))))



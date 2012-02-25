(ns BWT-Clojure.core-spec
  (:use
   [speclj.core]
   [BWT-Clojure.core]))

(describe "Truth"

          (it "is true"
              (should true))
          (it "is true"
              (should true))

          (it "is not false"
                  (should-not false)))


(run-specs)
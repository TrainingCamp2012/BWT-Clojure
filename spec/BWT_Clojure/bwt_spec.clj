(ns BWT-Clojure.bwt-spec
  (:use [speclj.core]
        [BWT-Clojure.bwt]))

(describe "Truth"
          (it "is true"
              (should true))
          (it "is not false"
              (should-not false)))
(run-specs)

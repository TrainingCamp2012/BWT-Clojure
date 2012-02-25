(ns BWT-Clojure.bwt-spec
  (:use [speclj.core]
        [BWT-Clojure.bwt]))


(describe "BWT-Clojure.bwt"
          (it "text-prep basic, add $ in the string end"
              (should (= "abracadabra$" (text-prep "abracadabra"))))
          (it "text-prep , "
              (should (= "$" (text-prep ""))))
          (it "text-prep, upper-case to lower-case"
              (should (= "abra$" (text-prep "ABRA"))))
          (it "text-prep, gsub space into _ "
              (should (= "ab_ra$" (text-prep "ab ra"))))

          (it "bwt-encode, "
              (should (= '(\a \r \d \$ \r \c \a \a \a \a \b \b)
                         (bwt-encode (text-prep "abracadabra")))))

          (it "bwt-decode, n = 0"
              (should (= "$abracadabra"
                         (apply str (bwt-decode (bwt-encode (text-prep "abracadabra")) 0)))))
          (it "bwt-decode, n = 1"
              (should (= "a"
                         (apply str (bwt-decode (bwt-encode (text-prep "abracadabra")) 1)))))
          (it "bwt-decode, n = 5"
              (should (= "adabra"
                         (apply str (bwt-decode (bwt-encode (text-prep "abracadabra")) 5)))))

          (it "sa"
              (should (= '((\$ 3) (\a 0) (\a 6) (\a 7) (\a 8) (\a 9) (\b 10) (\b 11) (\c 5) (\d 2) (\r 1) (\r 4))
                         (sa (bwt-encode (text-prep "abracadabra"))))))

          (it "bwt-find-index"
              (should (= '(0 8)
                         (bwt-find-index (bwt-encode (text-prep "abracadabra")) "ra"))))
          (it "bwt-search, search query 'ra'"
              (should (= '(9 2)
                         (bwt-search (bwt-encode (text-prep "abracadabra")) "ra"))))
          (it "bwt-search, query-head 'ab'"
              (should (= '(7 0)
                         (bwt-search (bwt-encode (text-prep "abracadabra")) "ab"))))
          (it "bwt-search, query-end 'dabra'"
              (should (= '(6)
                         (bwt-search (bwt-encode (text-prep "abracadabra")) "dabra"))))
          
          (it "is not false"
              (should-not false)))

(run-specs)

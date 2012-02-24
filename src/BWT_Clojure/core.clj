(ns BWT-Clojure.core
  (:use BWT-Clojure.bwt))


;;; test data
(def text "abracadabra")
(def iliad-head "Sing, O goddess, the anger of Achilles son of Peleus, that brought
countless ills upon the Achaeans. Many a brave soul did it send hurrying
down to Hades, and many a hero did it yield a prey to dogs and vultures,
for so were the counsels of Jove fulfilled from the day on which the
son of Atreus, king of men, and great Achilles, first fell out with
one another.")
(def iliad100 (slurp "/Users/yag_ays/dev/TrainingCamp2012/iliad.100.txt"))


(bwt-search (bwt-encode (text-prep text)) "ra")
(bwt-search (bwt-encode (text-prep iliad-head)) "it")
(apply str (bwt-encode (text-prep iliad-head)))

(bwt-search (bwt-encode (text-prep iliad100)) "it")
(def iliad100.encoded (bwt-encode (text-prep iliad100)))
(bwt-search iliad100.encoded "it")


(defn -main [& args]
  )

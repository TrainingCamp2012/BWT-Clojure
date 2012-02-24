(use '[clojure.contrib.str-utils :only (re-gsub)])

(defn text-prep [s]
  (str (re-gsub #" " "_" s) "$"))

(defn bwt-encode [s]
  (map last (sort (map #(apply str %) (take (count s) (partition (count s) 1 (cycle s)))))))

(defn bwt-decode [l]
  (let [sl (sort #(compare (first %1) (first %2)) (map list l (iterate inc 0)))]
    (loop [result [] n 0]
                 (if (zero? (last (nth sl n)))
                   (conj result (first (nth sl n)))
                   (recur (conj result (first (nth sl n))) (last (nth sl n)))))))

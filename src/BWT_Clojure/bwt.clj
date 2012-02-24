(use '[clojure.contrib.str-utils :only (re-gsub)])

(defn text-prep [s]
  (str (re-gsub #" " "_" s) "$"))

(defn bwt-encode [s]
  (map last (sort (map #(apply str %) (take (count s) (partition (count s) 1 (cycle s)))))))

(defn sa [s]
  (sort #(compare (first %1) (first %2)) (map list s (iterate inc 0))))

(defn bwt-decode [l]
  (let [sl (sort #(compare (first %1) (first %2)) (map list l (iterate inc 0)))]
    (loop [result [] n 0]
      (if (zero? (last (nth sl n)))
        (conj result (first (nth sl n)))
        (recur (conj result (first (nth sl n))) (last (nth sl n)))))))

(defn bwt-decode [l n]
  (let [sl (sort #(compare (first %1) (first %2)) (map list l (iterate inc 0)))
        index n]
    (loop [result [] n index]
      (if (zero? (last (nth sl n)))
        (conj result (first (nth sl n)))
        (recur (conj result (first (nth sl n))) (last (nth sl n)))))))

(defn bwt-find-index [bwt query]
  (map last (first (let [arr (sa bwt)]
                     (loop [f arr
                            q query
                            result '()]
                       (if (empty? q)
                         result
                         (recur (map #(nth arr (last %)) (filter #(= (first q) (first %)) f))
                                (rest q)
                                (if (empty? (filter #(= (first q) (first %)) f))
                                  nil
                                  (conj result (filter #(= (first q) (first %)) f))))))))))

(defn bwt-search [bwt query]
  (map #(- (dec (count bwt)) (if (= (count bwt) (count %)) 0 (count %)) (count query))
       (map #(bwt-decode bwt %) (bwt-find-index bwt query))))


;; (def text "abracadabra")

;; (bwt-decode (bwt-encode (text-prep text)) 0)
;; [\$ \a \b \r \a \c \a \d \a \b \r \a]

;; (sa (bwt-encode (text-prep text)))
;; ((\$ 3) (\a 0) (\a 6) (\a 7) (\a 8) (\a 9) (\b 10) (\b 11) (\c 5) (\d 2) (\r 1) (\r 4))

;; (bwt-find-index (bwt-encode (text-prep text)) "ra")
;; (0 8)

;; (bwt-search (bwt-encode (text-prep text)) "ra")
;; (9 2)

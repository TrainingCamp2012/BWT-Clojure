(ns BWT-Clojure.bwt
  (:use [clojure.contrib.str-utils :only (re-gsub)])
  (:use [clojure.contrib.string :only (lower-case)]))

(defn text-prep [s]
  (str (re-gsub #" " "_" (lower-case s)) "$"))

(defn bwt-encode [s]
  (map last (sort (map #(apply str %) (take (count s) (partition (count s) 1 (cycle s)))))))
;; (defn bwt-encode-p [s]
;;   (pmap last (sort (pmap #(apply str %) (take (count s) (partition (count s) 1 (cycle s)))))))

(defn bwt-decode [l n]
  (let [sl (sort #(compare (first %1) (first %2)) (map list l (iterate inc 0)))
        index n]
    (loop [result [] n index]
      (if (zero? (last (nth sl n)))
        (conj result (first (nth sl n)))
                (recur (conj result (first (nth sl n))) (last (nth sl n)))))))

(defn sa [s]
  (sort #(compare (first %1) (first %2)) (map list s (iterate inc 0))))

;; LF-mapping (maybe...)
;; need huge heap size(insufficient memory)
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
  (let [q (re-gsub #" " "_" (lower-case query))]
    (map #(- (dec (count bwt)) (if (= (count bwt) (count %)) 0 (count %)) (count q))
         (map #(bwt-decode bwt %) (bwt-find-index bwt q)))))

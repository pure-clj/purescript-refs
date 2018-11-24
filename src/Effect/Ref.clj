(ns Effect.Ref._foreign
  (:refer-clojure :exclude [read]))

(defn new [val]
  (fn [& _]
    (atom val)))

(defn read [ref]
  (fn [& _]
    @ref))

(defn modify' [f]
  (fn [ref]
    (fn [& _]
      (let [t (f @ref)]
        (reset! ref (get t "state"))
        (get t "value")))))

(defn write [val]
  (fn [ref]
    (fn [& _]
      (reset! ref val)
      {})))

(ns Effect.Ref._foreign
  (:refer-clojure :exclude [read]))

(defn new [val]
  (fn []
    (atom val)))

(defn read [ref]
  (fn []
    @ref))

(defn modify' [f]
  (fn [ref]
    (fn []
      (let [t (f @ref)]
        (reset! ref (get t "state"))
        (get t "value")))))

(defn write [val]
  (fn [ref]
    (fn []
      (reset! ref val)
      {})))

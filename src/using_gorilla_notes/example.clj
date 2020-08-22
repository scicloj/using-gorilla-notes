(ns using-gorilla-notes.example
  (:require [gorilla-notes.core :as gn]
            [gorilla-notes.intro :as intro]))

(defonce server (gn/start-server!))

;; Now you can browse the URL (gn/default-utl).
;; You are expected to see one note with an intro.

(println (gn/default-url))

(gn/browse-default-url)

;; Add a note:

(gn/add-note!
 [:div [:p "hello"]])

;; Reset all notes:

(gn/reset-notes!)

;; Add some notes:

(gn/add-note! [:div [:p (rand-int 999)]])
(gn/add-note! [:div [:p (rand-int 999)]])
(gn/add-note! [:div [:p (rand-int 999)]])

;; Change one of the notes:

(gn/assoc-note! 1 [:div [:p (rand-int 999)]])

;; Reset all notes again:

(gn/reset-notes!)

;; Add the original intro note:

(gn/add-note! intro/note)

;; Add more notes:
;; (most examples are taken from the gorilla-ui repo.)

(gn/add-note!
 [:p/code {:code "(+ 1 2)"}])

(gn/add-note!
 [:p/vega
  {:$schema     "https://vega.github.io/schema/vega-lite/v4.json"
   :description "A scatter plot."
   :data        {:values (for [i (range 4)]
                           {:x i
                            :y (+ i (* i 4 (rand-int 5)))})}
   :mark        :point
   :encoding    {:x {:field :x :type :quantitative}
                 :y {:field :y :type :quantitative}}}])

(gn/add-note!
 [:p/sparklinespot {:data (repeatedly 30 (partial rand-int 9)) :limit 100 :svgWidth 100 :svgHeight 20 :margin 1}])

(gn/add-note!
 [:p/clock])

(gn/add-note!
 [:div {:style {:width 100}}
  [:link {:rel  "stylesheet"
          :href "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"}]
  [:p/leaflet
  [{:type :view :center [51.49, -0.08] :zoom 12 :height 600 :width 700}
   {:type :rectangle :bounds [[51.49, -0.08]
                              [51.5, -0.06]]}
   {:type :circle :center [51.505, -0.09] :fillColor :blue :radius 200}
   {:type :polygon :positions [[51.515, -0.09]
                               [51.52, -0.1]
                               [51.52, -0.12]] :color :purple}
   {:type :polygon :positions [[[51.51, -0.12]
                                [51.51, -0.13]
                                [51.53, -0.13]]
                               [[51.51, -0.05]
                                [51.51, -0.07]
                                [51.53, -0.07]]] :color :purple}
   {:type :line :positions  [[51.505, -0.09]
                             [51.51, -0.1]
                             [51.51, -0.12]] :color :lime}
   {:type                         :line
    :positions                    [[[51.5, -0.1]
                                    [51.5, -0.12]
                                    [51.52, -0.12]]
                                   [[51.5, -0.05]
                                    [51.5, -0.06]
                                    [51.52, -0.06]]] :color :lime}
   {:type :marker :position [51.505, -0.09]}
   {:type :marker :position [51.51, -0.12] :popup "wow"}
   {:type :circlemarker :center [51.52, -0.06] :fillColor :blue :radius 200 :popup "square the circle"}]]])

(gn/add-note!
 [:p/player {:width  "100%"
             :height "100%"
             :url    "https://www.youtube.com/watch?v=Bs44qdAX5yo"}])

(gn/add-note!
 [:div
  [:p/frisk {:a             "I'm a string"
             :b             :imakeyword
             :c             [1 2 3]
             :d             '(1 2 3)
             :e             #{1 2 3}
             "g"            "String key"
             0              nil}]])

;; Modify rendering options
(gn/toggle-option! :header?)
(gn/toggle-option! :reverse-notes?)
(gn/toggle-option! :notes-in-cards?)
(gn/merge-new-options!
 {:reverse-notes? false
  :header? true
  :custom-header [:div "Hello" [:hr]]
  :custom-footer [:div [:hr] "Goodbye"]})

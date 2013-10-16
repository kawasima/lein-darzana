(ns {{name}}.core
  (:use
    [darzana.core :as darzana]
    [darzana.api])
  (:require
    [compojure.route :as route]
    [compojure.core :as compojure]
    [compojure.handler :as handler]
    [ring.middleware.reload :as reload]))

(def app
  (do
    (darzana.core/set-application-scope
      { :consumer-key nil     ;; Set your twitter consumer key.
        :consumer-secret nil  ;; Set your twitter consumer secret.
        })
    (dosync
      (alter workspace/config assoc :initial-resources "resources"))
    (load-file "resources/api.clj")
    (workspace/change-workspace "master")
    (reload/wrap-reload
      (->
        (handler/site
          (compojure/routes
            (darzana/load-routes)
            (compojure/context "/admin" [] darzana/admin-routes)
            (route/resources "/")))))))

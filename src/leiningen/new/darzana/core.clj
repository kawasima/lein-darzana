(ns {{name}}.core
  (:gen-class)
  (:use
    [taoensso.carmine.ring :only [carmine-store]]
    [org.httpkit.server :only [run-server]]
    [environ.core :only [env]]
    [darzana.api])
  (:require
    [compojure.route :as route]
    [compojure.core :as compojure]
    [compojure.handler :as handler]
    [ring.middleware.reload :as reload]
    [darzana.core :as darzana]
    [darzana.kvs :as kvs]
    [darzana.workspace :as workspace]
    [darzana.admin.core :as admin]))

(defn create-app []
  (darzana/set-application-scope
    { :consumer-key nil     ;; Set your twitter consumer key.
      :consumer-secret nil  ;; Set your twitter consumer secret.
      })
  (swap! workspace/config assoc :initial-resources "resources")
  (load-file "resources/api.clj")
  (workspace/change-workspace "master")
  (handler/site
    (compojure/routes
      (darzana/load-routes)
      (compojure/context "/admin" [] admin/admin-routes)
      (route/resources "/"))
    {:session { :store (carmine-store kvs/redis-connection)}}))

(def app (reload/wrap-reload (create-app)))

(defn -main [& args]
  (run-server (create-app) {:port (or (env :port) 3000)}))

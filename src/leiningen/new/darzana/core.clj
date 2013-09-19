(ns {{name}}.core
  (:use
    [darzana.core :as darzana]
    [darzana.api :only (defapi expire url query-keys method basic-auth oauth-token)])
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
        :q  "clojure"
        :count 10
        :grant_type "client_credentials"})
    (ns {{name}}.core)
    (load-file "resources/api.clj")
    (darzana/add-routes "routes")
    (darzana/load-routes)
    (reload/wrap-reload (->
                          (handler/site (compojure/routes
                                          darzana/routes
                                          (compojure/context "/admin" [] darzana/admin-routes)
                                          (route/resources "/")))))))

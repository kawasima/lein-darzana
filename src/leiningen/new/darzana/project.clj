(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [ [org.clojure/clojure "1.5.1"]
                  [net.unit8/darzana "0.1.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler {{name}}.core/app})
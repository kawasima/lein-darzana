(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [ [org.clojure/clojure "1.5.1"]
                  [net.unit8/darzana "0.2.0-SNAPSHOT"]
                  [javax.servlet/servlet-api "2.5"]
                  [ring/ring-devel "1.2.1"]
                  [http-kit "2.1.17"]]
  :plugins [[lein-ring "0.8.10"]]

  :source-paths ["src/clj"]
  :test-paths   ["test/clj"]
  :resource-paths ["resources"]

  :ring {:handler {{name}}.core/app}
  :aot [{{name}}.core]
  :main {{name}}.core)

(ns leiningen.new.darzana
  (:use [leiningen.new.templates :only [renderer name-to-path year ->files]]))

(def render (renderer "darzana"))

(defn darzana
  "Create a new darzana application."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :year (year)}
        render #((renderer "darzana") % data)]
    (->files data
      [".gitignore"  (render "gitignore")]
      ["project.clj" (render "project.clj")]
      ["README.md"   (render "README.md")]             
      ["src/clj/{{sanitized}}/core.clj"    (render "core.clj")]
      ["resources/api.clj"             (render "api.clj")]
      ["resources/router/routes.clj"   (render "router.clj")]
      ["resources/template/layout.hbs" (render "layout.hbs")]
      ["resources/template/index.hbs"  (render "index.hbs")]
      ["resources/template/guide.hbs"  (render "guide.hbs")]
      "resources/public/js"
      "resources/public/img")))

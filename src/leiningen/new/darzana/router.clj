(defmarga GET "/"
  (if-contains :consumer-key
    (-> (if-contains :twitter-authorize
          (->)
          (call-api [my-api/twitter-authorize]))
      (call-api [my-api/twitter-timeline])
      (store-session :twitter-authorize)
      (render "index"))
    (render "guide")))

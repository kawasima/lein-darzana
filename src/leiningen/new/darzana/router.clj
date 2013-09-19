(defmarga GET "/"
  (if-contains :consumer-key
    (-> (if-contains :twitter-authorize
          (->)
          (call-api [twitter-authorize]))
      (call-api [twitter-timeline])
      (store-session :twitter-authorize [:twitter-authorize])  
      (render "index"))
    (render "guide")))

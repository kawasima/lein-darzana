(defapi twitter-authorize
  (url "https://api.twitter.com/oauth2/token")
  (query-keys
    (assign "client_credentials" => :grant_type))
  (basic-auth :consumer-key :consumer-secret)
  (method :post))

(defapi twitter-timeline
  (url "https://api.twitter.com/1.1/search/tweets.json")
  (query-keys
    (assign "clojure" => :q)
    (assign 10 => :count))
  (expire 300)
  (oauth-token [:twitter-authorize :access_token]))


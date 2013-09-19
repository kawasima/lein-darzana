(defapi twitter-authorize
  (url "https://api.twitter.com/oauth2/token")
  (query-keys :grant_type)
  (basic-auth :consumer-key :consumer-secret)
  (method :post))

(defapi twitter-timeline
  (url "https://api.twitter.com/1.1/search/tweets.json")
  (query-keys :count :q)
  (expire 300)
  (oauth-token [:twitter-authorize :access_token]))


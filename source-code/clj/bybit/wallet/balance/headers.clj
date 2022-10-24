
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns bybit.wallet.balance.headers
    (:require [bybit.wallet.balance.uri :as wallet.balance.uri]
              [server-fruits.hash       :as hash]
              [time.api                 :as time]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn wallet-balance-param-string
  ; @param (map) param-props
  ;  {:api-key (string)
  ;   :coin (string)(opt)}
  ; @param (string) timestamp
  ;
  ; @return (string)
  [{:keys [api-key] :as param-props} timestamp]
  (let [query-string (wallet.balance.uri/wallet-balance-query-string param-props)]
       (str timestamp api-key 5000 query-string)))

(defn wallet-balance-headers
  ; @param (map) uri-props
  ;  {:api-key (string)
  ;   :api-secret (string)
  ;   :coin (string)(opt)
  ;   :use-mainnet? (boolean)(opt)
  ;    Default: false}
  ;
  ; @usage
  ;  (wallet-balance-headers {})
  ;
  ; @usage
  ;  (wallet-balance-headers {:use-mainnet? true})
  ;
  ; @return (map)
  ;  {"X-BAPI-SIGN-TYPE" (integer)
  ;   "X-BAPI-SIGN" (string)
  ;   "X-BAPI-API-KEY" (string)
  ;   "X-BAPI-TIMESTAMP" (string)
  ;   "X-BAPI-RECV-WINDOW" (integer)}
  [{:keys [api-key api-secret use-mainnet?] :as headers-props}]
  ; Please make sure that your timestamp is in sync with our (bybit.com) server time.
  ; You can use the Server Time endpoint.
  (let [timestamp    (time/epoch-ms)
        param-string (wallet-balance-param-string headers-props timestamp)
        sign         (hash/hmac-sha256 param-string api-secret)]
       {"X-BAPI-SIGN-TYPE"   2
        "X-BAPI-SIGN"        sign
        "X-BAPI-API-KEY"     api-key
        "X-BAPI-TIMESTAMP"   timestamp
        "X-BAPI-RECV-WINDOW" 5000}))

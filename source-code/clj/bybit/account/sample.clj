
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns bybit.account.sample
    (:require [bybit.api :as bybit]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn request-my-account-api-key!
  []
  (bybit/request-account-api-key! {:api-key "..." :api-secret "..."}))
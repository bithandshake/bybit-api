
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns bybit.wallet.sample
    (:require [bybit.api :as bybit]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn request-my-wallet-balance!
  []
  (bybit/request-wallet-balance! {:api-key "..." :api-secret "..."}))

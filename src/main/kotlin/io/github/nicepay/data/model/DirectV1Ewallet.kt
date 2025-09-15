package io.github.nicepay.data.model

import DirectV1Request

class DirectV1Ewallet private constructor(
    iMid: String?,
    timeStamp: String?,
    payMethod: String?,
    currency: String?,
    amt: String?,
    referenceNo: String?,
    goodsNm: String?,
    billingNm: String?,
    billingEmail: String?,
    billingPhone: String?,
    billingAddr: String?,
    billingCity: String?,
    billingState: String?,
    billingPostCd: String?,
    billingCountry: String?,
    callBackUrl: String?,
    dbProcessUrl: String?,
    description: String?,
    merchantToken: String?,
    userIP: String?,
    cartData: String?,
    deliveryNm: String?,
    deliveryPhone: String?,
    deliveryAddr: String?,
    deliveryCity: String?,
    deliveryState: String?,
    deliveryPostCd: String?,
    deliveryCountry: String?,
    vat: String?,
    fee: String?,
    notaxAmt: String?,
    reqDt: String?,
    reqTm: String?,
    reqDomain: String?,
    reqServerIP: String?,
    reqClientVer: String?,
    userSessionID: String?,
    userAgent: String?,
    userLanguage: String?,
//    Direct Debit
    val mitraCd:String?,
    val returnJsonFormat: String?,
    val paymentExpDt: String?,
    val paymentExpTm: String?,

) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1Ewallet, Builder>() {
        private var mitraCd: String? = null
        private var returnJsonFormat: String? = null
        private var paymentExpDt: String? = null
        private var paymentExpTm: String? = null


        fun mitraCd(value: String?) = apply { this.mitraCd = value }
        fun returnJsonFormat(value: String?) = apply { this.returnJsonFormat = value }
        fun paymentExpDt(value: String?) = apply { this.paymentExpDt = value }
        fun paymentExpTm(value: String?) = apply { this.paymentExpTm = value }

        override fun build(): DirectV1Ewallet {
            return DirectV1Ewallet(
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
                billingAddr, billingCity, billingState, billingPostCd, billingCountry,
                callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,
                mitraCd, returnJsonFormat, paymentExpDt, paymentExpTm
            )
        }
    }
}
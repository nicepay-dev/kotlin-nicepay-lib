package io.github.nicepay.data.model

import DirectV1Request

class DirectV1ConvenienceStore private constructor(
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
//    VA
    val mitraCd: String?,
    val payValidDt: String?,
    val payValidTm: String?,



//// Payment Optional Field

) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1ConvenienceStore, Builder>() {
        private var mitraCd: String? = null
        private var payValidDt: String? = null
        private var payValidTm: String? = null

        fun mitraCd(value: String?) = apply { this.mitraCd = value }
        fun payValidDt(value: String?) = apply { this.payValidDt = value }
        fun payValidTm(value: String?) = apply { this.payValidTm = value }

        override fun build(): DirectV1ConvenienceStore {
            return DirectV1ConvenienceStore(
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
                billingAddr, billingCity, billingState, billingPostCd, billingCountry,
                callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,
                mitraCd, payValidDt, payValidTm
            )
        }
    }
}
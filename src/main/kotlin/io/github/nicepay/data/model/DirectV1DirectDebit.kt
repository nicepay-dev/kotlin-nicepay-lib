package io.github.nicepay.data.model

import DirectV1Request

class DirectV1DirectDebit private constructor(
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
    val clickPayToken: String?,
    val clickPayNo: String?,
    val cashtag: String?

) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1DirectDebit, Builder>() {
        private var mitraCd: String? = null
        private var clickPayToken: String? = null
        private var clickPayNo: String? = null
        private var cashtag: String? = null

        fun mitraCd(value: String?) = apply { this.mitraCd = value }
        fun clickPayToken(value: String?) = apply { this.clickPayToken = value }
        fun clickPayNo(value: String?) = apply { this.clickPayNo = value }
        fun cashtag(value: String?) = apply { this.cashtag = value }

        override fun build(): DirectV1DirectDebit {
            return DirectV1DirectDebit(
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
                billingAddr, billingCity, billingState, billingPostCd, billingCountry,
                callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,
                mitraCd, clickPayToken, clickPayNo, cashtag
            )
        }
    }
}
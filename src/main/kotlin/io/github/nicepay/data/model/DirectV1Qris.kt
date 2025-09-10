package io.github.nicepay.data.model

import DirectV1Request

class DirectV1Qris private constructor(
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
    val shopId: String?,

) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1Qris, Builder>() {
        private var mitraCd: String? = null
        private var shopId: String? = null


        fun mitraCd(value: String?) = apply { this.mitraCd = value }
        fun shopId(value: String?) = apply { this.shopId = value }

        override fun build(): DirectV1Qris {
            return DirectV1Qris(
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
                billingAddr, billingCity, billingState, billingPostCd, billingCountry,
                callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,
                mitraCd, shopId
            )
        }
    }
}
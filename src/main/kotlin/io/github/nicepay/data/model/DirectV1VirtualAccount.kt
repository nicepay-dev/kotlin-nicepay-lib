package io.github.nicepay.data.model

import DirectV1Request

class DirectV1VirtualAccount private constructor(
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
    val bankCd: String?,
    val vacctValidDt: String?,
    val vacctValidTm: String?,
    val merFixAcctId: String?,



//// Payment Optional Field

) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1VirtualAccount, Builder>() {
        private var bankCd: String? = null
        private var vacctValidDt: String? = null
        private var vacctValidTm: String? = null
        private var merFixAcctId: String? = null

        fun bankCd(value: String?) = apply { this.bankCd = value }
        fun vacctValidDt(value: String?) = apply { this.vacctValidDt = value }
        fun vacctValidTm(value: String?) = apply { this.vacctValidTm = value }
        fun merFixAcctId(value: String?) = apply { this.merFixAcctId = value }

        override fun build(): DirectV1VirtualAccount {
            return DirectV1VirtualAccount(
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
                billingAddr, billingCity, billingState, billingPostCd, billingCountry,
                callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,
                bankCd, vacctValidDt, vacctValidTm, merFixAcctId
            )
        }
    }
}
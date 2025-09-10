package io.github.nicepay.data.model

import DirectV1Request

class DirectV1Card private constructor(
    // base fields
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

    // card-specific fields
    val instmntType: String?,
    val instmntMon: String?,
    val onePassToken: String?,
    val cardCvv: String?,
    val recurrOpt: String?,
    val cardNo: String?,
    val cardExpYymm: String?,
    val cardHolderNm: String?,
    val cardHolderEmail: String?,
    val country: String?,
    val recurringToken: String?,
    val preauthToken: String?
) : DirectV1Request(
    iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm, billingNm, billingEmail, billingPhone,
    billingAddr, billingCity, billingState, billingPostCd, billingCountry,
    callBackUrl, dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
    deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
    reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage
) {
    class Builder : DirectV1Request.Builder<DirectV1Card, Builder>() {
        private var instmntType: String? = null
        private var instmntMon: String? = null
        private var onePassToken: String? = null
        private var cardCvv: String? = null
        private var recurrOpt: String? = null
        private var cardNo: String? = null
        private var cardExpYymm: String? = null
        private var cardHolderNm: String? = null
        private var cardHolderEmail: String? = null
        private var country: String? = null
        private var recurringToken: String? = null
        private var preauthToken: String? = null


        fun instmntType(value: String?) = apply { this.instmntType = value }
        fun instmntMon(value: String?) = apply { this.instmntMon = value }
        fun onePassToken(value: String?) = apply { this.onePassToken = value }
        fun cardCvv(value: String?) = apply { this.cardCvv = value }
        fun recurrOpt(value: String?) = apply { this.recurrOpt = value }
        fun cardNo(value: String?) = apply { this.cardNo = value }
        fun cardExpYymm(value: String?) = apply { this.cardExpYymm = value }
        fun cardHolderNm(value: String?) = apply { this.cardHolderNm = value }
        fun cardHolderEmail(value: String?) = apply { this.cardHolderEmail = value }
        fun country(value: String?) = apply { this.country = value }
        fun recurringToken(value: String?) = apply { this.recurringToken = value }
        fun preauthToken(value: String?) = apply { this.preauthToken = value }

        override fun build(): DirectV1Card {
            return DirectV1Card(
                // base fields
                iMid, timeStamp, payMethod, currency, amt, referenceNo, goodsNm,
                billingNm, billingEmail, billingPhone, billingAddr, billingCity,
                billingState, billingPostCd, billingCountry, callBackUrl,
                dbProcessUrl, description, merchantToken, userIP, cartData, deliveryNm, deliveryPhone,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry, vat, fee, notaxAmt,
                reqDt, reqTm, reqDomain, reqServerIP, reqClientVer, userSessionID, userAgent, userLanguage,

                // card-specific fields
                instmntType, instmntMon, onePassToken, cardCvv, recurrOpt,
                cardNo, cardExpYymm, cardHolderNm, cardHolderEmail, country, recurringToken, preauthToken
            )
        }
    }
}

package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util
import io.github.nicepay.utils.code.NICEPayMethod

class DirectV2Card(
    private val timeStamp: String?,
    private val iMid: String?,
    private val payMethod: String?,
    private val currency: String?,
    private val merchantToken: String?,
    private val referenceNo: String?,

    private val dbProcessUrl: String?,

    private val instmntType: String?,
    private val instmntMon: String?,
    private val recurrOpt: String?,

    private val userIP: String?,
    private val userLanguage: String?,
    private val userAgent: String?,

    private val amt: String?,
    private val cartData: String?,
    private val goodsNm: String?,
    private val billingNm: String?,
    private val billingPhone: String?,
    private val billingEmail: String?,
    private val billingAddr: String?,
    private val billingCity: String?,
    private val billingState: String?,
    private val billingCountry: String?,
    private val billingPostCd: String?,

    // PAYMENT
    private val tXid: String?,

    private val cardNo: String?,
    private val cardExpYymm: String?,
    private val cardCvv: String?,
    private val cardHolderNm: String?,

    private val callBackUrl: String?,

    // For Recurring and Pre-auth feature
    private val recurringToken: String?,
    private val preauthToken: String?,
) {

    class Builder {
        private var timeStamp: String? = null
        private var iMid: String? = null
        private var payMethod: String? = null
        private var currency: String? = null
        private var referenceNo: String? = null
        private var dbProcessUrl: String? = null
        private var instmntType: String? = null
        private var instmntMon: String? = null
        private var recurrOpt: String? = null
        private var userIP: String? = null
        private var userLanguage: String? = null
        private var userAgent: String? = null
        private var amt: String? = null
        private var cartData: String? = null
        private var goodsNm: String? = null
        private var billingNm: String? = null
        private var billingPhone: String? = null
        private var billingEmail: String? = null
        private var billingAddr: String? = null
        private var billingCity: String? = null
        private var billingState: String? = null
        private var billingCountry: String? = null
        private var billingPostCd: String? = null
        private var merchantKey: String? = null
        private var tXid: String? = null
        private var cardNo: String? = null
        private var cardExpYymm: String? = null
        private var cardCvv: String? = null
        private var cardHolderNm: String? = null
        private var callBackUrl: String? = null
        private var recurringToken: String? = null
        private var preauthToken: String? = null

        // Builder methods
        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun payMethod(payMethod: String?) = apply { this.payMethod = payMethod }
        fun payMethod(payMethod: NICEPayMethod?) = apply { this.payMethod = payMethod?.code }
        fun currency(currency: String?) = apply { this.currency = currency }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun dbProcessUrl(dbProcessUrl: String?) = apply { this.dbProcessUrl = dbProcessUrl }
        fun instmntType(instmntType: String?) = apply { this.instmntType = instmntType }
        fun instmntMon(instmntMon: String?) = apply { this.instmntMon = instmntMon }
        fun recurrOpt(recurrOpt: String?) = apply { this.recurrOpt = recurrOpt }
        fun userIP(userIP: String?) = apply { this.userIP = userIP }
        fun userLanguage(userLanguage: String?) = apply { this.userLanguage = userLanguage }
        fun userAgent(userAgent: String?) = apply { this.userAgent = userAgent }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun cartData(cartData: String?) = apply { this.cartData = cartData }
        fun goodsNm(goodsNm: String?) = apply { this.goodsNm = goodsNm }
        fun billingNm(billingNm: String?) = apply { this.billingNm = billingNm }
        fun billingPhone(billingPhone: String?) = apply { this.billingPhone = billingPhone }
        fun billingEmail(billingEmail: String?) = apply { this.billingEmail = billingEmail }
        fun billingAddr(billingAddr: String?) = apply { this.billingAddr = billingAddr }
        fun billingCity(billingCity: String?) = apply { this.billingCity = billingCity }
        fun billingState(billingState: String?) = apply { this.billingState = billingState }
        fun billingCountry(billingCountry: String?) = apply { this.billingCountry = billingCountry }
        fun billingPostCd(billingPostCd: String?) = apply { this.billingPostCd = billingPostCd }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }
        fun tXid(tXid: String?) = apply { this.tXid = tXid }
        fun cardNo(cardNo: String?) = apply { this.cardNo = cardNo }
        fun cardExpYymm(cardExpYymm: String?) = apply { this.cardExpYymm = cardExpYymm }
        fun cardCvv(cardCvv: String?) = apply { this.cardCvv = cardCvv }
        fun cardHolderNm(cardHolderNm: String?) = apply { this.cardHolderNm = cardHolderNm }
        fun callBackUrl(callBackUrl: String?) = apply { this.callBackUrl = callBackUrl }
        fun recurringToken(recurringToken: String?) = apply { this.recurringToken = recurringToken }
        fun preauthToken(preauthToken: String?) = apply { this.preauthToken = preauthToken }

        fun build(): DirectV2Card {

            return DirectV2Card(
                timeStamp,
                iMid,
                payMethod,
                currency,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey)
                    .toString(),
                referenceNo,
                dbProcessUrl,
                instmntType,
                instmntMon,
                recurrOpt,
                userIP,
                userLanguage,
                userAgent,
                amt,
                cartData,
                goodsNm,
                billingNm,
                billingPhone,
                billingEmail,
                billingAddr,
                billingCity,
                billingState,
                billingCountry,
                billingPostCd,
                tXid,
                cardNo,
                cardExpYymm,
                cardCvv,
                cardHolderNm,
                callBackUrl,
                recurringToken,
                preauthToken
            )
        }
    }
}

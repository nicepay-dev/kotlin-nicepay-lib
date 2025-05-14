package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class DirectV2RequestPaymentCard(
    timeStamp: String?,
    tXid: String?,
    callBackUrl: String?,
    private val cardNo: String?,
    private val cardExpYymm: String?,
    private val cardCvv: String?,
    merchantToken: String?
) : DirectV2RequestPaymentEwallet(
    timeStamp, tXid, callBackUrl, merchantToken
) {

    override fun dataMap() : Map<String, String> {
        var result = HashMap<String, String>()

        result.put("timeStamp", this.timeStamp.toString())
        result.put("tXid", this.tXid.toString())
        result.put("callBackUrl", this.callBackUrl.toString())
        result.put("merchantToken", this.merchantToken.toString())
        result.put("cardNo", this.cardNo.toString())
        result.put("cardExpYymm", this.cardExpYymm.toString())
        result.put("cardCvv", this.cardCvv.toString())

        return result
    }

    class Builder() {

        private var timeStamp: String? = null
        private var callBakUrl: String? = null
        private var tXid: String? = null
        private var iMid: String? = null
        private var referenceNo: String? = null
        private var amt: String? = null
        private var merchantKey: String? = null
        private var cardNo: String? = null
        private var cardExpYymm: String? = null
        private var cardCvv: String? = null

        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun tXid(tXid: String?) = apply { this.tXid = tXid }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun callBakUrl(callBakUrl: String?) = apply { this.callBakUrl = callBakUrl }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }
        fun cardNo(cardNo: String?) = apply { this.cardNo = cardNo }
        fun cardExpYymm(cardExpYymm: String?) = apply { this.cardExpYymm = cardExpYymm }
        fun cardCvv(cardCvv: String?) = apply { this.cardCvv = cardCvv }

        fun build(): DirectV2RequestPaymentCard {
            return DirectV2RequestPaymentCard(
                timeStamp, tXid, callBakUrl, cardNo, cardExpYymm, cardCvv,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString()
            )
        }

    }

}
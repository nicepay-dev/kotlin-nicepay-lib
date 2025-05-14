package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

open class DirectV2RequestPaymentEwallet(
    val timeStamp: String?,
    val tXid: String?,
    val callBackUrl: String?,
    val merchantToken: String?
) {

    open fun dataMap() : Map<String, String> {
        var result = HashMap<String, String>()

        result.put("timeStamp", this.timeStamp.toString())
        result.put("tXid", this.tXid.toString())
        result.put("callBackUrl", this.callBackUrl.toString())
        result.put("merchantToken", this.merchantToken.toString())

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

        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun tXid(tXid: String?) = apply { this.tXid = tXid }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun callBakUrl(callBakUrl: String?) = apply { this.callBakUrl = callBakUrl }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }

        fun build(): DirectV2RequestPaymentEwallet {
            return DirectV2RequestPaymentEwallet(
                timeStamp, tXid, callBakUrl,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString()
            )
        }

    }

}
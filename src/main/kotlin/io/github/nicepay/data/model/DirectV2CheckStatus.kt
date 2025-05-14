package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class DirectV2CheckStatus(
    private val timeStamp: String?,
    private val tXid: String?,
    private val iMid: String?,
    private val referenceNo: String?,
    private val amt: String?,
    private val merchantToken: String?
) {

    class Builder {

        private var timeStamp: String? = null
        private var tXid: String? = null
        private var iMid: String? = null
        private var referenceNo: String? = null
        private var amt: String? = null
        private var merchantKey: String? = null

        fun timeStamp(timeStamp: String) = apply { this.timeStamp = timeStamp }
        fun tXid(tXid: String) = apply { this.tXid = tXid }
        fun iMid(iMid: String) = apply { this.iMid = iMid }
        fun referenceNo(referenceNo: String) = apply { this.referenceNo = referenceNo }
        fun amt(amt: String) = apply { this.amt = amt }
        fun merchantKey(merchantKey: String) = apply { this.merchantKey = merchantKey }

        fun build(): DirectV2CheckStatus {
            return DirectV2CheckStatus(
                timeStamp,
                tXid,
                iMid,
                referenceNo,
                amt,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString()
            )
        }
    }

}
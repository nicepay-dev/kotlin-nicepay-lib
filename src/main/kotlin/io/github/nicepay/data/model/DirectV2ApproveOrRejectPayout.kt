package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class DirectV2ApproveOrRejectPayout(
    private val timeStamp: String?,
    private val iMid: String?,
    private val tXid: String?,
    private val merchantToken: String?
) {

    class Builder {
        private var timeStamp: String? = null
        private var tXid: String? = null
        private var iMid: String? = null
        private var merchantKey: String? = null

        fun timeStamp(timeStamp: String) = apply { this.timeStamp = timeStamp }
        fun tXid(tXid: String) = apply { this.tXid = tXid }
        fun iMid(iMid: String) = apply { this.iMid = iMid }
        fun merchantKey(merchantKey: String) = apply { this.merchantKey = merchantKey }

        fun build(): DirectV2ApproveOrRejectPayout {
            return DirectV2ApproveOrRejectPayout(
                timeStamp,
                iMid,
                tXid,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.tXid + this.merchantKey).toString()
            )
        }

    }

}
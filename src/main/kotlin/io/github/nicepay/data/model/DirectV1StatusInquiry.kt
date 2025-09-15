package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class DirectV1StatusInquiry private constructor(
    val iMid:String?,
    val tXid:String?,
    val referenceNo: String?,
    val amt: String?,
    val merchantToken: String?,
) {
    class Builder () {
        private var iMid: String? = null
        private var tXid: String? = null
        private var referenceNo: String? = null
        private var amt: String? = null
        private var merchantToken: String? = null


        fun iMid(value: String?) = apply { this.iMid = value }
        fun tXid(value: String?) = apply { this.tXid = value }
        fun referenceNo(value: String?) = apply { this.referenceNo = value }
        fun amt(value: String?) = apply { this.amt = value }
        fun merchantToken(iMid: String?, referenceNo: String?, amt: String?, merchantKey: String?) = apply {
            this.merchantToken = SHA256Util.encrypt(iMid + referenceNo + amt + merchantKey).toString()
        }

        fun build(): DirectV1StatusInquiry {
            return DirectV1StatusInquiry(
                iMid, tXid, referenceNo, amt, merchantToken
            )
        }
    }
}
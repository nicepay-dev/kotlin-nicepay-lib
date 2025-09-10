package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class DirectV1Cancel private constructor(
    val iMid:String?,
    val tXid:String?,
    val referenceNo: String?,
    val amt: String?,
    val merchantToken: String?,
    val payMethod: String?,
    val cancelType: String?,

    val fee: String?,
    val vat: String?,
    val notaxAmt: String?,
    val reqServerIP: String?,
    val cancelMsg: String?,
    val cancelUserId: String?,
    val userIP: String?,
    val cancelUserInfo: String?,
    val cancelRetryCnt: String?,
    val worker: String?,

) {
    class Builder () {
        private var iMid: String? = null
        private var tXid: String? = null
        private var referenceNo: String? = null
        private var amt: String? = null
        private var merchantToken: String? = null
        private var payMethod: String? = null
        private var cancelType: String? = null
        private var fee: String? = null
        private var vat: String? = null
        private var notaxAmt: String? = null
        private var reqServerIP: String? = null
        private var cancelMsg: String? = null
        private var cancelUserId: String? = null
        private var userIP: String? = null
        private var cancelUserInfo: String? = null
        private var cancelRetryCnt: String? = null
        private var worker: String? = null


        fun iMid(value: String?) = apply { this.iMid = value }
        fun tXid(value: String?) = apply { this.tXid = value }
        fun referenceNo(value: String?) = apply { this.referenceNo = value }
        fun amt(value: String?) = apply { this.amt = value }
        fun merchantToken(iMid: String?, referenceNo: String?, amt: String?, merchantKey: String?) = apply {
            this.merchantToken = SHA256Util.encrypt(iMid + referenceNo + amt + merchantKey).toString()
        }
        fun payMethod(value: String?) = apply { this.payMethod = value }
        fun cancelType(value: String?) = apply { this.cancelType = value }
        fun fee(value: String?) = apply { this.fee = value }
        fun vat(value: String?) = apply { this.vat = value }
        fun notaxAmt(value: String?) = apply { this.notaxAmt = value }
        fun reqServerIP(value: String?) = apply { this.reqServerIP = value }
        fun cancelMsg(value: String?) = apply { this.cancelMsg = value }
        fun cancelUserId(value: String?) = apply { this.cancelUserId = value }
        fun userIP(value: String?) = apply { this.userIP = value }
        fun cancelUserInfo(value: String?) = apply { this.cancelUserInfo = value }
        fun cancelRetryCnt(value: String?) = apply { this.cancelRetryCnt = value }
        fun worker(value: String?) = apply { this.worker = value }


        fun build(): DirectV1Cancel {
            return DirectV1Cancel(
                iMid, tXid, referenceNo, amt, merchantToken, payMethod,
                cancelType, fee, vat, notaxAmt, reqServerIP, cancelMsg,
                cancelUserId, userIP, cancelUserInfo, cancelRetryCnt, worker
            )
        }
    }
}
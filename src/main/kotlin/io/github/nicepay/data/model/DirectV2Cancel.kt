package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util
import io.github.nicepay.utils.code.NICEPayMethod

class DirectV2Cancel(
    private val timeStamp: String?,
    private val tXid: String?,
    private val iMid: String?,
    private val payMethod: String?,
    private val cancelType: String?,
    private val amt: String?,
    private val merchantToken: String?,
    private val referenceNo: String?,
    private val cancelMsg: String?,
    private val cancelServerIp: String?,
    private val cancelUserId: String?,
    private val cancelUserIp: String?,
    private val cancelUserInfo: String?,
    private val cancelRetryCnt: String?,
    private val worker: String?
) {

    class Builder {

        private var timeStamp: String? = null
        private var tXid: String? = null
        private var iMid: String? = null
        private var payMethod: String? = null
        private var cancelType: String? = null
        private var amt: String? = null
        private var merchantKey: String? = null
        private var referenceNo: String? = null

        private var cancelMsg: String? = null
        private var cancelServerIp: String? = null
        private var cancelUserId: String? = null
        private var cancelUserIp: String? = null
        private var cancelUserInfo: String? = null
        private var cancelRetryCnt: String? = null
        private var worker: String? = null

        // Fluent setters
        fun timeStamp(timeStamp: String) = apply { this.timeStamp = timeStamp }
        fun tXid(tXid: String) = apply { this.tXid = tXid }
        fun iMid(iMid: String) = apply { this.iMid = iMid }
        fun payMethod(payMethod: String) = apply { this.payMethod = payMethod }
        fun payMethod(payMethod: NICEPayMethod?) = apply { this.payMethod = payMethod?.code }
        fun cancelType(cancelType: String) = apply { this.cancelType = cancelType }
        fun amt(amt: String) = apply { this.amt = amt }
        fun merchantKey(merchantKey: String) = apply { this.merchantKey = merchantKey }
        fun referenceNo(referenceNo: String) = apply { this.referenceNo = referenceNo }

        fun cancelMsg(cancelMsg: String?) = apply { this.cancelMsg = cancelMsg }
        fun cancelServerIp(cancelServerIp: String?) = apply { this.cancelServerIp = cancelServerIp }
        fun cancelUserId(cancelUserId: String?) = apply { this.cancelUserId = cancelUserId }
        fun cancelUserIp(cancelUserIp: String?) = apply { this.cancelUserIp = cancelUserIp }
        fun cancelUserInfo(cancelUserInfo: String?) = apply { this.cancelUserInfo = cancelUserInfo }
        fun cancelRetryCnt(cancelRetryCnt: String?) = apply { this.cancelRetryCnt = cancelRetryCnt }
        fun worker(worker: String?) = apply { this.worker = worker }

        fun build(): DirectV2Cancel {
            return DirectV2Cancel(
                timeStamp,
                tXid,
                iMid,
                payMethod,
                cancelType,
                amt,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.tXid + this.amt + this.merchantKey).toString(),
                referenceNo,
                cancelMsg,
                cancelServerIp,
                cancelUserId,
                cancelUserIp,
                cancelUserInfo,
                cancelRetryCnt,
                worker
            )
        }
    }

}
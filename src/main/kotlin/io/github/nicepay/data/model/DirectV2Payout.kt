package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util
import io.github.nicepay.utils.code.PayoutBank

class DirectV2Payout(
    private val timeStamp: String?,
    private val iMid: String?,
    private val amt: String?,
    private val referenceNo: String?,
    private val bankCd: String?,
    val accountNo: String?,
    private val benefNm: String?,
    private val benefStatus: String?,
    private val benefType: String?,
    private val benefPhone: String?,
    private val payoutMethod: String?,
    private val merchantToken: String?
) {

    class Builder {
        private var timeStamp: String? = null
        private var iMid: String? = null
        private var amt: String? = null
        private var referenceNo: String? = null
        private var bankCd: String? = null
        private var accountNo: String? = null
        private var benefNm: String? = null
        private var benefStatus: String? = null
        private var benefType: String? = null
        private var benefPhone: String? = null
        private var payoutMethod: String? = null
        private var merchantKey: String? = null

        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun bankCd(bankCd: String?) = apply { this.bankCd = bankCd }
        fun bankCd(bankCd: PayoutBank?) = apply { this.bankCd = bankCd?.code }
        fun accountNo(accountNo: String?) = apply { this.accountNo = accountNo }
        fun benefNm(benefNm: String?) = apply { this.benefNm = benefNm }
        fun benefStatus(benefStatus: String?) = apply { this.benefStatus = benefStatus }
        fun benefType(benefType: String?) = apply { this.benefType = benefType }
        fun benefPhone(benefPhone: String?) = apply { this.benefPhone = benefPhone }
        fun payoutMethod(payoutMethod: String?) = apply { this.payoutMethod = payoutMethod }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }

        fun build(): DirectV2Payout {
            return DirectV2Payout(
                timeStamp, iMid, amt, referenceNo,
                bankCd, accountNo,
                benefNm, benefStatus, benefType, benefPhone, payoutMethod,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.amt + this.accountNo + this.merchantKey).toString()
            )
        }
    }

}
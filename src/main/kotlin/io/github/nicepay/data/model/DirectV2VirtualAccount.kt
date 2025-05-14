package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util
import io.github.nicepay.utils.code.NICEPayMethod
import io.github.nicepay.utils.code.VirtualAccountBank

class DirectV2VirtualAccount(
    private val timeStamp: String?,
    private val iMid: String?,
    private val payMethod: String?,
    private val currency: String?,
    private val amt: String?,
    private val referenceNo: String?,
    private val goodsNm: String?,
    private val billingNm: String?,
    private val billingPhone: String?,
    private val billingEmail: String?,
    private val billingAddr: String?,
    private val billingCity: String?,
    private val billingState: String?,
    private val billingPostCd: String?,
    private val billingCountry: String?,
    private val bankCd: String?,
    private val vacctValidDt: String?,
    private val vacctValidTm: String?,
    private val merFixAcctId: String?,
    private val dbProcessUrl: String?,
    private val merchantToken: String?
) {

    class Builder {
        private var timeStamp: String? = null
        private var iMid: String? = null
        private var payMethod: String? = null
        private var currency: String? = null
        private var amt: String? = null
        private var referenceNo: String? = null
        private var goodsNm: String? = null
        private var billingNm: String? = null
        private var billingPhone: String? = null
        private var billingEmail: String? = null
        private var billingAddr: String? = null
        private var billingCity: String? = null
        private var billingState: String? = null
        private var billingPostCd: String? = null
        private var billingCountry: String? = null
        private var bankCd: String? = null
        private var vacctValidDt: String? = null
        private var vacctValidTm: String? = null
        private var merFixAcctId: String? = null
        private var dbProcessUrl: String? = null
        private var merchantKey: String? = null

        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun payMethod(payMethod: String?) = apply { this.payMethod = payMethod }
        fun payMethod(payMethod: NICEPayMethod?) = apply { this.payMethod = payMethod?.code }
        fun currency(currency: String?) = apply { this.currency = currency }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun goodsNm(goodsNm: String?) = apply { this.goodsNm = goodsNm }
        fun billingNm(billingNm: String?) = apply { this.billingNm = billingNm }
        fun billingPhone(billingPhone: String?) = apply { this.billingPhone = billingPhone }
        fun billingEmail(billingEmail: String?) = apply { this.billingEmail = billingEmail }
        fun billingAddr(billingAddr: String?) = apply { this.billingAddr = billingAddr }
        fun billingCity(billingCity: String?) = apply { this.billingCity = billingCity }
        fun billingState(billingState: String?) = apply { this.billingState = billingState }
        fun billingPostCd(billingPostCd: String?) = apply { this.billingPostCd = billingPostCd }
        fun billingCountry(billingCountry: String?) = apply { this.billingCountry = billingCountry }
        fun bankCd(bankCd: String?) = apply { this.bankCd = bankCd }
        fun bankCd(bankCd: VirtualAccountBank?) = apply { this.bankCd = bankCd?.code }
        fun vacctValidDt(vacctValidDt: String?) = apply { this.vacctValidDt = vacctValidDt }
        fun vacctValidTm(vacctValidTm: String?) = apply { this.vacctValidTm = vacctValidTm }
        fun merFixAcctId(merFixAcctId: String?) = apply { this.merFixAcctId = merFixAcctId }
        fun dbProcessUrl(dbProcessUrl: String?) = apply { this.dbProcessUrl = dbProcessUrl }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }

        fun build(): DirectV2VirtualAccount {
            return DirectV2VirtualAccount(
                timeStamp, iMid, payMethod, currency, amt, referenceNo, goodsNm,
                billingNm, billingPhone, billingEmail, billingAddr, billingCity,
                billingState, billingPostCd, billingCountry, bankCd, vacctValidDt,
                vacctValidTm, merFixAcctId, dbProcessUrl,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString()
            )
        }
    }
}
package io.github.nicepay.data.model

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.nicepay.data.cart.CartData
import io.github.nicepay.data.cart.SellersData
import io.github.nicepay.utils.SHA256Util
import io.github.nicepay.utils.code.NICEPayMethod
import io.github.nicepay.utils.code.PayloanMitra

class DirectV2Payloan(
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
    private val mitraCd: String?,
    private val payValidDt: String?,
    private val payValidTm: String?,
    private val dbProcessUrl: String?,
    private val cartData: String?,
    private val sellers: String?,
    private val deliveryNm: String?,
    private val deliveryPhone: String?,
    private val deliveryAddr: String?,
    private val deliveryCity: String?,
    private val deliveryState: String?,
    private val deliveryPostCd: String?,
    private val deliveryCountry: String?,
    private val instmntType: String?,
    private val instmntMon: String?,
    private val recurrOpt: String?,
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
        private var mitraCd: String? = null
        private var payValidDt: String? = null
        private var payValidTm: String? = null
        private var dbProcessUrl: String? = null
        private var cartData: String? = null
        private var sellers: String? = null
        private var merchantKey: String? = null
        private var deliveryNm: String? = null
        private var deliveryPhone: String? = null
        private var deliveryAddr: String? = null
        private var deliveryCity: String? = null
        private var deliveryState: String? = null
        private var deliveryPostCd: String? = null
        private var deliveryCountry: String? = null
        private var instmntType: String? = null
        private var instmntMon: String? = null
        private var recurrOpt: String? = null

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
        fun mitraCd(mitraCd: String?) = apply { this.mitraCd = mitraCd }
        fun mitraCd(mitraCd: PayloanMitra?) = apply { this.mitraCd = mitraCd?.code }
        fun payValidDt(payValidDt: String?) = apply { this.payValidDt = payValidDt }
        fun payValidTm(payValidTm: String?) = apply { this.payValidTm = payValidTm }
        fun dbProcessUrl(dbProcessUrl: String?) = apply { this.dbProcessUrl = dbProcessUrl }
        fun cartData(cartData: String?) = apply { this.cartData = cartData }
        fun cartData(cartData: CartData?) = apply {
            val mapper = ObjectMapper()
            this.cartData = mapper.writeValueAsString(
                cartData
            )
        }
        fun sellers(sellers: String?) = apply { this.sellers = sellers }
        fun sellers(sellers: List<SellersData>?) = apply {
            val mapper = ObjectMapper()
            this.sellers = mapper.writeValueAsString(
                sellers
            )
        }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }
        fun deliveryNm(deliveryNm: String?) = apply { this.deliveryNm = deliveryNm }
        fun deliveryPhone(deliveryPhone: String?) = apply { this.deliveryPhone = deliveryPhone }
        fun deliveryAddr(deliveryAddr: String?) = apply { this.deliveryAddr = deliveryAddr }
        fun deliveryCity(deliveryCity: String?) = apply { this.deliveryCity = deliveryCity }
        fun deliveryState(deliveryState: String?) = apply { this.deliveryState = deliveryState }
        fun deliveryPostCd(deliveryPostCd: String?) = apply { this.deliveryPostCd = deliveryPostCd }
        fun deliveryCountry(deliveryCountry: String?) = apply { this.deliveryCountry = deliveryCountry }
        fun instmntType(instmntType: String?) = apply { this.instmntType = instmntType }
        fun instmntMon(instmntMon: String?) = apply { this.instmntMon = instmntMon }
        fun recurrOpt(recurrOpt: String?) = apply { this.recurrOpt = recurrOpt }

        fun build(): DirectV2Payloan {
            return DirectV2Payloan(
                timeStamp,
                iMid,
                payMethod,
                currency,
                amt,
                referenceNo,
                goodsNm,
                billingNm,
                billingPhone,
                billingEmail,
                billingAddr,
                billingCity,
                billingState,
                billingPostCd,
                billingCountry,
                mitraCd,
                payValidDt,
                payValidTm,
                dbProcessUrl,
                cartData,
                sellers,
                deliveryNm,
                deliveryPhone,
                deliveryAddr,
                deliveryCity,
                deliveryState,
                deliveryPostCd,
                deliveryCountry,
                instmntType,
                instmntMon,
                recurrOpt,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString()
            )
        }
    }

}
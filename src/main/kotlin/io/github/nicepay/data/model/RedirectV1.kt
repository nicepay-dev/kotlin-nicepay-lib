package io.github.nicepay.data.model

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.nicepay.data.cart.CartData
import io.github.nicepay.utils.SHA256Util

class RedirectV1(
//    Mandatory fields
    private val iMid: String?,
    private val timeStamp: String?,
    private val payMethod: String?,
    private val currency: String?,
    private val amt: String?,
    private val referenceNo: String?,
    private val goodsNm: String?,
    private val billingNm: String?,
    private val billingEmail: String?,
    private val billingPhone: String?,
    private val billingAddr: String?,
    private val billingCity: String?,
    private val billingState: String?,
    private val billingPostCd: String?,
    private val billingCountry: String?,
    private val deliveryNm: String?,
    private val deliveryAddr: String?,
    private val deliveryCity: String?,
    private val deliveryState: String?,
    private val deliveryPostCd: String?,
    private val deliveryCountry: String?,
    private val deliveryPhone: String?,
    private val callBackUrl: String?,
    private val dbProcessUrl: String?,
    private val vat: String?,
    private val fee: String?,
    private val notaxAmt: String?,
    private val description: String?,
    private val userIP: String?,
    private val shopId: String?,
    private val merchantToken: String?,
    private val cartData: String?,
    private val instmntMon: String?,
    private val instmntType: String?,
    private val recurrOpt: String?,

//    Optional
    private val reqDt: String?,
    private val reqTm: String?,
    private val reqDomain: String?,
    private val reqServerIP: String?,
    private val reqClientVer: String?,
    private val userSessionID: String?,
    private val userAgent: String?,
    private val userLanguage: String?,
    private val merFixAcctId: String?,
    private val vacctValidDt: String?,
    private val vacctValidTm: String?,
    private val paymentExpiryDt: String?,
    private val paymentExpiryTm: String?,


    ) {

    class Builder() {


        //    Mandatory fields
        private var iMid: String? = null
        private var timeStamp: String? = null
        private var payMethod: String? = null
        private var currency: String? = null
        private var amt: String? = null
        private var referenceNo: String? = null
        private var goodsNm: String? = null
        private var billingNm: String? = null
        private var billingEmail: String? = null
        private var billingPhone: String? = null
        private var billingAddr: String? = null
        private var billingCity: String? = null
        private var billingState: String? = null
        private var billingPostCd: String? = null
        private var billingCountry: String? = null
        private var deliveryNm: String? = null
        private var deliveryAddr: String? = null
        private var deliveryCity: String? = null
        private var deliveryState: String? = null
        private var deliveryPostCd: String? = null
        private var deliveryCountry: String? = null
        private var deliveryPhone: String? = null
        private var callBackUrl: String? = null
        private var dbProcessUrl: String? = null
        private var vat: String? = null
        private var fee: String? = null
        private var notaxAmt: String? = null
        private var description: String? = null
        private var userIP: String? = null
        private var shopId: String? = null
        private var merchantToken: String? = null
        private var cartData: String? = null
        private var instmntMon: String? = null
        private var instmntType: String? = null
        private var recurrOpt: String? = null

        //    Optional
        private var reqDt: String? = null
        private var reqTm: String? = null
        private var reqDomain: String? = null
        private var reqServerIP: String? = null
        private var reqClientVer: String? = null
        private var userSessionID: String? = null
        private var userAgent: String? = null
        private var userLanguage: String? = null
        private var merFixAcctId: String? = null
        private var vacctValidDt: String? = null
        private var vacctValidTm: String? = null
        private var paymentExpiryDt: String? = null
        private var paymentExpiryTm: String? = null

        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun payMethod(payMethod: String?) = apply { this.payMethod = payMethod }
        fun currency(currency: String?) = apply { this.currency = currency }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun goodsNm(goodsNm: String?) = apply { this.goodsNm = goodsNm }
        fun billingNm(billingNm: String?) = apply { this.billingNm = billingNm }
        fun billingEmail(billingEmail: String?) = apply { this.billingEmail = billingEmail }
        fun billingPhone(billingPhone: String?) = apply { this.billingPhone = billingPhone }
        fun billingAddr(billingAddr: String?) = apply { this.billingAddr = billingAddr }
        fun billingCity(billingCity: String?) = apply { this.billingCity = billingCity }
        fun billingState(billingState: String?) = apply { this.billingState = billingState }
        fun billingPostCd(billingPostCd: String?) = apply { this.billingPostCd = billingPostCd }
        fun billingCountry(billingCountry: String?) = apply { this.billingCountry = billingCountry }
        fun deliveryNm(deliveryNm: String?) = apply { this.deliveryNm = deliveryNm }
        fun deliveryAddr(deliveryAddr: String?) = apply { this.deliveryAddr = deliveryAddr }
        fun deliveryCity(deliveryCity: String?) = apply { this.deliveryCity = deliveryCity }
        fun deliveryState(deliveryState: String?) = apply { this.deliveryState = deliveryState }
        fun deliveryPostCd(deliveryPostCd: String?) = apply { this.deliveryPostCd = deliveryPostCd }
        fun deliveryCountry(deliveryCountry: String?) = apply { this.deliveryCountry = deliveryCountry }
        fun deliveryPhone(deliveryPhone: String?) = apply { this.deliveryPhone = deliveryPhone }
        fun callBackUrl(callBackUrl: String?) = apply { this.callBackUrl = callBackUrl }
        fun dbProcessUrl(dbProcessUrl: String?) = apply { this.dbProcessUrl = dbProcessUrl }
        fun vat(vat: String?) = apply { this.vat = vat }
        fun fee(fee: String?) = apply { this.fee = fee }
        fun notaxAmt(notaxAmt: String?) = apply { this.notaxAmt = notaxAmt }
        fun description(description: String?) = apply { this.description = description }
        fun userIP(userIP: String?) = apply { this.userIP = userIP }
        fun shopId(shopId: String?) = apply { this.shopId = shopId }
        fun instmntMon(instmntMon: String?) = apply { this.instmntMon = instmntMon }
        fun instmntType(instmntType: String?) = apply { this.instmntType = instmntType }
        fun recurrOpt(recurrOpt: String?) = apply { this.recurrOpt = recurrOpt }

        //    Optional
        fun reqDt(reqDt: String?) = apply { this.reqDt = reqDt }
        fun reqTm(reqTm: String?) = apply { this.reqTm = reqTm }
        fun reqDomain(reqDomain: String?) = apply { this.reqDomain = reqDomain }
        fun reqServerIP(reqServerIP: String?) = apply { this.reqServerIP = reqServerIP }
        fun reqClientVer(reqClientVer: String?) = apply { this.reqClientVer = reqClientVer }
        fun userSessionID(userSessionID: String?) = apply { this.userSessionID = userSessionID }
        fun userAgent(userAgent: String?) = apply { this.userAgent = userAgent }
        fun userLanguage(userLanguage: String?) = apply { this.userLanguage = userLanguage }
        fun merFixAcctId(merFixAcctId: String?) = apply { this.merFixAcctId = merFixAcctId }
        fun vacctValidDt(vacctValidDt: String?) = apply { this.vacctValidDt = vacctValidDt }
        fun vacctValidTm(vacctValidTm: String?) = apply { this.vacctValidTm = vacctValidTm }
        fun paymentExpiryDt(paymentExpiryDt: String?) = apply { this.paymentExpiryDt = paymentExpiryDt }
        fun paymentExpiryTm(paymentExpiryTm: String?) = apply { this.paymentExpiryTm = paymentExpiryTm }

        fun cartData(cartData: String?) = apply { this.cartData = cartData }
        fun cartData(cartData: CartData?) = apply {
            val mapper = ObjectMapper()
            this.cartData = mapper.writeValueAsString(
                cartData
            )
        }

        fun merchantToken(iMid: String?, referenceNo: String?, amt: String?, merchantKey: String?) = apply {
            this.merchantToken = SHA256Util.encrypt(iMid + referenceNo + amt + merchantKey).toString()
        }

        fun build(): RedirectV1 {
            return RedirectV1(
                iMid,
                timeStamp,
                payMethod,
                currency,
                amt,
                referenceNo,
                goodsNm,
                billingNm,
                billingEmail,
                billingPhone,
                billingAddr,
                billingCity,
                billingState,
                billingPostCd,
                billingCountry,
                deliveryNm,
                deliveryAddr,
                deliveryCity,
                deliveryState,
                deliveryPostCd,
                deliveryCountry,
                deliveryPhone,
                callBackUrl,
                dbProcessUrl,
                vat,
                fee,
                notaxAmt,
                description,
                userIP,
                shopId,
                merchantToken,
                cartData,
                instmntMon,
                instmntType,
                recurrOpt,
                reqDt,
                reqTm,
                reqDomain,
                reqServerIP,
                reqClientVer,
                userSessionID,
                userAgent,
                userLanguage,
                merFixAcctId,
                vacctValidDt,
                vacctValidTm,
                paymentExpiryDt,
                paymentExpiryTm
            )


        }

    }

}
import io.github.nicepay.utils.SHA256Util

// 🔹 Base Request (shared fields)
abstract class DirectV1Request(
    open val iMid: String? = null,
    open val timeStamp: String? = null,
    open val payMethod: String? = null,
    open val currency: String? = null,
    open val amt: String? = null,
    open val referenceNo: String? = null,
    open val goodsNm: String? = null,
    open val billingNm: String? = null,
    open val billingEmail: String? = null,
    open val billingPhone: String? = null,
    open val billingAddr: String? = null,
    open val billingCity: String? = null,
    open val billingState: String? = null,
    open val billingPostCd: String? = null,
    open val billingCountry: String? = null,
    open val callBackUrl: String? = null,
    open val dbProcessUrl: String? = null,
    open val description: String? = null,
    open val merchantToken: String? = null,
    open val userIP: String? = null,
    open val cartData: String? = null,
    open val deliveryNm: String? = null,
    open val deliveryPhone: String? = null,
    open val deliveryAddr: String? = null,
    open val deliveryCity: String? = null,
    open val deliveryState: String? = null,
    open val deliveryPostCd: String? = null,
    open val deliveryCountry: String? = null,
    open val vat: String? = null,
    open val fee: String? = null,
    open val notaxAmt: String? = null,
    open val reqDt: String? = null,
    open val reqTm: String? = null,
    open val reqDomain: String? = null,
    open val reqServerIP: String? = null,
    open val reqClientVer: String? = null,
    open val userSessionID: String? = null,
    open val userAgent: String? = null,
    open val userLanguage: String? = null,

) {
    abstract class Builder<T : DirectV1Request, B : Builder<T, B>> {
        protected var iMid: String? = null
        protected var timeStamp: String? = null
        protected var payMethod: String? = null
        protected var currency: String? = null
        protected var amt: String? = null
        protected var referenceNo: String? = null
        protected var goodsNm: String? = null
        protected var billingNm: String? = null
        protected var billingEmail: String? = null
        protected var billingPhone: String? = null
        protected var billingAddr: String? = null
        protected var billingCity: String? = null
        protected var billingState: String? = null
        protected var billingPostCd: String? = null
        protected var billingCountry: String? = null
        protected var callBackUrl: String? = null
        protected var dbProcessUrl: String? = null
        protected var description: String? = null
        protected var merchantToken: String? = null
        protected var userIP: String? = null
        protected var cartData: String? = null
        protected var deliveryNm: String? = null
        protected var deliveryPhone: String? = null
        protected var deliveryAddr: String? = null
        protected var deliveryCity: String? = null
        protected var deliveryState: String? = null
        protected var deliveryPostCd: String? = null
        protected var deliveryCountry: String? = null
        protected var vat: String? = null
        protected var fee: String? = null
        protected var notaxAmt: String? = null
        protected var reqDt: String? = null
        protected var reqTm: String? = null
        protected var reqDomain: String? = null
        protected var reqServerIP: String? = null
        protected var userSessionID: String? = null
        protected var reqClientVer: String? = null
        protected var userAgent: String? = null
        protected var userLanguage: String? = null

        fun iMid(value: String?) = apply { this.iMid = value } as B
        fun timeStamp(value: String?) = apply { this.timeStamp = value } as B
        fun payMethod(value: String?) = apply { this.payMethod = value } as B
        fun currency(value: String?) = apply { this.currency = value } as B
        fun amt(value: String?) = apply { this.amt = value } as B
        fun referenceNo(value: String?) = apply { this.referenceNo = value } as B
        fun goodsNm(value: String?) = apply { this.goodsNm = value } as B
        fun billingNm(value: String?) = apply { this.billingNm = value } as B
        fun billingEmail(value: String?) = apply { this.billingEmail = value } as B
        fun billingPhone(value: String?) = apply { this.billingPhone = value } as B
        fun billingAddr(value: String?) = apply { this.billingAddr = value } as B
        fun billingCity(value: String?) = apply { this.billingCity = value } as B
        fun billingState(value: String?) = apply { this.billingState = value } as B
        fun billingPostCd(value: String?) = apply { this.billingPostCd = value } as B
        fun billingCountry(value: String?) = apply { this.billingCountry = value } as B
        fun callBackUrl(value: String?) = apply { this.callBackUrl = value } as B
        fun dbProcessUrl(value: String?) = apply { this.dbProcessUrl = value } as B
        fun description(value: String?) = apply { this.description = value } as B
        fun merchantToken(iMid: String?, referenceNo: String?, amt: String?, merchantKey: String?) = apply {
            this.merchantToken = SHA256Util.encrypt(iMid + referenceNo + amt + merchantKey).toString()
        } as B
        fun merchantToken(iMid: String?, recurringToken: String?, merchantKey: String?) = apply {
            this.merchantToken = SHA256Util.encrypt(iMid + recurringToken + merchantKey).toString()
        } as B
        fun userIP(value: String?) = apply { this.userIP = value } as B
        fun cartData(value: String?) = apply { this.cartData = value } as B
        fun deliveryNm(value: String?) = apply { this.deliveryNm = value } as B
        fun deliveryPhone(value: String?) = apply { this.deliveryPhone = value } as B
        fun deliveryAddr(value: String?) = apply { this.deliveryAddr = value } as B
        fun deliveryCity(value: String?) = apply { this.deliveryCity = value } as B
        fun deliveryState(value: String?) = apply { this.deliveryState = value } as B
        fun deliveryPostCd(value: String?) = apply { this.deliveryPostCd = value } as B
        fun deliveryCountry(value: String?) = apply { this.deliveryCountry = value } as B
        fun vat(value: String?) = apply { this.vat = value } as B
        fun fee(value: String?) = apply { this.fee = value } as B
        fun notaxAmt(value: String?) = apply { this.notaxAmt = value } as B
        fun reqDt(value: String?) = apply { this.reqDt = value } as B
        fun reqTm(value: String?) = apply { this.reqTm = value } as B
        fun reqDomain(value: String?) = apply { this.reqDomain = value } as B
        fun reqServerIP(value: String?) = apply { this.reqServerIP = value } as B
        fun reqClientVer(value: String?) = apply { this.reqClientVer = value } as B
        fun userSessionID(value: String?) = apply { this.userSessionID = value } as B
        fun userAgent(value: String?) = apply { this.userAgent = value } as B
        fun userLanguage(value: String?) = apply { this.userLanguage = value } as B

        abstract fun build(): T
    }
}
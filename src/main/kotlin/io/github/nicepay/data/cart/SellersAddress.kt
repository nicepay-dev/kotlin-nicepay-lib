package io.github.nicepay.data.cart

class SellersAddress(
    val sellerNm : String?,
    val sellerLastNm : String?,
    val sellerAddr : String?,
    val sellerCity : String?,
    val sellerPostCd : String?,
    val sellerPhone : String?,
    val sellerCountry : String?
) {

    class Builder {
        private var sellerNm: String? = null
        private var sellerLastNm: String? = null
        private var sellerAddr: String? = null
        private var sellerCity: String? = null
        private var sellerPostCd: String? = null
        private var sellerPhone: String? = null
        private var sellerCountry: String? = null

        fun sellerNm(sellerNm: String?) = apply { this.sellerNm = sellerNm }
        fun sellerLastNm(sellerLastNm: String?) = apply { this.sellerLastNm = sellerLastNm }
        fun sellerAddr(sellerAddr: String?) = apply { this.sellerAddr = sellerAddr }
        fun sellerCity(sellerCity: String?) = apply { this.sellerCity = sellerCity }
        fun sellerPostCd(sellerPostCd: String?) = apply { this.sellerPostCd = sellerPostCd }
        fun sellerPhone(sellerPhone: String?) = apply { this.sellerPhone = sellerPhone }
        fun sellerCountry(sellerCountry: String?) = apply { this.sellerCountry = sellerCountry }

        fun build(): SellersAddress {
            return SellersAddress(
                sellerNm,
                sellerLastNm,
                sellerAddr,
                sellerCity,
                sellerPostCd,
                sellerPhone,
                sellerCountry
            )
        }
    }

}
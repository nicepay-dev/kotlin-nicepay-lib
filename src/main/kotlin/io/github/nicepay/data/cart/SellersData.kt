package io.github.nicepay.data.cart

class SellersData(
    val sellersId : String?,
    val sellersNm : String?,
    val sellersEmail : String?,
    val sellersUrl : String?,
    val sellersAddress : SellersAddress?,
) {

    class Builder {
        private var sellersId: String? = null
        private var sellersNm: String? = null
        private var sellersEmail: String? = null
        private var sellersUrl: String? = null
        private var sellersAddress: SellersAddress? = null

        fun sellersId(sellersId: String?) = apply { this.sellersId = sellersId }
        fun sellersNm(sellersNm: String?) = apply { this.sellersNm = sellersNm }
        fun sellersEmail(sellersEmail: String?) = apply { this.sellersEmail = sellersEmail }
        fun sellersUrl(sellersUrl: String?) = apply { this.sellersUrl = sellersUrl }
        fun sellersAddress(sellersAddress: SellersAddress?) = apply { this.sellersAddress = sellersAddress }

        fun build(): SellersData {
            return SellersData(
                sellersId,
                sellersNm,
                sellersEmail,
                sellersUrl,
                sellersAddress
            )
        }
    }

}
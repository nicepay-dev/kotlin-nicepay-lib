package io.github.nicepay.data.model

class SnapQris private constructor(
    val partnerReferenceNo: String? = null,
    val merchantId: String? = null,
    val subMerchantId: String? = null,
    val storeId: String? = null,
    val validityPeriod: String? = null,
    val amount: Map<String, Any>? = null,
    val additionalInfo: Map<String, Any>? = null,
) {
    class Builder {
        private var partnerReferenceNo: String? = null
        private var merchantId: String? = null
        private var subMerchantId: String? = null
        private var externalStoreId: String? = null
        private var validityPeriod: String? = null
        private var amount: MutableMap<String, Any>? = null
        private var additionalInfo: MutableMap<String, Any>? = null

        fun partnerReferenceNo(value: String) = apply { this.partnerReferenceNo = value }
        fun merchantId(value: String) = apply { this.merchantId = value }
        fun subMerchantId(value: String) = apply { this.subMerchantId = value }
        fun storeId(value: String) = apply { this.externalStoreId = value }
        fun validityPeriod(value: String) = apply { this.validityPeriod = value }

        fun amount(value: String, currency: String) = apply {
            this.amount = mutableMapOf("value" to value, "currency" to currency)
        }

        fun additionalInfo(map: Map<String, Any>) = apply {
            this.additionalInfo = map.toMutableMap()
        }



        fun build(): SnapQris {
            return SnapQris(
                partnerReferenceNo,
                merchantId,
                subMerchantId,
                externalStoreId,
                validityPeriod,
                amount,
                additionalInfo,
            )
        }
    }
}

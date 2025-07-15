package io.github.nicepay.data.model

class SnapEwallet private constructor(
    val partnerReferenceNo: String? = null,
    val merchantId: String? = null,
    val subMerchantId: String? = null,
    val externalStoreId: String? = null,
    val validUpTo: String? = null,
    val pointOfInitiation: String? = null,
    val amount: Map<String, Any>? = null,
    val additionalInfo: Map<String, Any>? = null,
    val urlParam: List<Map<String, String>>? = null,
) {
    class Builder {
        private var partnerReferenceNo: String? = null
        private var merchantId: String? = null
        private var subMerchantId: String? = null
        private var externalStoreId: String? = null
        private var validUpTo: String? = null
        private var pointOfInitiation: String? = null
        private var amount: MutableMap<String, Any>? = null
        private var additionalInfo: MutableMap<String, Any>? = null
        private var urlParam: MutableList<Map<String, String>>? = null

        fun partnerReferenceNo(value: String) = apply { this.partnerReferenceNo = value }
        fun merchantId(value: String) = apply { this.merchantId = value }
        fun subMerchantId(value: String) = apply { this.subMerchantId = value }
        fun externalStoreId(value: String) = apply { this.externalStoreId = value }
        fun validUpTo(value: String) = apply { this.validUpTo = value }
        fun pointOfInitiation(value: String) = apply { this.pointOfInitiation = value }

        fun amount(value: String, currency: String) = apply {
            this.amount = mutableMapOf("value" to value, "currency" to currency)
        }

        fun additionalInfo(map: Map<String, Any>) = apply {
            this.additionalInfo = map.toMutableMap()
        }

        fun urlParam(urlParams: Array<Array<String>>) = apply {
            val paramList = mutableListOf<Map<String, String>>()
            urlParams.forEach { params ->
                if (params.size == 3) {
                    val map = mapOf(
                        "url" to params[0],
                        "type" to params[1],
                        "isDeeplink" to params[2]
                    )
                    paramList.add(map)
                }
            }
            this.urlParam = paramList
        }


        fun build(): SnapEwallet {
            return SnapEwallet(
                partnerReferenceNo,
                merchantId,
                subMerchantId,
                externalStoreId,
                validUpTo,
                pointOfInitiation,
                amount,
                additionalInfo,
                urlParam,
            )
        }
    }
}

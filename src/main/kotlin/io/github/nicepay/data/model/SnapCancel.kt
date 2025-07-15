package io.github.nicepay.data.model

class SnapCancel private constructor(
    val partnerServiceId: String? = null,
    val customerNo: String? = null,
    val virtualAccountNo: String? = null,
    val trxId: String? = null,
    val additionalInfoMap: Map<String, Any>? = null,

    val merchantId: String? = null,
    val subMerchantId: String? = null,
    val originalPartnerReferenceNo: String? = null,
    val originalReferenceNo: String? = null,
    val serviceCode: String? = null,
    val transactionDate: String? = null,
    val externalStoreId: String? = null,
    val refundAmount: Map<String, Any>? = null,

    val partnerRefundNo: String? = null,
    val reason: String? = null,

    val totalAmount: Map<String, Any>? = null,
    val additionalInfo: Map<String, Any>? = null,

) {
    class Builder {
        private var partnerServiceId: String? = null
        private var customerNo: String? = null
        private var virtualAccountNo: String? = null
        private var trxId: String? = null
        private var additionalInfoMap: Map<String, Any>? = null

        private var merchantId: String? = null
        private var subMerchantId: String? = null
        private var originalPartnerReferenceNo: String? = null
        private var originalReferenceNo: String? = null
        private var serviceCode: String? = null
        private var transactionDate: String? = null
        private var externalStoreId: String? = null
        private var refundAmount: MutableMap<String, Any>? = null

        private var partnerRefundNo: String? = null
        private var reason: String? = null

        private var totalAmount: MutableMap<String, Any>? = null
        private var additionalInfo: MutableMap<String, Any> = mutableMapOf()

        fun partnerServiceId(value: String) = apply { this.partnerServiceId = value }
        fun customerNo(value: String) = apply { this.customerNo = value }
        fun virtualAccountNo(value: String) = apply { this.virtualAccountNo = value }
        fun trxId(value: String) = apply { this.trxId = value }

        fun merchantId(value: String) = apply { this.merchantId = value }
        fun subMerchantId(value: String) = apply { this.subMerchantId = value }
        fun originalPartnerReferenceNo(value: String) = apply { this.originalPartnerReferenceNo = value }
        fun originalReferenceNo(value: String) = apply { this.originalReferenceNo = value }
        fun serviceCode(value: String) = apply { this.serviceCode = value }
        fun transactionDate(value: String) = apply { this.transactionDate = value }
        fun externalStoreId(value: String) = apply { this.externalStoreId = value }

        fun partnerRefundNo(value: String) = apply { this.partnerRefundNo = value }
        fun reason(value: String) = apply { this.reason = value }
        fun refundAmount(value: String, currency: String) = apply {
            this.refundAmount = mutableMapOf("value" to value, "currency" to currency)
        }

        fun totalAmount(value: String, currency: String) = apply {
            this.totalAmount = mutableMapOf("value" to value, "currency" to currency)
            additionalInfo["totalAmount"] = totalAmount!!
        }

        fun additionalInfo(additional: Map<String, Any>) = apply {
            this.additionalInfo.putAll(additional)
        }

        fun tXidVA(value: String) = apply {
            this.additionalInfo["tXidVA"] = value
        }

        fun cancelMessage(value: String) = apply {
            this.additionalInfo["cancelMessage"] = value
        }

        fun refundType(value: String) = apply {
            this.additionalInfo["refundType"] = value
        }

        fun build(): SnapCancel {
            return SnapCancel(
                partnerServiceId, customerNo, virtualAccountNo, trxId, additionalInfoMap,
                merchantId, subMerchantId, originalPartnerReferenceNo, originalReferenceNo,
                serviceCode, transactionDate, externalStoreId, refundAmount,
                partnerRefundNo, reason, totalAmount, additionalInfo
            )
        }
    }
}

package io.github.nicepay.data.model

class SnapCheckStatus private constructor(
    val partnerServiceId: String? = null,
    val customerNo: String? = null,
    val virtualAccountNo: String? = null,
    val inquiryRequestId: String? = null,
    val additionalInfo: Map<String, Any>? = null,

    val merchantId: String? = null,
    val subMerchantId: String? = null,
    val originalPartnerReferenceNo: String? = null,
    val originalReferenceNo: String? = null,
    val serviceCode: String? = null,
    val transactionDate: String? = null,
    val externalStoreId: String? = null,
    val amount: Map<String, Any>? = null,
    val totalAmount: Map<String, Any>? = null,
    val additionalInfoMap: Map<String, Any>? = null,

    val beneficiaryAccountNo: String? = null
) {
    class Builder {
        private var partnerServiceId: String? = null
        private var customerNo: String? = null
        private var virtualAccountNo: String? = null
        private var inquiryRequestId: String? = null
        private val additionalInfo: MutableMap<String, Any> = mutableMapOf()

        private var merchantId: String? = null
        private var subMerchantId: String? = null
        private var originalPartnerReferenceNo: String? = null
        private var originalReferenceNo: String? = null
        private var serviceCode: String? = null
        private var transactionDate: String? = null
        private var externalStoreId: String? = null
        private var amount: MutableMap<String, Any>? = null
        private var totalAmount: MutableMap<String, Any>? = null
        private var additionalInfoMap: MutableMap<String, Any>? = null

        private var beneficiaryAccountNo: String? = null

        fun partnerServiceId(value: String) = apply { this.partnerServiceId = value }
        fun customerNo(value: String) = apply { this.customerNo = value }
        fun virtualAccountNo(value: String) = apply { this.virtualAccountNo = value }
        fun inquiryRequestId(value: String) = apply { this.inquiryRequestId = value }

        fun merchantId(value: String) = apply { this.merchantId = value }
        fun subMerchantId(value: String) = apply { this.subMerchantId = value }
        fun originalPartnerReferenceNo(value: String) = apply { this.originalPartnerReferenceNo = value }
        fun originalReferenceNo(value: String) = apply { this.originalReferenceNo = value }
        fun serviceCode(value: String) = apply { this.serviceCode = value }
        fun transactionDate(value: String) = apply { this.transactionDate = value }
        fun externalStoreId(value: String) = apply { this.externalStoreId = value }
        fun beneficiaryAccountNo(value: String) = apply { this.beneficiaryAccountNo = value }

        fun totalAmount(value: String, currency: String) = apply {
            val totalAmountMap = mutableMapOf<String, Any>(
                "value" to value,
                "currency" to currency
            )
            this.totalAmount = totalAmountMap
            this.additionalInfo["totalAmount"] = totalAmountMap
        }

        fun trxId(value: String) = apply {
            this.additionalInfo["trxId"] = value
        }

        fun tXidVA(value: String) = apply {
            this.additionalInfo["tXidVA"] = value
        }

        fun additionalInfoMap(map: Map<String, Any>) = apply {
            this.additionalInfoMap = mutableMapOf<String, Any>().apply {
                put("totalAmount", additionalInfo)
                put("trxId", additionalInfo)
                put("tXidVA", additionalInfo)
            }
        }

        fun amount(value: String, currency: String) = apply {
            this.amount = mutableMapOf(
                "value" to value,
                "currency" to currency
            )
        }

        fun build(): SnapCheckStatus {
            return SnapCheckStatus(
                partnerServiceId,
                customerNo,
                virtualAccountNo,
                inquiryRequestId,
                additionalInfo,
                merchantId,
                subMerchantId,
                originalPartnerReferenceNo,
                originalReferenceNo,
                serviceCode,
                transactionDate,
                externalStoreId,
                amount,
                totalAmount,
                additionalInfoMap,
                beneficiaryAccountNo
            )
        }
    }
}

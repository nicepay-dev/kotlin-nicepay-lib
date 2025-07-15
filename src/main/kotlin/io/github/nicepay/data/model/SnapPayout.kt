package io.github.nicepay.data.model

class SnapPayout private constructor(
    val merchantId: String? = null,
    val msId: String? = null,
    val beneficiaryAccountNo: String? = null,
    val beneficiaryName: String? = null,
    val beneficiaryPhone: String? = null,
    val beneficiaryCustomerResidence: String? = null,
    val beneficiaryCustomerType: String? = null,
    val beneficiaryPostalCode: String? = null,
    val payoutMethod: String? = null,
    val beneficiaryBankCode: String? = null,
    val amount: Map<String, Any>? = null,
    val partnerReferenceNo: String? = null,
    val reservedDt: String? = null,
    val reservedTm: String? = null,
    val description: String? = null,
    val deliveryName: String? = null,
    val deliveryId: String? = null,
    val beneficiaryPOE: String? = null,
    val beneficiaryDOE: String? = null,
    val beneficiaryCoNo: String? = null,
    val beneficiaryAddress: String? = null,
    val beneficiaryAuthPhoneNumber: String? = null,
    val beneficiaryMerCategory: String? = null,
    val beneficiaryCoMgmtName: String? = null,
    val beneficiaryCoShName: String? = null,
    val originalPartnerReferenceNo: String? = null,
    val originalReferenceNo: String? = null,
    val additionalInfo: Map<String, Any>? = null,
    val accountNo: String? = null
) {
    class Builder {
        private var merchantId: String? = null
        private var msId: String? = null
        private var beneficiaryAccountNo: String? = null
        private var beneficiaryName: String? = null
        private var beneficiaryPhone: String? = null
        private var beneficiaryCustomerResidence: String? = null
        private var beneficiaryCustomerType: String? = null
        private var beneficiaryPostalCode: String? = null
        private var payoutMethod: String? = null
        private var beneficiaryBankCode: String? = null
        private var amount: MutableMap<String, Any>? = null
        private var partnerReferenceNo: String? = null
        private var reservedDt: String? = null
        private var reservedTm: String? = null
        private var description: String? = null
        private var deliveryName: String? = null
        private var deliveryId: String? = null
        private var beneficiaryPOE: String? = null
        private var beneficiaryDOE: String? = null
        private var beneficiaryCoNo: String? = null
        private var beneficiaryAddress: String? = null
        private var beneficiaryAuthPhoneNumber: String? = null
        private var beneficiaryMerCategory: String? = null
        private var beneficiaryCoMgmtName: String? = null
        private var beneficiaryCoShName: String? = null
        private var originalPartnerReferenceNo: String? = null
        private var originalReferenceNo: String? = null
        private var additionalInfo: MutableMap<String, Any>? = null
        private var accountNo: String? = null

        fun merchantId(value: String) = apply { this.merchantId = value }
        fun msId(value: String) = apply { this.msId = value }
        fun beneficiaryAccountNo(value: String) = apply { this.beneficiaryAccountNo = value }
        fun beneficiaryName(value: String) = apply { this.beneficiaryName = value }
        fun beneficiaryPhone(value: String) = apply { this.beneficiaryPhone = value }
        fun beneficiaryCustomerResidence(value: String) = apply { this.beneficiaryCustomerResidence = value }
        fun beneficiaryCustomerType(value: String) = apply { this.beneficiaryCustomerType = value }
        fun beneficiaryPostalCode(value: String) = apply { this.beneficiaryPostalCode = value }
        fun payoutMethod(value: String) = apply { this.payoutMethod = value }
        fun beneficiaryBankCode(value: String) = apply { this.beneficiaryBankCode = value }

        fun amount(value: String, currency: String) = apply {
            this.amount = mutableMapOf("value" to value, "currency" to currency)
        }

        fun partnerReferenceNo(value: String) = apply { this.partnerReferenceNo = value }
        fun reservedDt(value: String) = apply { this.reservedDt = value }
        fun reservedTm(value: String) = apply { this.reservedTm = value }
        fun description(value: String) = apply { this.description = value }
        fun deliveryName(value: String) = apply { this.deliveryName = value }
        fun deliveryId(value: String) = apply { this.deliveryId = value }
        fun beneficiaryPOE(value: String) = apply { this.beneficiaryPOE = value }
        fun beneficiaryDOE(value: String) = apply { this.beneficiaryDOE = value }
        fun beneficiaryCoNo(value: String) = apply { this.beneficiaryCoNo = value }
        fun beneficiaryAddress(value: String) = apply { this.beneficiaryAddress = value }
        fun beneficiaryAuthPhoneNumber(value: String) = apply { this.beneficiaryAuthPhoneNumber = value }
        fun beneficiaryMerCategory(value: String) = apply { this.beneficiaryMerCategory = value }
        fun beneficiaryCoMgmtName(value: String) = apply { this.beneficiaryCoMgmtName = value }
        fun beneficiaryCoShName(value: String) = apply { this.beneficiaryCoShName = value }
        fun originalPartnerReferenceNo(value: String) = apply { this.originalPartnerReferenceNo = value }
        fun originalReferenceNo(value: String) = apply { this.originalReferenceNo = value }
        fun accountNo(value: String) = apply { this.accountNo = value }

        fun additionalInfo(msId: String) = apply {
            this.additionalInfo = mutableMapOf("msId" to msId)
        }

        fun build(): SnapPayout {
            return SnapPayout(
                merchantId,
                msId,
                beneficiaryAccountNo,
                beneficiaryName,
                beneficiaryPhone,
                beneficiaryCustomerResidence,
                beneficiaryCustomerType,
                beneficiaryPostalCode,
                payoutMethod,
                beneficiaryBankCode,
                amount,
                partnerReferenceNo,
                reservedDt,
                reservedTm,
                description,
                deliveryName,
                deliveryId,
                beneficiaryPOE,
                beneficiaryDOE,
                beneficiaryCoNo,
                beneficiaryAddress,
                beneficiaryAuthPhoneNumber,
                beneficiaryMerCategory,
                beneficiaryCoMgmtName,
                beneficiaryCoShName,
                originalPartnerReferenceNo,
                originalReferenceNo,
                additionalInfo,
                accountNo
            )
        }
    }
}

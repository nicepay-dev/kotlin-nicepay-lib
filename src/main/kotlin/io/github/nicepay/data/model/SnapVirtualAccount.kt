package io.github.nicepay.data.model

class SnapVirtualAccount private constructor(
    val partnerServiceId: String? = null,
    val customerNo: String? = null,
    val virtualAccountNo: String? = null,
    val virtualAccountName: String? = null,
    val trxId: String? = null,
    val totalAmount: Map<String, Any>? = null,
    val additionalInfo: Map<String, Any>? = null,
    val tXidVA: String? = null
) {
    class Builder {
        private var partnerServiceId: String? = null
        private var customerNo: String? = null
        private var virtualAccountNo: String? = null
        private var virtualAccountName: String? = null
        private var trxId: String? = null
        private var totalAmount: MutableMap<String, Any>? = null
        private var additionalInfo: MutableMap<String, Any>? = null
        private var tXidVA: String? = null

        fun partnerServiceId(value: String) = apply { this.partnerServiceId = value }
        fun customerNo(value: String) = apply { this.customerNo = value }
        fun virtualAccountNo(value: String) = apply { this.virtualAccountNo = value }
        fun virtualAccountName(value: String) = apply { this.virtualAccountName = value }
        fun trxId(value: String) = apply { this.trxId = value }
        fun tXidVA(value: String) = apply { this.tXidVA = value }

        fun totalAmount(value: String, currency: String) = apply {
            this.totalAmount = mutableMapOf("value" to value, "currency" to currency)
        }

        fun additionalInfo(map: Map<String, Any>) = apply {
            this.additionalInfo = map.toMutableMap()
        }

        fun build(): SnapVirtualAccount {
            return SnapVirtualAccount(
                partnerServiceId,
                customerNo,
                virtualAccountNo,
                virtualAccountName,
                trxId,
                totalAmount,
                additionalInfo,
                tXidVA
            )
        }
    }
}

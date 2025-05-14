package io.github.nicepay.data.model

class SnapVirtualAccount(
    partnerServiceId: String?,
    customerNo: String?,
    virtualAccountNo: String?,
    virtualAccountName: String?,
    trxId: String?,
    totalAmount: Map<String, Any>?,
    additionalInfo: Map<String, Any>?,
    tXidVA: String?
) {

    private val  partnerServiceId: String? = null
    private val  customerNo: String? = null
    private val  virtualAccountNo: String? = null
    private val  virtualAccountName: String? = null
    private val  trxId: String? = null
    private val  totalAmount: Map<String, Any>? = null
    private val  additionalInfo: Map<String, Any>? = null
    private val  tXidVA: String? = null

    class Builder {
        private var partnerServiceId: String? = null
        private var customerNo: String? = null
        private var virtualAccountNo: String? = null
        private var virtualAccountName: String? = null
        private var trxId: String? = null
        private var totalAmount: Map<String, Any>? = null
        private var additionalInfo: Map<String, Any>? = null
        private var tXidVA: String? = null

        // Builder methods for each property
        fun partnerServiceId(partnerServiceId: String?) = apply { this.partnerServiceId = partnerServiceId }
        fun customerNo(customerNo: String?) = apply { this.customerNo = customerNo }
        fun virtualAccountNo(virtualAccountNo: String?) = apply { this.virtualAccountNo = virtualAccountNo }
        fun virtualAccountName(virtualAccountName: String?) = apply { this.virtualAccountName = virtualAccountName }
        fun trxId(trxId: String?) = apply { this.trxId = trxId }
        fun totalAmount(totalAmount: Map<String, Any>?) = apply { this.totalAmount = totalAmount }
        fun additionalInfo(additionalInfo: Map<String, Any>?) = apply { this.additionalInfo = additionalInfo }
        fun tXidVA(tXidVA: String?) = apply { this.tXidVA = tXidVA }

        fun build(): SnapVirtualAccount {
            return SnapVirtualAccount(
                partnerServiceId, customerNo, virtualAccountNo, virtualAccountName,
                trxId, totalAmount, additionalInfo, tXidVA
            )
        }
    }

}
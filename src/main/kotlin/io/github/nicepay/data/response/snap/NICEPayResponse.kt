package io.github.nicepay.data.response.snap

import io.github.nicepay.data.response.snap.nested.ObjectAccountInfo

class NICEPayResponse : BaseNICEPayResponse() {


    var accessToken: String? = null
    var expiresIn: String? = null
    var tokenType: String? = null
    var virtualAccountData: Map<String, Any>? = null
    var additionalInfo: Map<String, String>? = null
    var totalAmount: Map<String, String>? = null

    // Virtual Account
    var partnerServiceId: String? = null
    var customerNo: String? = null
    var inquiryRequestId: String? = null
    var virtualAccountNo: String? = null
    var virtualAccountName: String? = null
    var trxId: String? = null
    var transactionStatusDesc: String? = null
    var latestTransactionStatus: String? = null
    var bankCd: String? = null
    var tXidVA: String? = null
    var goodsNm: String? = null
    var vacctValidTm: String? = null
    var vacctValidDt: String? = null

    // Ewallet
    var amount: Map<String, String>? = null
    var ewalletData: Map<String, Any>? = null
    var partnerReferenceNo: String? = null
    var originalReferenceNo: String? = null
    var refundNo: String? = null
    var Value: String? = null
    var refundType: String? = null
    var refundTime: String? = null
    var Currency: String? = null
    var partnerRefundNo: String? = null
    var webRedirectUrl: String? = null
    var originalPartnerReferenceNo: String? = null
    var appRedirectUrl: String? = null
    var refundAmount: Map<String, Any>? = null
    var transAmount: Map<String, Any>? = null

    // QRIS
    var qrContent: String? = null
    var qrUrl: String? = null
    var referenceNo: String? = null

    // Payout
    var accountNo: String? = null
    var beneficiaryaccountNo: String? = null
    var beneficiaryBankCode: String? = null
    var beneficiaryName: String? = null
    var payoutMethod: String? = null
    var beneficiaryAccountNo: String? = null
    var transactionStatus: String? = null
    var accountInfos: ArrayList<ObjectAccountInfo>? = null
    var beneficiaryCustomerResidence: String? = null
    var beneficiaryCustomerType: String? = null


}
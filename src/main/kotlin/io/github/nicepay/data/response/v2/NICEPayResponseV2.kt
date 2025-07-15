package io.github.nicepay.data.response.v2

import com.google.gson.JsonObject

class NICEPayResponseV2 : BaseNICEPayResponseV2() {

    //base
    val tXid: String? = null
    val referenceNo: String? = null
    val payMethod: String? = null
    val amt: String? = null
    val transDt: String? = null
    val transTm: String? = null
    val currency: String? = null
    val goodsNm: String? = null
    val billingNm: String? = null
    val mitraCd: String? = null

    //va
    val bankCd: String? = null
    val vacctNo: String? = null
    val vacctValidDt: String? = null
    val vacctValidTm: String? = null

    //CVS
    val payNo: String? = null
    val payValidDt: String? = null
    val payValidTm: String? = null

    //QRIS
    val qrContent: String? = null
    val qrUrl: String? = null

    //inquiry
    val reqDt: String? = null
    val reqTm: String? = null
    val status: String? = null

    //cancel
    val description: String? = null

    // CC
    val ccTransType: String? = null
    val cardNo: String? = null
    val issuBankCd: String? = null
    val preauthToken: String? = null
    val cardExpYymm: String? = null
    val acquBankNm: String? = null
    val instmntType: String? = null
    val instmntMon: String? = null
    val issuBankNm: String? = null
    val acquBankCd: String? = null
    val acquirerData: JsonObject? = null
    val authNo: String? = null
    val recurringToken: String? = null
    val acquStatus: String? = null

    // Redirect
    val paymentURL: String? = null

}
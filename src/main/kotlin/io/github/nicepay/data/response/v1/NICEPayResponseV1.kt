package io.github.nicepay.data.response.v1

import io.github.nicepay.data.response.v1.nested.ObjectAcquirerData
import io.github.nicepay.data.response.v1.nested.ObjectData

class NICEPayResponseV1 : BaseNICEPayResponseV1() {

    val apiType: String? = null
    val tXid: String? = null
    val data: ObjectData? = null
    val resultCode:String? = null

//    CC Request Token
    val cardToken:String? = null
    val paymentType :String? = null

//    CC Payment

    val ccTransType :String? = null
    val fee :String? = null
    val description :String? = null
    val notaxAmt :String? = null
    val cardNo :String? = null
    val issuBankCd :String? = null
    val preauthToken :String? = null
    val cardExpYymm :String? = null
    val acquBankNm :String? = null
    val payMethod :String? = null
    val callbackUrl :String? = null
    val instmntMon :String? = null
    val currency :String? = null
    val issuBankNm :String? = null
    val amount :String? = null
    val goodsNm :String? = null
    val referenceNo :String? = null
    val transTm :String? = null
    val authNo :String? = null
    val preAuth :String? = null
    val vat :String? = null
    val instmntType :String? = null
    val billingNm :String? = null
    val acquBankCd :String? = null
    val transDt :String? = null
    val acquirerData: ObjectAcquirerData? = null
    val acquStatus: String? = null
    val recurringToken: String? = null


//    val requestDate: String? = null
//    val responseDate: String? = null
//    val requestURL: String? = null
//
//    //base
//    val referenceNo: String? = null
//    val payMethod: String? = null
//    val amt: String? = null
//    val transDt: String? = null
//    val transTm: String? = null
//    val currency: String? = null
//    val goodsNm: String? = null
//    val billingNm: String? = null
//    val mitraCd: String? = null
//
//    //va
    val bankCd: String? = null
    val vacctNo: String? = null
    val vacctValidDt: String? = null
    val bankVacctNo: String? = null
    val vacctValidTm: String? = null

//
    //CVS
    val payNo: String? = null
    val mitraCd: String? = null
    val payValidDt: String? = null
    val payValidTm: String? = null
//
    //QRIS
    val qrContent: String? = null
    val qrUrl: String? = null

    //inquiry
    val reqDt: String? = null
    val reqTm: String? = null
    val status: String? = null
//
//    //cancel

//    // CC

//
    // Ewallet
    val paymentExpDt: String? = null
    val paymentExpTm: String? = null
    val redirectUrlHttp: String? = null
    val redirectUrlApp: String? = null
    val redirectToken: String? = null
    val paymentURL: String? = null


}
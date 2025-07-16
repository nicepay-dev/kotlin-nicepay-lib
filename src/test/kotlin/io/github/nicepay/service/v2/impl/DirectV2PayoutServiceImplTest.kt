package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PayoutService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.PayoutAction
import io.github.nicepay.utils.code.PayoutBank
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2PayoutServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2PayoutService : DirectV2PayoutService = DirectV2PayoutServiceImpl()
        private val timeStamp: String = TestingConstants.V2_TIMESTAMP

        private var config: NICEPay? = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.CLIENT_SECRET)
            .partnerId(TestingConstants.PARTNER_ID)
            .externalID(TestingConstants.EXTERNAL_ID)
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        private val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(TestingConstants.CLIENT_SECRET)
            .partnerId(TestingConstants.PARTNER_ID)
            .externalID(TestingConstants.EXTERNAL_ID)
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        private var request: DirectV2Payout? = null

        private lateinit var registeredData : NICEPayResponseV2

        private val DEFAULT_AMOUNT = "100"
        private val DEFAULT_REFERENCE_NO = "NICEPAYVA111213"
        private val DEFAULT_IMID = "IONPAYTEST"
        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun requestPayoutV2() {
        if (RUN_TEST){
            request = DirectV2Payout.Builder()
                .timeStamp(timeStamp)
                .iMid(DEFAULT_IMID)
                .bankCd(PayoutBank.MANDIRI)
                .amt(DEFAULT_AMOUNT)
                .referenceNo(DEFAULT_REFERENCE_NO)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .accountNo("1150000231250")
                .benefNm("Test")
                .benefType("1")
                .benefStatus("1")
                .benefPhone("081234567890")
                .payoutMethod("0")
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.requestPayout(request!!, config)!!

            print.logInfoV2("TXID : " + response.tXid)

            Assertions.assertNotNull(response.tXid)
            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

            registeredData = response
        } else {
            println("test skipped")
        }

    }

    @Test
    fun approvePayout() {
        if (RUN_TEST){
            requestPayoutV2()

            val request: DirectV2ApproveOrRejectPayout = DirectV2ApproveOrRejectPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.approveOrRejectPayout(request, config, PayoutAction.APPROVE)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

    @Test
    fun inquiryPayout() {
        if (RUN_TEST){
            approvePayout()

            val request: DirectV2InquiryPayout = DirectV2InquiryPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .accountNo(request!!.accountNo.toString())
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.inquiryPayout(request, config)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        } else {
            println("test skipped")
        }
    }

    @Test
    fun cancel() {
        if (RUN_TEST){
            approvePayout()

            val request : DirectV2CancelPayout = DirectV2CancelPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.cancel(request, config)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)


        } else {
            println("test skipped")
        }
    }

    @Test
    @Throws(IOException::class)
    fun requestPayoutV2Cloud() {
        if (RUN_TEST){
            request = DirectV2Payout.Builder()
                .timeStamp(timeStamp)
                .iMid(DEFAULT_IMID)
                .bankCd(PayoutBank.MANDIRI)
                .amt(DEFAULT_AMOUNT)
                .referenceNo(DEFAULT_REFERENCE_NO)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .accountNo("1150000231250")
                .benefNm("Test")
                .benefType("1")
                .benefStatus("1")
                .benefPhone("081234567890")
                .payoutMethod("0")
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.requestPayout(request!!, configCloud)!!

            print.logInfoV2("TXID : " + response.tXid)

            Assertions.assertNotNull(response.tXid)
            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

            registeredData = response
        } else {
            println("test skipped")
        }
    }

    @Test
    fun approvePayoutCloud() {
        if (RUN_TEST){
            requestPayoutV2()

            val request: DirectV2ApproveOrRejectPayout = DirectV2ApproveOrRejectPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.approveOrRejectPayout(request, configCloud, PayoutAction.APPROVE)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

    @Test
    fun inquiryPayoutCloud() {
        if (RUN_TEST){
            approvePayout()

            val request: DirectV2InquiryPayout = DirectV2InquiryPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .accountNo(request!!.accountNo.toString())
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.inquiryPayout(request, configCloud)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

    @Test
    fun cancelCloud() {
        if (RUN_TEST){
            approvePayout()

            val request : DirectV2CancelPayout = DirectV2CancelPayout.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .build()

            val response : NICEPayResponseV2 = v2PayoutService.cancel(request, configCloud)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

}
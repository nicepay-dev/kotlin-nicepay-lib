package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Qris
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.NICEPayMethod
import io.github.nicepay.utils.code.QrisMitra
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2QrisServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2QrisService : DirectV2Service<DirectV2Qris> = DirectV2QrisServiceImpl()
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

        private lateinit var registeredData : NICEPayResponseV2

        private val DEFAULT_AMOUNT = "100"
        private val DEFAULT_REFERENCE_NO = "NICEPAYVA111213" + timeStamp
        private val DEFAULT_IMID = "TNICEQR081"
        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationQrisV2() {
        val request : DirectV2Qris = DirectV2Qris.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_QRIS)
            .currency("IDR")
            .mitraCd(QrisMitra.SHOPEEPAY)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .payValidDt("")
            .payValidTm("")
            .goodsNm("Goods")
            .billingNm("NICEPAY Testing")
            .billingPhone("081363681274")
            .billingEmail("nicepay@example.com")
            .billingAddr("Jln. Raya Kasablanka Kav.88")
            .billingCity("South Jakarta")
            .billingState("DKI Jakarta")
            .billingPostCd("15119")
            .billingCountry("Indonesia")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .shopId("NICEPAY")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2QrisService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertNotNull(response.qrContent)
        Assertions.assertNotNull(response.qrUrl)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationQrisV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2QrisService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun cancel() {
        requestRegistrationQrisV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_QRIS)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2QrisService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationQrisV2Cloud() {
        val request : DirectV2Qris = DirectV2Qris.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_QRIS)
            .currency("IDR")
            .mitraCd(QrisMitra.SHOPEEPAY)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .payValidDt("")
            .payValidTm("")
            .goodsNm("Goods")
            .billingNm("NICEPAY Testing")
            .billingPhone("081363681274")
            .billingEmail("nicepay@example.com")
            .billingAddr("Jln. Raya Kasablanka Kav.88")
            .billingCity("South Jakarta")
            .billingState("DKI Jakarta")
            .billingPostCd("15119")
            .billingCountry("Indonesia")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .shopId("NICEPAY")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2QrisService.registration(request, configCloud)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertNotNull(response.qrContent)
        Assertions.assertNotNull(response.qrUrl)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatusCloud() {
        requestRegistrationQrisV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2QrisService.checkStatus(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun cancelCloud() {
        requestRegistrationQrisV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_QRIS)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2QrisService.cancel(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}
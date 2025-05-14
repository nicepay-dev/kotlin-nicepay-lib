package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Cvs
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.ConvinienceStoreMitra
import io.github.nicepay.utils.code.NICEPayMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2CvsServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2CvsService : DirectV2Service<DirectV2Cvs> = DirectV2CvsServiceImpl()
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
        private val DEFAULT_REFERENCE_NO = "NICEPAYVA111213"
        private val DEFAULT_IMID = "IONPAYTEST"
        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationCvsV2() {
        val request : DirectV2Cvs = DirectV2Cvs.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_CONVINIENCE_STORE)
            .currency("IDR")
            .mitraCd(ConvinienceStoreMitra.INDOMART)
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
            .merFixAcctId("")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2CvsService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)
        print.logInfoV2("VA : " + response.vacctNo)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertNotNull(response.payNo)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationCvsV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2CvsService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun cancel() {
        requestRegistrationCvsV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_CONVINIENCE_STORE)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2CvsService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationCvsV2Cloud() {
        val request : DirectV2Cvs = DirectV2Cvs.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_CONVINIENCE_STORE)
            .currency("IDR")
            .mitraCd(ConvinienceStoreMitra.INDOMART)
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
            .merFixAcctId("")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2CvsService.registration(request, configCloud)!!

        print.logInfoV2("TXID : " + response.tXid)
        print.logInfoV2("VA : " + response.vacctNo)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertNotNull(response.payNo)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatusCloud() {
        requestRegistrationCvsV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2CvsService.checkStatus(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun cancelCloud() {
        requestRegistrationCvsV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_CONVINIENCE_STORE)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2CvsService.cancel(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}
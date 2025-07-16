package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2Card
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2RequestPaymentCard
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.NICEPayMethod
import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2CardServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2CardService : DirectV2Service<DirectV2Card> = DirectV2CardServiceImpl()

        private val paymentService : DirectV2PaymentService<DirectV2RequestPaymentCard> = DirectV2PaymentCardServiceImpl()

        private val timeStamp: String = TestingConstants.V2_TIMESTAMP

        var config: NICEPay? = NICEPay.Builder()
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

        lateinit var registeredData : NICEPayResponseV2

        private val DEFAULT_AMOUNT = "100"
        private val DEFAULT_REFERENCE_NO = "NICEPAYVA111213"
        private val DEFAULT_IMID = "TESTMPGS05"
        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationCardV2() {
        val request: DirectV2Card = DirectV2Card.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_CARD)
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
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
            .userIP("127.0.0.1")
            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
            .userLanguage("en")
            .instmntType("1")
            .instmntMon("1")
            .recurrOpt("")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2CardService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)

//        Assertions.assertNotNull(response.tXid)
//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationCardV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response: NICEPayResponseV2 = v2CardService.checkStatus(request,
            config
        )!!

//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun payment() {
        requestRegistrationCardV2()

        val request : DirectV2RequestPaymentCard = DirectV2RequestPaymentCard.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .callBakUrl((config?.getNICEPayBaseUrl()) + "/IONPAY_CLIENT/paymentResult.jsp")
            .cardNo("5123450000000008")
            .cardCvv("123")
            .cardExpYymm("2512")
            .build()

        val response = paymentService.registration(request, config)!!

//        Assertions.assertNotNull(response)
//        Assertions.assertNotEquals(Strings.EMPTY, response)

//        TestingConstants.openHtmlInBrowser(response, registeredData.tXid.toString())
    }

    @Test
    fun cancel() {
        payment()

//        Thread.sleep(20000)

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod("02")
            .cancelType("1")
            .build()

        val response: NICEPayResponseV2 = v2CardService.cancel(request, config)!!

//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationCardV2Cloud() {
        val request: DirectV2Card = DirectV2Card.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_CARD)
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
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
            .userIP("127.0.0.1")
            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
            .userLanguage("en")
            .instmntType("1")
            .instmntMon("1")
            .recurrOpt("")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

//        val response : NICEPayResponseV2 = v2CardService.registration(request, configCloud)!!

//        print.logInfoV2("TXID : " + response.tXid)

//        Assertions.assertNotNull(response.tXid)
//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

//        registeredData = response
    }

    @Test
    fun checkStatusCloud() {
        requestRegistrationCardV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

//        val response: NICEPayResponseV2 = v2CardService.checkStatus(request, configCloud)!!

//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun paymentCloud() {
        requestRegistrationCardV2()

        val request : DirectV2RequestPaymentCard = DirectV2RequestPaymentCard.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .callBakUrl((config?.getNICEPayBaseUrl()) + "/IONPAY_CLIENT/paymentResult.jsp")
            .cardNo("5123450000000008")
            .cardCvv("123")
            .cardExpYymm("2512")
            .build()

        val response = paymentService.registration(request, configCloud)!!

//        Assertions.assertNotNull(response)
//        Assertions.assertNotEquals(Strings.EMPTY, response)

//        TestingConstants.openHtmlInBrowser(response, registeredData.tXid.toString())
    }

    @Test
    fun cancelCloud() {
        payment()

//        Thread.sleep(20000)

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod("02")
            .cancelType("1")
            .build()

//        val response: NICEPayResponseV2 = v2CardService.cancel(request, configCloud)!!

//        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}
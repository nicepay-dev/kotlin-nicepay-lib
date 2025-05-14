package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.cart.CartData
import io.github.nicepay.data.cart.CartItem
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Ewallet
import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.EwalletMitra
import io.github.nicepay.utils.code.NICEPayMethod
import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2EwalletServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2EwalletService : DirectV2Service<DirectV2Ewallet> = DirectV2EwalletServiceImpl()

        private val paymentService : DirectV2PaymentService<DirectV2RequestPaymentEwallet> = DirectV2PaymentEwalletServiceImpl()

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
    fun requestRegistrationEwalletV2() {
        val request : DirectV2Ewallet = DirectV2Ewallet.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_EWALLET)
            .currency("IDR")
            .mitraCd(EwalletMitra.DANA)
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
            .cartData(
                CartData.Builder()
                    .count("1")
                    .item(
                        listOf(
                            CartItem.Builder()
                                .goodsId("BB12345678")
                                .imgUrl("https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium")
                                .goodsName("Nokia 3360")
                                .goodsDetail("Old Nokia 3360")
                                .goodsAmt(DEFAULT_AMOUNT)
                                .goodsType("Smartphone")
                                .goodsUrl("http://merchant.com/cellphones/iphone5s_64g")
                                .goodsQuantity("1")
                                .goodsSellersId("goods_sellers_id")
                                .goodsSellersName("Sellers1")
                                .build()
                        )
                    )
                    .build()
            )
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationEwalletV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun payment() {
        requestRegistrationEwalletV2()

        val request : DirectV2RequestPaymentEwallet = DirectV2RequestPaymentEwallet.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .callBakUrl((config?.getNICEPayBaseUrl()) + "/IONPAY_CLIENT/paymentResult.jsp")
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : String = paymentService.registration(request, config)!!

        Assertions.assertNotNull(response)
        Assertions.assertNotEquals(Strings.EMPTY, response)

        TestingConstants.openHtmlEwalletV2InBrowser(response, registeredData.tXid.toString())
    }

    @Test
    fun cancel() {
        payment()

        Thread.sleep(20000)

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_EWALLET)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationEwalletV2Cloud() {
        val request : DirectV2Ewallet = DirectV2Ewallet.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_EWALLET)
            .currency("IDR")
            .mitraCd(EwalletMitra.DANA)
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
            .cartData(
                CartData.Builder()
                    .count("1")
                    .item(
                        listOf(
                            CartItem.Builder()
                                .goodsId("BB12345678")
                                .imgUrl("https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium")
                                .goodsName("Nokia 3360")
                                .goodsDetail("Old Nokia 3360")
                                .goodsAmt(DEFAULT_AMOUNT)
                                .goodsType("Smartphone")
                                .goodsUrl("http://merchant.com/cellphones/iphone5s_64g")
                                .goodsQuantity("1")
                                .goodsSellersId("goods_sellers_id")
                                .goodsSellersName("Sellers1")
                                .build()
                        )
                    )
                    .build()
            )
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.registration(request, configCloud)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatusCloud() {
        requestRegistrationEwalletV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.checkStatus(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun paymentCloud() {
        requestRegistrationEwalletV2()

        val request : DirectV2RequestPaymentEwallet = DirectV2RequestPaymentEwallet.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .callBakUrl((config?.getNICEPayBaseUrl()) + "/IONPAY_CLIENT/paymentResult.jsp")
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : String = paymentService.registration(request, configCloud)!!

        Assertions.assertNotNull(response)
        Assertions.assertNotEquals(Strings.EMPTY, response)

        TestingConstants.openHtmlEwalletV2InBrowser(response, registeredData.tXid.toString())
    }

    @Test
    fun cancelCloud() {
        payment()

        Thread.sleep(20000)

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_EWALLET)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.cancel(request, configCloud)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}
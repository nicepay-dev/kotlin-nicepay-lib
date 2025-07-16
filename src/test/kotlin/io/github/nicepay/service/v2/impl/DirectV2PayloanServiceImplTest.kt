package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import io.github.nicepay.data.cart.CartData
import io.github.nicepay.data.cart.CartItem
import io.github.nicepay.data.cart.SellersAddress
import io.github.nicepay.data.cart.SellersData
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Payloan
import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.NICEPayMethod
import io.github.nicepay.utils.code.PayloanMitra
import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2PayloanServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2PayloanService : DirectV2Service<DirectV2Payloan> = DirectV2PayloanServiceImpl()

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

        private val SELLER_ID_SHOULD_BE_EQUAL = "SEL123"
        private val SELLER_NAME_SHOULD_BE_EQUAL = "Sellers1"
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationPayloanV2() {
        if (RUN_TEST){

            val request : DirectV2Payloan = DirectV2Payloan.Builder()
                .timeStamp(timeStamp)
                .iMid(DEFAULT_IMID)
                .payMethod(NICEPayMethod.PAY_METHOD_PAYLOAN)
                .currency("IDR")
                .mitraCd(PayloanMitra.AKULAKU)
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
                                    .goodsSellersId(SELLER_ID_SHOULD_BE_EQUAL)
                                    .goodsSellersName(SELLER_NAME_SHOULD_BE_EQUAL)
                                    .build()
                            )
                        )
                        .build()
                )
                .sellers(
                    listOf(
                        SellersData.Builder()
                            .sellersId(SELLER_ID_SHOULD_BE_EQUAL)
                            .sellersNm(SELLER_NAME_SHOULD_BE_EQUAL)
                            .sellersEmail("sellers@test.com")
                            .sellersUrl("http://nicestore.store")
                            .sellersAddress(
                                SellersAddress.Builder()
                                    .sellerNm("Sellers")
                                    .sellerLastNm("1")
                                    .sellerAddr("Jl. Kota Kasablanka")
                                    .sellerCity("Jakarta Selatan")
                                    .sellerPostCd("12344")
                                    .sellerPhone("081234567890")
                                    .sellerCountry("ID")
                                    .build()
                            )
                            .build()
                    )
                )
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .deliveryNm("Nicepay Test Delivery Name")
                .deliveryPhone("081234567890")
                .deliveryAddr("EightyEight@Kota Kasablanka, 29th Floor")
                .deliveryCity("Jakarta")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12140")
                .deliveryCountry("Indonesia")
                .instmntType("1")
                .instmntMon("1")
                .recurrOpt("")
                .build()

            val response : NICEPayResponseV2 = v2PayloanService.registration(request, config)!!

            print.logInfoV2("TXID : " + response.tXid)

            Assertions.assertNotNull(response.tXid)
            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

            registeredData = response
        } else {
            println("test skipped")
        }
    }

    @Test
    fun checkStatus() {
        if (RUN_TEST){
            requestRegistrationPayloanV2()

            val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .referenceNo(DEFAULT_REFERENCE_NO)
                .amt(DEFAULT_AMOUNT)
                .build()

            val response : NICEPayResponseV2 = v2PayloanService.checkStatus(request, config)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

    @Test
    fun payment() {
        if (RUN_TEST){
            requestRegistrationPayloanV2()

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
        } else {
            println("test skipped")
        }
    }

    @Test
    fun cancel() {
        if (RUN_TEST){
            requestRegistrationPayloanV2()

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
        } else {
            println("test skipped")
        }
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationPayloanV2Cloud() {
        if (RUN_TEST){
            val request : DirectV2Payloan = DirectV2Payloan.Builder()
                .timeStamp(timeStamp)
                .iMid(DEFAULT_IMID)
                .payMethod(NICEPayMethod.PAY_METHOD_PAYLOAN)
                .currency("IDR")
                .mitraCd(PayloanMitra.AKULAKU)
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
                                    .goodsSellersId(SELLER_ID_SHOULD_BE_EQUAL)
                                    .goodsSellersName(SELLER_NAME_SHOULD_BE_EQUAL)
                                    .build()
                            )
                        )
                        .build()
                )
                .sellers(
                    listOf(
                        SellersData.Builder()
                            .sellersId(SELLER_ID_SHOULD_BE_EQUAL)
                            .sellersNm(SELLER_NAME_SHOULD_BE_EQUAL)
                            .sellersEmail("sellers@test.com")
                            .sellersUrl("http://nicestore.store")
                            .sellersAddress(
                                SellersAddress.Builder()
                                    .sellerNm("Sellers")
                                    .sellerLastNm("1")
                                    .sellerAddr("Jl. Kota Kasablanka")
                                    .sellerCity("Jakarta Selatan")
                                    .sellerPostCd("12344")
                                    .sellerPhone("081234567890")
                                    .sellerCountry("ID")
                                    .build()
                            )
                            .build()
                    )
                )
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .deliveryNm("Nicepay Test Delivery Name")
                .deliveryPhone("081234567890")
                .deliveryAddr("EightyEight@Kota Kasablanka, 29th Floor")
                .deliveryCity("Jakarta")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12140")
                .deliveryCountry("Indonesia")
                .instmntType("1")
                .instmntMon("1")
                .recurrOpt("")
                .build()

            val response : NICEPayResponseV2 = v2PayloanService.registration(request, configCloud)!!

            print.logInfoV2("TXID : " + response.tXid)

            Assertions.assertNotNull(response.tXid)
            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

            registeredData = response
        } else {
            println("test skipped")
        }
    }

    @Test
    fun checkStatusCloud() {
        if (RUN_TEST){
            requestRegistrationPayloanV2()

            val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .referenceNo(DEFAULT_REFERENCE_NO)
                .amt(DEFAULT_AMOUNT)
                .build()

            val response : NICEPayResponseV2 = v2PayloanService.checkStatus(request, configCloud)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        } else {
            println("test skipped")
        }
    }

    @Test
    fun paymentCloud() {
        if (RUN_TEST){
            requestRegistrationPayloanV2()

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
        } else {
            println("test skipped")
        }
    }

    @Test
    fun cancelCloud() {
        if (RUN_TEST){
            payment()

            val request : DirectV2Cancel = DirectV2Cancel.Builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(registeredData.tXid!!)
                .iMid(DEFAULT_IMID)
                .merchantKey(DEFAULT_MERCHANT_KEY)
                .referenceNo(DEFAULT_REFERENCE_NO)
                .amt(DEFAULT_AMOUNT)
                .payMethod(NICEPayMethod.PAY_METHOD_PAYLOAN)
                .cancelType("1")
                .build()

            val response : NICEPayResponseV2 = v2PayloanService.cancel(request, configCloud)!!

            Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        } else {
            println("test skipped")
        }
    }

}
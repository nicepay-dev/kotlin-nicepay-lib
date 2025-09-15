package io.github.nicepay.service.v1

import DirectV1CardServiceImpl
import com.google.gson.Gson
import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.PARTNER_ID
import io.github.nicepay.data.model.DirectV1Card
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class DirectV1CardTest {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private lateinit var registeredData: NICEPayResponse

        private val DEFAULT_AMOUNT = "1000"
        private val DEFAULT_REFERENCE_NO = "OrdNo"+ TIMESTAMP
        private val IS_CLOUD_SERVER = false;
        private var I_MID = PARTNER_ID;
        private val IS_PRODUCTION = false;

        private val DEFAULT_MERCHANT_KEY = CLIENT_SECRET
        private val DEFAULT_CARD_NO = ""
        private var cardToken : String? = ""
        private var recurringToken : String?= ""

        val ccService: DirectV1CardService = DirectV1CardServiceImpl()
    }

    @Test
    fun `Should be able to Request Token V1 Transaction`() {

        val ccService: DirectV1CardService = DirectV1CardServiceImpl()
        val config = generateConfig()

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .cardNo(DEFAULT_CARD_NO)
            .cardExpYymm("2703")
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response: NICEPayResponseV1? = ccService.requestToken(cardRequest, config)

        assertNotNull(response)
//
        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)
//        if (response != null) {
//
////            assertEquals("SUCCESS", response.resultMsg)
//        }
    }

    private fun generateConfig(): NICEPay {

        val config: NICEPay = NICEPay.Builder()
            .isProduction(IS_PRODUCTION)
            .isCloudServer(IS_CLOUD_SERVER)
            .partnerId(I_MID)
            .build()

        return config
    }

    @Test
    fun `Should be able to Request Payment Card 3DS Transaction`() {
        val config = generateConfig()

        val cardToken : NICEPayResponseV1? = generateRequestToken(config)

        val cardRequest = DirectV1Card.Builder()
            .country("360")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .onePassToken(cardToken?.cardToken)
            .build()

        val response = ccService.paymentCard3DS(cardRequest, config)

//        TestingConstants.openHtmlEwalletV2InBrowser(response, "test")


    }

    @Test
    fun `Should be able to Request Payment Card MIGS Transaction`() {
        val config = generateConfig()

        val cardToken : NICEPayResponseV1? = generateRequestToken(config)

        val cardRequest = DirectV1Card.Builder()
            .country("360")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .onePassToken(cardToken?.cardToken)
            .build()

        val response = ccService.paymentCardMigs(cardRequest, config)

//        TestingConstants.openHtmlEwalletV2InBrowser(response, "test")


    }

    @Test
    fun `Should be able to Request Payment Card Transaction`() {
        val config = generateConfig()

        val cardToken : NICEPayResponseV1? = generateRequestToken(config)

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .payMethod("01")
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .goodsNm("Kotlin testing V1")
            .billingNm("Kotlin Nicepay")
            .billingPhone("081234567890")
            .billingAddr("Jl Ninja")
            .billingEmail("nicepay@email.com")
            .billingCity("Kota selatan")
            .billingState("Bukan Ibukota")
            .billingPostCd("12170")
            .billingCountry("Indonesia")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .dbProcessUrl( "https://www.merchant.com/dbProcessUrl/")
            .description("Testing Card Payment V1 with Kotlin")
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .onePassToken(cardToken?.cardToken)
            .userIP("127.0.0.1")
            .cartData("{}")
            .instmntType("1")
            .instmntMon("1")
            .cardCvv("100")
            .recurrOpt("1")
            .build()

        val response : NICEPayResponseV1? = ccService.paymentCard(cardRequest, config)

        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)


    }

    @Test
    fun `Should be able to issue recurring token`() {
        val config = generateConfig()

        val reqCardToken : NICEPayResponseV1? = generateRequestToken(config)
        cardToken = reqCardToken?.cardToken

        val resCardPayment : NICEPayResponseV1? = generateCardPayment(config)
        recurringToken = resCardPayment?.recurringToken

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .recurringToken(recurringToken)
            .instmntType("1")
            .instmntMon("1")
            .cardHolderNm("Nicepay Testing")
            .cardHolderEmail("nicepay@email.com")
            .build()


        val response : NICEPayResponseV1? = ccService.recurringIssue(cardRequest, config)

        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)


    }

    @Test
    fun `Should be able to payment recurring`() {
        val config = generateConfig()

        val reqCardToken : NICEPayResponseV1? = generateRequestToken(config)
        cardToken = reqCardToken?.cardToken

        val resCardPayment : NICEPayResponseV1? = generateCardPayment(config)
        recurringToken = resCardPayment?.recurringToken

        val resRecurrTokenIssue : NICEPayResponseV1? = generateRecurringIssue(config)
        cardToken = resRecurrTokenIssue?.cardToken

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .payMethod("01")
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .goodsNm("Kotlin testing V1")
            .billingNm("Kotlin Nicepay")
            .billingPhone("081234567890")
            .billingAddr("Jl Ninja")
            .billingEmail("nicepay@email.com")
            .billingCity("Kota selatan")
            .billingState("Bukan Ibukota")
            .billingPostCd("12170")
            .billingCountry("Indonesia")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .dbProcessUrl( "https://www.merchant.com/dbProcessUrl/")
            .description("Testing Card Payment V1 with Kotlin")
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .recurringToken(recurringToken)
            .userIP("127.0.0.1")
            .cartData("{}")
            .instmntType("1")
            .instmntMon("1")
            .cardCvv("100")
            .recurrOpt("1")
            .build()

        val response : NICEPayResponseV1? = ccService.recurringTrans(cardRequest, config)


        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)

    }

    @Test
    fun `Should be able to generate recurring token without first payment`() {
        val config = generateConfig()

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .cardNo(DEFAULT_CARD_NO)
            .cardExpYymm("2703")
            .billingNm("Nicepay Kotlin")
            .cardHolderNm("Nicepay Kotlin")
            .cardHolderEmail("nicepay@email.com")
            .merchantToken(config.partnerId, DEFAULT_CARD_NO, "2703", DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV1? = ccService.tokenizedRecurringCard(cardRequest, config)


        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)

    }

    @Test
    fun `Should be able to check recurring token`() {
        val config = generateConfig()

        val resGenerateToken : NICEPayResponseV1? = generateRecurringTokenWithoutFirstPay(config)
        recurringToken = resGenerateToken?.recurringToken

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .merchantToken(config.partnerId, recurringToken, DEFAULT_MERCHANT_KEY)
            .recurringToken(recurringToken)
            .build()

        val response : NICEPayResponseV1? = ccService.checkToken(cardRequest, config)

        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)

    }

    @Test
    fun `Should be able to remove recurring token`() {
        val config = generateConfig()

        val resGenerateToken : NICEPayResponseV1? = generateRecurringTokenWithoutFirstPay(config)
        recurringToken = resGenerateToken?.recurringToken

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .merchantToken(config.partnerId, recurringToken, DEFAULT_MERCHANT_KEY)
            .recurringToken(recurringToken)
            .build()

        val response : NICEPayResponseV1? = ccService.removeToken(cardRequest, config)

        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)

    }

    @Test
    fun `Should be able to capture with preauth token`() {
        I_MID = "TESTMPGS05"
        val config = generateConfig()

        val preauthToken = "420ace09a8b54bfefdcd82e56d771ba98644e4f4aef9c22525de80895b282879"

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .payMethod("01")
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .goodsNm("Kotlin testing V1")
            .billingNm("Kotlin Nicepay")
            .billingPhone("081234567890")
            .billingAddr("Jl Ninja")
            .billingEmail("nicepay@email.com")
            .billingCity("Kota selatan")
            .billingState("Bukan Ibukota")
            .billingPostCd("12170")
            .billingCountry("Indonesia")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .dbProcessUrl( "https://www.merchant.com/dbProcessUrl/")
            .description("Testing Card Payment V1 with Kotlin")
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .userIP("127.0.0.1")
            .cartData("{}")
            .instmntType("1")
            .instmntMon("1")
            .preauthToken("420ace09a8b54bfefdcd82e56d771ba98644e4f4aef9c22525de80895b282879")
            .build()

//        iMid: "TESTMPGS05"
//        payMethod: "01"
//        currency: "IDR"
//        amt: "10000"
//        referenceNo: "OrdNo20250904020994"
//        goodsNm: "TESTING CREDIT CARD V1 DIRECT - BEDOEL"
//        billingNm: "Hantu Kesorean"
//        billingPhone: "085167036063"
//        billingEmail: "abdul@example.com"
//        billingAddr: "Jln.Raya Kasablanka Kav.88"
//        billingCity: "South Jakarta"
//        billingState: "DKI Jakarta"
//        billingPostCd: "10200"
//        billingCountry: "Indonesia"
//        callBackUrl: "https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp"
//        dbProcessUrl: "https://webhook.site/e15ef201-98a9-428c-85d4-a0c6458939c3"
//        description: "This Is Testing Transaction"
//        merchantToken: "d85ecbb5845eb3dde9e96a23e9e90727dc9d517dd5c9e857260c805c4a3f4e92"
//        userIP: "::1"
//        cartData: "{}"
//        instmntType: "1"
//        instmntMon: "1"
//        preauthToken: "420ace09a8b54bfefdcd82e56d771ba98644e4f4aef9c22525de80895b282879"

        val response : NICEPayResponseV1? = ccService.capturePreAuth(cardRequest, config)

        val gson = Gson()
        val jsonString = gson.toJson(response)
        println("Response nih : " + jsonString)

    }

    fun generateRecurringTokenWithoutFirstPay(config: NICEPay) : NICEPayResponseV1?{

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .cardNo(DEFAULT_CARD_NO)
            .cardExpYymm("2703")
            .billingNm("Nicepay Kotlin")
            .cardHolderNm("Nicepay Kotlin")
            .cardHolderEmail("nicepay@email.com")
            .merchantToken(config.partnerId, DEFAULT_CARD_NO, "2703", DEFAULT_MERCHANT_KEY)
            .build()

        return ccService.tokenizedRecurringCard(cardRequest, config)

    }

    fun generateRecurringIssue(config: NICEPay) : NICEPayResponseV1?{

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .recurringToken(recurringToken)
            .instmntType("1")
            .instmntMon("1")
            .cardHolderNm("Nicepay Testing")
            .cardHolderEmail("nicepay@email.com")
            .build()


        return ccService.recurringIssue(cardRequest, config)

    }

    fun generateRequestToken(config:NICEPay) : NICEPayResponseV1?{


        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .cardNo(DEFAULT_CARD_NO)
            .cardExpYymm("2703")
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        return ccService.requestToken(cardRequest, config)
    }

    fun generateCardPayment(config: NICEPay) : NICEPayResponseV1?{

        val cardRequest = DirectV1Card.Builder()
            .iMid(config.partnerId)
            .payMethod("01")
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .goodsNm("Kotlin testing V1")
            .billingNm("Kotlin Nicepay")
            .billingPhone("081234567890")
            .billingAddr("Jl Ninja")
            .billingEmail("nicepay@email.com")
            .billingCity("Kota selatan")
            .billingState("Bukan Ibukota")
            .billingPostCd("12170")
            .billingCountry("Indonesia")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .dbProcessUrl( "https://www.merchant.com/dbProcessUrl/")
            .description("Testing Card Payment V1 with Kotlin")
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT, DEFAULT_MERCHANT_KEY)
            .onePassToken(cardToken)
            .userIP("127.0.0.1")
            .cartData("{}")
            .instmntType("1")
            .instmntMon("1")
            .cardCvv("100")
            .recurrOpt("1")
            .build()

        return ccService.paymentCard(cardRequest, config)

    }
}
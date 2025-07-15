package io.github.nicepay.service.snap.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.I_MID_NORMALTEST
import io.github.nicepay.data.TestingConstants.Companion.NORMALTEST_CLOUD_PRIVATE_KEY
import io.github.nicepay.data.TestingConstants.Companion.TIMESTAMP
import io.github.nicepay.data.TestingConstants.Companion.generateExternalId
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapPayout
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.service.snap.SnapPayoutService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.io.IOException
import kotlin.test.Test

class SnapPayoutTest {

    companion object {

        private val snapPayoutService : SnapPayoutService = SnapPayoutServiceImpl()
        private val print: LoggerPrint = LoggerPrint()

        private val timeStamp: String = TestingConstants.V2_TIMESTAMP
        private val DEFAULT_AMOUNT = "10000.00"
        private val DEFAULT_REFERENCE_NO = "kotlinPayout"+ timeStamp
        private val IS_CLOUD_SERVER = false;
        private val BENEFICIARY_ACCOUNT_NO = "800152779200"
        private val BENEFICIARY_BANK_CD = "BNIA"

    }

    @Test
    fun `Should be able to generate Payout transaction`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")

        val payout = SnapPayout.Builder()
            .merchantId(config.partnerId!!)
            .beneficiaryAccountNo(BENEFICIARY_ACCOUNT_NO)
            .beneficiaryName("IONPAY NETWORKS")
            .beneficiaryCustomerResidence("1")
            .beneficiaryCustomerType("1")
            .beneficiaryPostalCode("123456")
            .beneficiaryPhone("081234567890")
            .payoutMethod("0")
            .beneficiaryBankCode(BENEFICIARY_BANK_CD)
            .amount(DEFAULT_AMOUNT, "IDR")
            .partnerReferenceNo(DEFAULT_REFERENCE_NO)
            .description("Payout registration test on Kotlin Library")
            .deliveryName("Kotlin Lib")
            .deliveryId("1234567890234512")
            .reservedDt("")
            .reservedTm("")
            .build()

        val response = snapPayoutService.registration(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2000000", response.responseCode)
        }
    }

    @Test
    fun `Should be able to approve Payout transaction`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")

        val payoutTransaction = createPayoutTransaction(accessToken);

        val payout = SnapPayout.Builder()
            .merchantId(config.partnerId!!)
            .originalReferenceNo(payoutTransaction!!.originalReferenceNo!!)
            .originalPartnerReferenceNo(payoutTransaction.partnerReferenceNo!!)
            .build()

        val response = snapPayoutService.approve(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2000000", response.responseCode)
        }
    }

    @Test
    fun `Should be able to reject Payout transaction`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")

        val payoutTransaction = createPayoutTransaction(accessToken);

        val payout = SnapPayout.Builder()
            .merchantId(config.partnerId!!)
            .originalReferenceNo(payoutTransaction!!.originalReferenceNo!!)
            .originalPartnerReferenceNo(payoutTransaction.partnerReferenceNo!!)
            .beneficiaryAccountNo(BENEFICIARY_ACCOUNT_NO)
            .build()

        val response = snapPayoutService.reject(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2000000", response.responseCode)
        }
    }

    @Test
    fun `Should be able to check payout balance`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")


        val payout = SnapPayout.Builder()
            .accountNo(config.partnerId!!)
            .additionalInfo("")
            .build()

        val response = snapPayoutService.balanceInquiry(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2001100", response.responseCode)
        }
    }


    @Test
    fun `Should be able to check Payout transaction status`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")

        val payoutTransaction = createApprovedPayoutTransaction(accessToken);

        val payout = SnapCheckStatus.Builder()
            .merchantId(config.partnerId!!)
            .originalReferenceNo(payoutTransaction!!.originalReferenceNo!!)
            .originalPartnerReferenceNo(payoutTransaction.originalPartnerReferenceNo!!)
            .beneficiaryAccountNo(payoutTransaction.beneficiaryAccountNo!!)
            .build()

        val response = snapPayoutService.statusInquiry(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2000000", response.responseCode)
        }
    }

    @Test
    fun `Should be able to cancel Payout transaction`(){
        val config = generateConfig(IS_CLOUD_SERVER)

        val accessToken = (getToken(config) as? NICEPayResponse)
            ?.accessToken
            ?: throw IllegalArgumentException("Token is null")

        val payoutTransaction = createApprovedPayoutTransaction(accessToken);

        val payout = SnapCancel.Builder()
            .merchantId(config.partnerId!!)
            .originalReferenceNo(payoutTransaction!!.originalReferenceNo!!)
            .originalPartnerReferenceNo(payoutTransaction.originalPartnerReferenceNo!!)
            .build()

        val response = snapPayoutService.cancel(payout, accessToken, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("Successful", response.responseMessage)
            assertEquals("2000000", response.responseCode)
        }
    }




    @Throws(IOException::class)
    fun getToken(config: NICEPay): Any? {
        val service: SnapAccessTokenService = SnapAccessTokenServiceImpl()

        val request = AccessToken.Builder()
            .grantType("client_credentials")
            .additionalInfo(emptyMap())
            .build()

        return service.getAccessToken(request, config)
    }

    fun generateConfig(isCloud : Boolean) : NICEPay {
        var config: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.NORMALTEST_CLIENT_SECRET)
            .partnerId(TestingConstants.I_MID_NORMALTEST)
            .externalID(TestingConstants.generateExternalId())
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(CLIENT_SECRET)
            .partnerId(I_MID_NORMALTEST)
            .externalID(generateExternalId())
            .timestamp(TIMESTAMP)
            .privateKey(NORMALTEST_CLOUD_PRIVATE_KEY)
            .build()

        if(isCloud){
            return configCloud
        } else {
            return config
        }
    }

    fun createPayoutTransaction(accessToken:String) : NICEPayResponse? {

        val config : NICEPay = generateConfig(IS_CLOUD_SERVER)
        val reservedDate:String = TestingConstants.V2_TIMESTAMP_ADD_1D

        val payout = SnapPayout.Builder()
            .merchantId(config.partnerId!!)
            .beneficiaryAccountNo(BENEFICIARY_ACCOUNT_NO)
            .beneficiaryName("IONPAY NETWORKS")
            .beneficiaryCustomerResidence("1")
            .beneficiaryCustomerType("1")
            .beneficiaryPostalCode("123456")
            .beneficiaryPhone("081234567890")
            .payoutMethod("0")
            .beneficiaryBankCode(BENEFICIARY_BANK_CD)
            .amount(DEFAULT_AMOUNT, "IDR")
            .partnerReferenceNo(DEFAULT_REFERENCE_NO)
            .description("Payout registration test on Kotlin Library")
            .deliveryName("Kotlin Lib")
            .deliveryId("1234567890234512")
            .reservedDt(reservedDate.substring(0,8))
            .reservedTm(reservedDate.substring(8,14))
            .build()

        return snapPayoutService.registration(payout, accessToken, config)

    }

    fun createApprovedPayoutTransaction(accessToken:String): NICEPayResponse? {

        val payoutTransaction = createPayoutTransaction(accessToken)
        val config:NICEPay = generateConfig(IS_CLOUD_SERVER)

        val payout = SnapPayout.Builder()
            .merchantId(config.partnerId!!)
            .originalReferenceNo(payoutTransaction!!.originalReferenceNo!!)
            .originalPartnerReferenceNo(payoutTransaction.partnerReferenceNo!!)
            .build()

        return snapPayoutService.approve(payout, accessToken, config)

    }




}
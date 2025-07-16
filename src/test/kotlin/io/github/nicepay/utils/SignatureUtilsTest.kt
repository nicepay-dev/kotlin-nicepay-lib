package io.github.nicepay.utils

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SignatureUtilsTest {

    @Test
    fun verify() {
        val signatureString =
            TestingConstants.SAMPLE_SIGNATURE_STRING
        val dataString = "IONPAYTEST|2024-12-10T14:14:15+07:00" // Sample notification signature to verify
        val publicKeyString: String = TestingConstants.PUBLIC_KEY

        if (RUN_TEST){
            val isVerify : Boolean = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString)
            println("Is verified "+ isVerify)
            assertTrue { isVerify }
        } else {
            println("test skipped")
        }
    }


    @Test
    fun testGenerateSignature() {
        val dataString = "IONPAYTEST|2024-12-10T14:14:15+07:00"
        val privateKey = TestingConstants.PRIVATE_KEY

        if (RUN_TEST){
            val signature = SignatureUtils.signSHA256RSA(dataString, privateKey)
            println("Generated signature : " + signature)
            assertNotNull(signature)
        } else {
            println("test skipped")
        }
    }
}
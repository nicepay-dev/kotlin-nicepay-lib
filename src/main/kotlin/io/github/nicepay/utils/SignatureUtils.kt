package io.github.nicepay.utils

import org.apache.commons.codec.binary.Hex
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SignatureUtils {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        fun signSHA256RSA(stringTosign: String, privateKey: String?): String {
            var s: ByteArray? = null
            var hex = ""
            try {
                val b1 = Base64.getDecoder().decode(privateKey)
                val kf = KeyFactory.getInstance("RSA")
                val spec = PKCS8EncodedKeySpec(b1)
                val privateSignature = Signature.getInstance("SHA256withRSA")
                privateSignature.initSign(kf.generatePrivate(spec))
                privateSignature.update(stringTosign.toByteArray(StandardCharsets.UTF_8))
                s = privateSignature.sign()
                hex = Base64.getEncoder().encodeToString(s)
            } catch (e: Exception) {
                print.logError("Error Generate Signature = " + e.message)
            }
            return hex
        }

        fun getSignature(
            httpMethod: String,
            accessToken: String,
            requestBody: String,
            url: String,
            timeStamp: String,
            staticKey: String
        ): String {
            var sign = ""

            val data =
                "$httpMethod:$url:$accessToken:$requestBody:$timeStamp" //RU4/FMF2KItWkzD9z3vWYSf/RlP9gfoN8rCLQVzpDgAsOu7jwi0sYvzxzO14QtngWAUnwfWP6uD5JGmRanBBXw==
            print.logInfo("String Data Sign :$data")

            try {
                sign = hmacSha512encodeBase64(staticKey, data)
                print.logInfo("This Signature :$sign")
            } catch (e: Exception) {
                print.logError("Error Generate Signature = " + e.message)
            }

            return sign
        }

        @Throws(Exception::class)
        fun hmacSha512encodeBase64(key: String, data: String): String {
            val sha256_HMAC = Mac.getInstance("HmacSHA512")
            val secret_key = SecretKeySpec(key.toByteArray(), "HmacSHA512")
            Mac.getInstance("HmacSHA512")
            sha256_HMAC.init(secret_key)

            val strbyte = sha256_HMAC.doFinal(data.toByteArray(charset("UTF-8")))

            val str = Base64.getEncoder().encodeToString(strbyte)
            return str
        }

        @Throws(Exception::class)
        fun sha256EncodeHex(data: String): String {
            val sh = MessageDigest.getInstance("SHA-256")
            return Hex.encodeHexString(sh.digest(data.toByteArray(charset("UTF-8"))))
        }

        fun verifySHA256RSA(stringToSign: String, publicKeyString: String?, signatureString: String?): Boolean {
            var isVerified = false
            try {
                val publicKeyBytes = Base64.getDecoder().decode(publicKeyString)
                val signatureBytes = Base64.getDecoder().decode(signatureString)
                val stringToSignBytes = stringToSign.toByteArray()

                val kf = KeyFactory.getInstance("RSA")
                val spec = X509EncodedKeySpec(publicKeyBytes)
                val publicKey = kf.generatePublic(spec)

                val signature = Signature.getInstance("SHA256withRSA")

                signature.initVerify(publicKey)
                signature.update(stringToSignBytes)

                isVerified = signature.verify(signatureBytes)

                print.logInfo("Signature is " + (if (isVerified) "valid" else "invalid"))
            } catch (e: Exception) {
                print.logError("Error Generate Signature = " + e.message)
            }
            return isVerified
        }
    }

}
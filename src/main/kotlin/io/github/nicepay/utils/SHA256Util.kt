package io.github.nicepay.utils

import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SHA256Util {
    companion object {
        fun encrypt(str: String): String? {
            var SHA: String? = ""
            try {
                val sh = MessageDigest.getInstance("SHA-256")
                sh.update(str.toByteArray())
                val byteData = sh.digest()
                val sb = StringBuffer()
                for (i in byteData.indices) {
                    sb.append(((byteData[i].toInt() and 0xff) + 0x100).toString(16).substring(1))
                }
                SHA = sb.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                SHA = null
            }
            return SHA
        }
    }

    @Throws(Exception::class)
    fun sha256HMACencode(key: String, data: String): String {
        val sha256_HMAC = Mac.getInstance("HmacSHA256")
        val secret_key = SecretKeySpec(key.toByteArray(charset("UTF-8")), "HmacSHA256")
        sha256_HMAC.init(secret_key)

        return Hex.encodeHexString(sha256_HMAC.doFinal(data.toByteArray(charset("UTF-8"))))
    }

    @Throws(Exception::class)
    fun sha256EncodeHex(data: String): String {
        val sh = MessageDigest.getInstance("SHA-256")
        return Hex.encodeHexString(sh.digest(data.toByteArray(charset("UTF-8"))))
    }

    @Throws(Exception::class)
    fun hmacSha256encodeBase64(key: String, data: String): String {
        val sha256_HMAC = Mac.getInstance("HmacSHA256")
        val secret_key = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        sha256_HMAC.init(secret_key)

        val strbyte = sha256_HMAC.doFinal(data.toByteArray())

        val str = Base64.getUrlEncoder().encodeToString(strbyte)
        return str.substring(0, str.length - 1)
    }

    @Throws(Exception::class)
    fun encBase64String(inputStr: String): String {
        val str = Base64.getUrlEncoder().encodeToString(inputStr.toByteArray())
        return str.substring(0, str.length - 1)
    }
}
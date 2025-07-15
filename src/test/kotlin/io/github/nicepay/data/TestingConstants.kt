package io.github.nicepay.data

import java.awt.Desktop
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TestingConstants {

    companion object {
        private fun TestingConstants() {}


        val SAMPLE_SIGNATURE_STRING: String = ""
        var f: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")

        val TIMESTAMP: String = f.format(Date())

        var rand: Random = Random()

        var random: Int = rand.nextInt(10000)

        val EXTERNAL_ID: String =
            "OrdNo" + TIMESTAMP.substring(0, 10).replace("-", "") + TIMESTAMP.substring(11, 19).replace(":", "") + random

        val PARTNER_ID: String = ""
        val IONPAYTEST_CLOUD_PRIVATE_KEY = ""
        val CLIENT_SECRET: String = ""
        val PUBLIC_KEY: String = ""
        const val I_MID_NORMALTEST: String = ""
        const val NORMALTEST_CLIENT_SECRET: String = ""
        const val PRIVATE_KEY: String = ""
        var v2_format: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val V2_TIMESTAMP: String = v2_format.format(Date())
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val V2_TIMESTAMP_ADD_1D = LocalDateTime.now().plusDays(1).format(formatter)
        val I_MID_QRIS: String = ""
        val QRIS_CLIENT_SECRET: String = ""
        val CLOUD_PRIVATE_KEY: String = ""
        val NORMALTEST_CLOUD_PRIVATE_KEY: String = ""
        val I_MID_EWALLET: String = ""
        val EWALLET_CLIENT_SECRET: String = ""
        val DEFAULT_NICEPAY_SUCCESS_RESULT_CODE = "0000"

        fun openHtmlEwalletV2InBrowser(resClient: String, tXid: String) {
            val script = "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script> \n"
            openHtmlInBrowser(script + resClient, tXid)
        }

        fun openHtmlInBrowser(resClient: String, tXid: String) {
            //Save the HTML content to a temporary file

            val tempFile = File.createTempFile("Payment-$tXid-", ".html")
            val writer = FileWriter(tempFile)
            writer.write(resClient)
            writer.close()

            // Open page on browser
            Desktop.getDesktop().browse(tempFile.toURI())
        }

        fun generateExternalId() : String{
            var random: Int = rand.nextInt(10000)
            return "kotlinExtID" + TIMESTAMP.substring(0, 10).replace("-", "") + TIMESTAMP.substring(11, 19).replace(":", "") + random

        }
    }
}
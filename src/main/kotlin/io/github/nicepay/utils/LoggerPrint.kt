package io.github.nicepay.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerPrint {

    private val LOGGER: Logger = LoggerFactory.getLogger("[SNAP ]")
    private val LOGGER_V2: Logger = LoggerFactory.getLogger("[V2 ]")
    private val LOGGER_V1: Logger = LoggerFactory.getLogger("[V1 ]")

    fun logInfoHeader(logging: String) {
        LOGGER.info("\u001B[33m$logging\u001B[0m")
    }

    fun logInfoBody(logging: String) {
        LOGGER.info("\u001B[34m$logging\u001B[0m")
    }

    fun logInfoResponse(logging: String) {
        LOGGER.info("\u001B[35m$logging\u001B[0m")
    }

    fun logInfo(logging: String) {
        LOGGER.info("\u001B[32m$logging\u001B[0m")
    }

    fun logError(logging: String) {
        LOGGER.error("\u001B[31m$logging\u001B[0m")
    }

    //V2
    fun logInfoBodyV2(logging: String) {
        LOGGER_V2.info("\u001B[34m$logging\u001B[0m")
    }

    fun logInfoResponseV2(logging: String) {
        LOGGER_V2.info("\u001B[35m$logging\u001B[0m")
    }

    fun logInfoV2(logging: String) {
        LOGGER_V2.info("\u001B[32m$logging\u001B[0m")
    }

    fun logErrorV2(logging: String) {
        LOGGER_V2.error("\u001B[31m$logging\u001B[0m")
    }


    //    V1
    fun logInfoBodyV1(logging: String) {
        LOGGER_V1.info("\u001B[34m$logging\u001B[0m")
    }

    fun logInfoResponseV1(logging: String) {
        LOGGER_V1.info("\u001B[35m$logging\u001B[0m")
    }

    fun logInfoV1(logging: String) {
        LOGGER_V1.info("\u001B[32m$logging\u001B[0m")
    }

    fun logErrorV1(logging: String) {
        LOGGER_V1.error("\u001B[31m$logging\u001B[0m")
    }


    companion object {
        val LOG_HEADER: String = "\u001B[33m {} \u001B[0m"

        val LOG_BODY: String = "\u001B[34m {} \u001B[0m"

        val LOG_RESPONSE: String = "\u001B[35m {} \u001B[0m"

        val LOG_DEFAULT: String = "\u001B[32m {} \u001B[0m"

        val LOG_ERROR: String = "\u001B[31m {} \u001B[0m"
    }
    
}
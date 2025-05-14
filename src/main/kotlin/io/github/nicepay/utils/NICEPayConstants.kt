package io.github.nicepay.utils

class NICEPayConstants {

    companion object {
        private val SANDBOX_BASE_URL: String = "https://dev.nicepay.co.id/"
        private val PRODUCTION_BASE_URL: String = "https://www.nicepay.co.id/"

        private val AWS_SANDBOX_BASE_URL: String = "https://dev-services.nicepay.co.id"
        private val AWS_PRODUCTION_BASE_URL: String = "https://services.nicepay.co.id"

        fun getSandboxBaseUrl(isCloudServer: Boolean): String {
            return if (isCloudServer) {
                AWS_SANDBOX_BASE_URL
            } else {
                SANDBOX_BASE_URL
            }
        }

        fun getProductionBaseUrl(isCloudServer: Boolean): String {
            return if (isCloudServer) {
                AWS_PRODUCTION_BASE_URL
            } else {
                PRODUCTION_BASE_URL
            }
        }
    }

}
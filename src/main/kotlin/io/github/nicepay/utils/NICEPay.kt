package io.github.nicepay.utils

class NICEPay(
    val partnerId: String?,
    val clientSecret: String?,
    val isProduction: Boolean,
    val isCloudServer: Boolean,
    val externalID: String?,
    val timestamp: String?,
    val privateKey: String?
) {

    fun getNICEPayBaseUrl(): String {
        return if (isProduction) {
            NICEPayConstants.getProductionBaseUrl(isCloudServer)
        } else {
            NICEPayConstants.getSandboxBaseUrl(isCloudServer)
        }
    }

    override fun toString(): String {
        return "NICEPay{" +
                "partnerId='" + partnerId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", isProduction=" + isProduction +
                '}'
    }

    class Builder {

        private var partnerId: String? = null
        private var clientSecret: String? = null
        private var isProduction: Boolean = false
        private var isCloudServer: Boolean = false
        private var externalID: String? = null
        private var timestamp: String? = null
        private var privateKey: String? = null

        fun partnerId(partnerId: String) = apply { this.partnerId = partnerId }
        fun clientSecret(clientSecret: String) = apply { this.clientSecret = clientSecret }
        fun isProduction(isProduction: Boolean) = apply { this.isProduction = isProduction }
        fun isCloudServer(isCloudServer: Boolean) = apply { this.isCloudServer = isCloudServer }
        fun externalID(externalID: String?) = apply { this.externalID = externalID }
        fun timestamp(timestamp: String?) = apply { this.timestamp = timestamp }
        fun privateKey(privateKey: String?) = apply { this.privateKey = privateKey }

        fun build(): NICEPay {
            return NICEPay(
                partnerId,
                clientSecret,
                isProduction,
                isCloudServer,
                externalID,
                timestamp,
                privateKey
            )
        }
    }

}
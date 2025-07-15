package io.github.nicepay.data.model

class AccessToken private constructor(
    private val grantType: String?,
    private val additionalInfo: Map<String, String>?
) {

    fun getGrantType(): String? = grantType
    fun getAdditionalInfo(): Map<String, String>? = additionalInfo

    class Builder {
        private var grantType: String? = null
        private var additionalInfo: Map<String, String>? = null

        fun grantType(grantType: String) = apply { this.grantType = grantType }
        fun additionalInfo(additionalInfo: Map<String, String>) = apply { this.additionalInfo = additionalInfo }

        fun build(): AccessToken {

            return AccessToken(
                grantType = grantType,
                additionalInfo = additionalInfo
            )
        }
    }
}

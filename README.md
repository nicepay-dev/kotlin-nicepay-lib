# Kotlin - NICEPAY SDK

This is the **official Kotlin SDK** for integrating with the [NICEPAY Payment API](https://docs.nicepay.co.id/).  
It supports both [NICEPAY SNAP APIs](https://docs.nicepay.co.id/nicepay-api-snap) and [NICEPAY V2 APIs](https://docs.nicepay.co.id/nicepay-api-v2).

---

## 💳 Supported Payment Methods

| Method               | SNAP | V2   |
|----------------------|------|------|
| Virtual Account      | ✅   | ✅   |
| E-Wallet             | ✅   | ✅   |
| QRIS                 | ✅   | ✅   |
| Card Payment         | ❌   | ✅   |
| Convenience Store    | ❌   | ✅   |
| Payloan              | ❌   | ✅   |
| Payout / Disbursement| ✅   | ✅   |

---

## 📦 Installation

Published on **Maven Central**.

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("io.github.nicepay-dev:nicepay-kotlin-client:1.0.0")
}
```

---

## 💠 Features

### SNAP API
- 🔑 **Access Token**
  - Get Access Token
- 🏦 **Virtual Account**
  - Create VA
  - Status Inquiry
  - Delete VA
- 💸 **E-Wallet**
  - Registration
  - Status Inquiry
  - Refund
- 📲 **QRIS**
  - Generate QR Code
  - Status Inquiry
  - Refund
- 💵 **Payout / Disbursement**
  - Registration
  - Approve / Reject / Cancel
  - Balance Inquiry
  - Status Inquiry
- 🧰 **Utils**
  - Generate Signature
  - Verify Signature

### V2 API
- 🔁 **Common**
  - Register Payment (VA, Card, Ewallet, etc.)
  - Status Inquiry
  - Cancel Transaction
- 💳 **Card & E-Wallet**
  - Payment Flow

---

## 🚀 Quick Example

```kotlin
val config: NICEPay = NICEPay.Builder()
    .isProduction(false)
    .clientSecret(CLIENT_SECRET)
    .partnerId(I_MID_NORMALTEST)
    .externalID(EXTERNAL_ID)
    .timestamp(TIMESTAMP)
    .privateKey(PRIVATE_KEY)
    .build()

val service: SnapAccessTokenService = SnapAccessTokenServiceImpl()

val request = AccessToken.Builder()
    .grantType("client_credentials")
    .additionalInfo(emptyMap())
    .build()

val response = service.getAccessToken(request, config)
println("Access Token: ${response.accessToken}")
```

📚 For more examples, check the [unit tests](https://github.com/nicepay-dev/kotlin-nicepay-lib/tree/main/src/test/kotlin/io/github/nicepay)

---

## 🔗 Links

- [NICEPAY Official Site](https://www.nicepay.co.id)
- [Nicepay Documentation](https://docs.nicepay.co.id/)
- [GitHub Project](https://www.github.com/nicepay-dev/kotlin-nicepay-lib)

---

## 📄 License

[MIT License](LICENSE)

---

## 🧪 Running Tests

```bash
./gradlew test
```

JUnit 5 is used, with Retrofit calls mocked or pointed at the dev environment.

---
# RNSSLPinningDemo
A simple implementation of SSL Pinning in a React Native application

## How to start this project

Run this in your terminal:

```
npm i
npm run android
npm run ios
```

## SSL Pinning

### Android implementation

#### Android N and newer

Starting with Android N (API 24), there is a simplified way to specify pin sets in XML with a Network Security Configuration file. In this file, you can specify the domains you want to pin against. The pins should be defined as a Base64 encoded SPKI Fingerprint. [https://developers.redhat.com/blog/2017/10/26/android-spki-pinning-trustkit#pinning_in_android_n]

#### Older versions of Android OS

Certificate Pinning using OkHttp is easy, as it only requires creating an instance of CertificatePinner using a dedicated builder with its corresponding fingerprints. The fingerprints need to be hard-coded into the app or we can inject such keys during the build process, using the buildConfigField method. Then, we need to build an OkHttpClient instance with the CertificatePinner. [https://mailapurvpandey.medium.com/ssl-pinning-in-android-90dddfa3e051#:~:text=SSL%20pinning%20is%20a%20process,'pinned'%20to%20the%20host]


### iOS implementation with TrustKit
TrustKit is an open source framework that makes it easy to deploy SSL public key pinning and reporting in any iOS 12+, macOS 10.13+, tvOS 12+ or watchOS 4+ App; it supports both Swift and Objective-C Apps.[https://github.com/datatheorem/TrustKit]

### How to get SubjectPublicKeyInfo (Pin SHA256)
Run this in your terminal:

`openssl s_client -servername typicode.com -connect typicode.com:443 | openssl x509 -pubkey -noout | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64`

### How to test SSL pinning

Try a fake pin to check if SSL pinning implementation works (for example: 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=').

#### Where insert your fake pin:

For Android N and newer look at: android/app/src/main/res/xml/network_security_config.xml
For Older versions of Android OS look at: android/app/src/main/java/com/rnsslpinningdemo/SSLPinnerFactory.java
For iOS look at: ios/RNSSLPinningDemo/AppDelegate.mm

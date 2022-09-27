package com.rnsslpinningdemo;

import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;

public class SSLPinnerFactory implements OkHttpClientFactory {
  // Define domain and include all subdomains.
  // https://square.github.io/okhttp/4.x/okhttp/okhttp3/-certificate-pinner/#domain-patterns
  private static String hostname = "**.typicode.com";

  // https://square.github.io/okhttp/3.x/okhttp/okhttp3/CertificatePinner.html
  public OkHttpClient createNewNetworkModuleClient() {
    CertificatePinner certificatePinner = new CertificatePinner.Builder()
      // Run this in your terminal to get SubjectPublicKeyInfo (PIN SHA256):
      // openssl s_client -servername typicode.com -connect typicode.com:443 | openssl x509 -pubkey -noout | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
      // Try a fake pin to check if SSL pinning implementation works:
      // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=
      .add(hostname, "sha256/F5yEJFlAhYki30l8i+NwYAdXTKE1+x/n9J41HHorqys=")
      .build();

    // Get a OkHttpClient builder with all the React Native defaults
    OkHttpClient.Builder clientBuilder = OkHttpClientProvider.createClientBuilder();
    return clientBuilder
          .certificatePinner(certificatePinner)
          .build();
  }
}
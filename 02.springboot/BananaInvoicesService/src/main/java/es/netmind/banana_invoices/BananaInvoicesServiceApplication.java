package es.netmind.banana_invoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BananaInvoicesServiceApplication {

    /*This block is for SSL communication with OAuth server*/
    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(
                            String hostname,
                            javax.net.ssl.SSLSession sslSession
                    ) {
                        if (hostname.equals("auth-server")) return true;
                        return false;
                    }
                });
    }

    public static void main(String[] args) {
        SpringApplication.run(BananaInvoicesServiceApplication.class, args);
    }

}

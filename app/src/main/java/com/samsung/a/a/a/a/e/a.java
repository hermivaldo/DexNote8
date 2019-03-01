package com.samsung.a.a.a.a.e;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* compiled from: CertificateManager */
public class a {
    private SSLContext a;

    /* compiled from: CertificateManager */
    private static class a {
        private static final a a = new a();
    }

    private a() {
        c();
    }

    public static a a() {
        return a.a;
    }

    private void c() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            KeyStore instance2 = KeyStore.getInstance("AndroidCAStore");
            instance2.load(null, null);
            Enumeration aliases = instance2.aliases();
            while (aliases.hasMoreElements()) {
                String str = (String) aliases.nextElement();
                X509Certificate x509Certificate = (X509Certificate) instance2.getCertificate(str);
                if (str.startsWith("system:")) {
                    instance.setCertificateEntry(str, x509Certificate);
                }
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance);
            this.a = SSLContext.getInstance("TLS");
            this.a.init(null, instance3.getTrustManagers(), null);
            com.samsung.a.a.a.a.i.a.a("pinning success");
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pinning fail : ");
            stringBuilder.append(e.getMessage());
            com.samsung.a.a.a.a.i.a.a(stringBuilder.toString());
        }
    }

    public SSLContext b() {
        return this.a;
    }
}

package com.samsung.p047a.p048a.p049a.p050a.p055e;

import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* compiled from: CertificateManager */
/* renamed from: com.samsung.a.a.a.a.e.a */
public class C0769a {
    /* renamed from: a */
    private SSLContext f2437a;

    /* compiled from: CertificateManager */
    /* renamed from: com.samsung.a.a.a.a.e.a$a */
    private static class C0768a {
        /* renamed from: a */
        private static final C0769a f2436a = new C0769a();
    }

    private C0769a() {
        m3191c();
    }

    /* renamed from: a */
    public static C0769a m3190a() {
        return C0768a.f2436a;
    }

    /* renamed from: c */
    private void m3191c() {
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
            this.f2437a = SSLContext.getInstance("TLS");
            this.f2437a.init(null, instance3.getTrustManagers(), null);
            C0784a.m3253a("pinning success");
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pinning fail : ");
            stringBuilder.append(e.getMessage());
            C0784a.m3253a(stringBuilder.toString());
        }
    }

    /* renamed from: b */
    public SSLContext m3192b() {
        return this.f2437a;
    }
}

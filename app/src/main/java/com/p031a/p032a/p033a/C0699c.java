package com.p031a.p032a.p033a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: SerializedName */
/* renamed from: com.a.a.a.c */
public @interface C0699c {
    /* renamed from: a */
    String m2949a();

    /* renamed from: b */
    String[] m2950b() default {};
}

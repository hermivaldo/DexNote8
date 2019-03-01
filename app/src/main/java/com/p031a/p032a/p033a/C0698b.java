package com.p031a.p032a.p033a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: JsonAdapter */
/* renamed from: com.a.a.a.b */
public @interface C0698b {
    /* renamed from: a */
    Class<?> m2947a();

    /* renamed from: b */
    boolean m2948b() default true;
}

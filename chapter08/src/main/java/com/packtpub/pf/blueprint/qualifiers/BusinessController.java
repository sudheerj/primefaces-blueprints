package com.packtpub.pf.blueprint.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Qualifier;

@Qualifier
@Named
@ApplicationScoped
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessController {
}

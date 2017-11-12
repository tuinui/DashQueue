package com.telecorp.dashqueue.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Saran on 13/10/2560.
 */

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ServiceScope
{
}

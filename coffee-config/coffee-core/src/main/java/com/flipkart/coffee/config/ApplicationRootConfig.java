package com.flipkart.coffee.config;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to be placed on config class while is root of the config object composition hierarchy.
 * RootConfig objects cannot be referenced in other config objects
 */
@Retention(CLASS)
@Target(TYPE)
public @interface ApplicationRootConfig {}

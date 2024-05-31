package com.flipkart.coffee.config;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to be placed on config class which participates in the config object composition
 * hierarchy.
 */
@Retention(CLASS)
@Target(TYPE)
public @interface ModuleConfig {}

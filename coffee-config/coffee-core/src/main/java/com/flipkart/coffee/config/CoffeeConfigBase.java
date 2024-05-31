package com.flipkart.coffee.config;

import com.flipkart.coffee.config.providers.ConfigProvider;

/**
 * Root class for all Coffee config objects.
 *
 * @implNote This class is an abstract class because we do not want implementations of this class to
 *     participate in other class hierarchies. A Config implementation should ideally be dedicated
 *     to making configs accessible in a type-safe manner.
 */
public abstract class CoffeeConfigBase {
  private ConfigProvider configProvider;

  protected CoffeeConfigBase(ConfigProvider configProvider) {
    this.configProvider = configProvider;
  }

  protected ConfigProvider _configProvider() {
    return configProvider;
  }
}

package com.flipkart.coffee.config.providers;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

@Slf4j
public class SubConfigProvider implements ConfigProvider {
  private final String configPrefix;
  private final ConfigProvider delegate;

  public SubConfigProvider(String configPrefix, ConfigProvider delegate) {
    this.configPrefix = configPrefix;
    this.delegate = delegate;
  }

  @Override
  public <T> @NonNull T getConfig(String key, T defaultValue) {
    try {
      return delegate.getConfig(configPrefix + key, defaultValue);
    } catch (Throwable e) {
      log.error("Could not retrieve config value for key {} due to an exception: ", key, e);
      return defaultValue;
    }
  }
}

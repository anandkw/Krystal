package com.flipkart.coffee.config.providers;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

public interface ConfigProvider {

  <T> @NonNull Optional<T> getConfig(String key);
}

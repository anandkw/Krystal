package com.flipkart.coffee.config.providers;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode
@ToString
@Slf4j
public final class MapConfigProvider implements ConfigProvider {
  private final Map<String, Object> configs;

  public MapConfigProvider(Map<String, Object> configs) {
    this.configs = configs;
  }

  public Map<String, Object> configs() {
    return configs;
  }

  @Override
  public @NonNull <T> Optional<T> getConfig(String key) {
    try {
      //noinspection unchecked
      return Optional.ofNullable((T) configs.get(key));
    } catch (Throwable e) {
      log.error("Could not retrieve config value for key {} due to an exception: ", key, e);
      return Optional.empty();
    }
  }
}

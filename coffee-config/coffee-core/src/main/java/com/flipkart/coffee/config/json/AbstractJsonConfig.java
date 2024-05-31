package com.flipkart.coffee.config.json;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public abstract class AbstractJsonConfig {
  public static final String CONFIG_PATH_SEP = "->";
  private final LockResource _lock = new LockResource();

  private final String configPath;
  protected final ImmutableMap<String, Object> map;

  protected AbstractJsonConfig(String configPath, Map<String, Object> map) {
    this.configPath = configPath;
    this.map = ImmutableMap.copyOf(map);
  }

  protected <V> Optional<V> _getValue(String key) {
    try {
      //noinspection unchecked
      return Optional.ofNullable((V) map.get(key));
    } catch (Throwable e) {
      log.error("Could not retrieve config with path {}{}{} ", configPath, CONFIG_PATH_SEP, key, e);
      return Optional.empty();
    }
  }

  protected final LockResource _lock() {
    return _lock;
  }

  protected static class LockResource implements AutoCloseable {
    private final ReentrantLock _lock = new ReentrantLock();

    @Override
    public void close() {
      _lock.unlock();
    }
  }
}

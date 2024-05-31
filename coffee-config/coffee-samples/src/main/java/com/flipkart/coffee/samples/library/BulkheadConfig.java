package com.flipkart.coffee.samples.library;

import com.flipkart.coffee.config.ModuleConfig;

import java.util.Optional;

@ModuleConfig
public interface BulkheadConfig {
  Optional<Boolean> enabled();

  Optional<BulkheadType> type();

  Optional<Integer> maxConcurrency();
}

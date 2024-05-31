package com.flipkart.coffee.samples;

import com.flipkart.coffee.config.types.Instant;
import com.flipkart.coffee.samples.library.BulkheadConfig;
import com.flipkart.coffee.samples.library.BulkheadType;

import java.util.Optional;

public interface FooBulkheadConfig extends BulkheadConfig {
  @Instant
  @Override
  Optional<Boolean> enabled();

  @Instant
  @Override
  Optional<Integer> maxConcurrency();

  default Optional<BulkheadType> type() {
    return Optional.of(BulkheadType.SEMAPHORE);
  }
}

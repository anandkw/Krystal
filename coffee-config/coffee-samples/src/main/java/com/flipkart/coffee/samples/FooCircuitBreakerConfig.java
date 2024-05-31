package com.flipkart.coffee.samples;

import com.flipkart.coffee.config.validations.Atomic;
import com.flipkart.coffee.config.types.Dynamic;
import com.flipkart.coffee.config.types.Instant;
import com.flipkart.coffee.samples.library.CircuitBreakerConfig;
import com.flipkart.coffee.samples.library.SlidingWindowType;
import java.util.Optional;

public interface FooCircuitBreakerConfig extends CircuitBreakerConfig {

  String SLIDING_WINDOW = "sliding_window";

  @Instant
  @Override
  Optional<Boolean> enabled();

  @Dynamic
  @Override
  Optional<Integer> errorThresholdPercent();

  @Atomic(SLIDING_WINDOW)
  @Instant
  @Override
  Optional<Integer> slidingWindowSize();

  @Atomic(SLIDING_WINDOW)
  @Instant
  @Override
  Optional<SlidingWindowType> slidingWindowType();
}

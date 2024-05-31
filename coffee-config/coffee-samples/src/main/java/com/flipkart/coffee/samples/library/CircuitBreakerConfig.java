package com.flipkart.coffee.samples.library;

import com.flipkart.coffee.config.ModuleConfig;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Optional;

@ModuleConfig
public interface CircuitBreakerConfig {
  Optional<Boolean> enabled();

  @Min(1)
  @Max(100)
  Optional<Integer> errorThresholdPercent();

  Optional<SlidingWindowType> slidingWindowType();

  Optional<Integer> slidingWindowSize();
}

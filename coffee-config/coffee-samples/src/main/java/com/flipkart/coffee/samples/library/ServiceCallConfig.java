package com.flipkart.coffee.samples.library;

import com.flipkart.coffee.config.ApplicationRootConfig;

@ApplicationRootConfig
public interface ServiceCallConfig {
  CircuitBreakerConfig circuitBreaker();

  BulkheadConfig bulkhead();
}

package com.flipkart.coffee.samples;

import com.flipkart.coffee.samples.library.ServiceCallConfig;

public interface FooServiceCallConfig extends ServiceCallConfig {
  FooCircuitBreakerConfig circuitBreaker();

  FooBulkheadConfig bulkhead();
}

package com.flipkart.coffee.samples;

import com.flipkart.coffee.samples.library.CallPathPriority;
import com.flipkart.coffee.samples.library.NpsConfig;

public interface FooNpsConfig extends NpsConfig {
  FooServiceCallConfig serviceCallConfigs(
      String dataProviderName, CallPathPriority callPathPriority);
}

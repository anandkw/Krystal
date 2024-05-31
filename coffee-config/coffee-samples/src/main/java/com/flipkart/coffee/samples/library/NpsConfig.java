package com.flipkart.coffee.samples.library;

import com.flipkart.coffee.config.FallbackTo;
import com.flipkart.coffee.config.ModuleConfig;

@ModuleConfig
public interface NpsConfig {
  ServiceCallConfig serviceCallConfigs(
      @FallbackTo("ANY_DATA_PROVIDER") String dataProviderName,
      @FallbackTo("P_DEFAULT") CallPathPriority callPathPriority);
}

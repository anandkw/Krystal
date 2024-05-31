package com.flipkart.coffee.samples;

import com.flipkart.coffee.config.ApplicationRootConfig;
import com.flipkart.coffee.samples.library.ServiceClientConfig;

@ApplicationRootConfig
public interface FooAppConfig {
  FooNpsConfig nps();

  ServiceClientConfig serviceClientConfigs(String serviceName, String apiName);


}

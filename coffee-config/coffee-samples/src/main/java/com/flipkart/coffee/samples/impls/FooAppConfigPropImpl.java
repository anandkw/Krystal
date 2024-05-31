package com.flipkart.coffee.samples.impls;

import com.flipkart.coffee.config.providers.ConfigProvider;
import com.flipkart.coffee.samples.*;
import com.flipkart.coffee.samples.library.*;

import java.util.Optional;

public class FooAppConfigPropImpl implements FooAppConfig {
  private ConfigProvider configProvider;

  public FooAppConfigPropImpl(ConfigProvider configProvider) {
    this.configProvider = configProvider;
  }

  @Override
  public FooNpsConfig nps() {
    return new FooNpsConfig() {
      @Override
      public FooServiceCallConfig serviceCallConfigs(
          String dataProviderName, CallPathPriority callPathPriority) {
        return new ServiceCallConfig() {
          @Override
          public BulkheadConfig bulkhead() {
            return new BulkheadConfig() {
              @Override
              public Optional<Boolean> enabled() {
                return configProvider
                    .<Boolean>getConfig(
                        "cal.vajramConfigs."
                            + dataProviderName
                            + "."
                            + callPathPriority
                            + ".enabled")
                    .or(
                        () ->
                            configProvider
                                .<Boolean>getConfig(
                                    "cal.vajramConfigs." + dataProviderName + ".DEFAULT.enabled")
                                .or(
                                    () ->
                                        configProvider.getConfig(
                                            "cal.vajramConfigs.DEFAULT."
                                                + callPathPriority
                                                + ".enabled")));
              }

              @Override
              public Optional<BulkheadType> type() {
                return configProvider
                    .<BulkheadType>getConfig(
                        "cal.vajramConfigs." + dataProviderName + "." + callPathPriority + ".type")
                    .or(
                        () ->
                            configProvider.getConfig(
                                "cal.vajramConfigs." + dataProviderName + ".DEFAULT.type"));
              }

              @Override
              public Optional<Integer> maxConcurrency() {
                return configProvider
                    .<Integer>getConfig(
                        "cal.vajramConfigs."
                            + dataProviderName
                            + "."
                            + callPathPriority
                            + ".maxConcurrency")
                    .or(
                        () ->
                            configProvider.getConfig(
                                "cal.vajramConfigs."
                                    + dataProviderName
                                    + ".DEFAULT.maxConcurrency"));
              }
            };
          }
        };
      }
    };
  }

  @Override
  public ServiceClientConfig serviceClientConfigs(String serviceName, String apiName) {
    return new ServiceClientConfig() {};
  }
}

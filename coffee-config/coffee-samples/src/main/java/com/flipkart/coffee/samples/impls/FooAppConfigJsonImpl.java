package com.flipkart.coffee.samples.impls;

import com.flipkart.coffee.config.json.AbstractJsonConfig;
import com.flipkart.coffee.config.providers.MapConfigProvider;
import com.flipkart.coffee.samples.FooAppConfig;
import com.flipkart.coffee.samples.library.NpsConfig;
import com.flipkart.coffee.samples.library.ServiceClientConfig;

import java.util.Map;

public class FooAppConfigJsonImpl extends AbstractJsonConfig implements FooAppConfig {

  private NpsConfig cal;
  private ServiceClientConfig scConfigs;

  protected FooAppConfigJsonImpl(MapConfigProvider mapConfigProvider) {
    super(mapConfigProvider.configs());
  }

  @Override
  public NpsConfig nps() {
    if (cal == null) {
      try (LockResource ignored = _lock()) {
        if (cal == null) {
          this.cal =
              new NpsConfigJsonImpl(this.<Map<String, Object>>_getValue("cal").orElse(Map.of()));
        }
      }
    }
    return this.cal;
  }

  @Override
  public ServiceClientConfig serviceClientConfigs(String serviceName, String apiName) {
    return null;
  }
}

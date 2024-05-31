package com.flipkart.coffee.samples.impls;

import com.flipkart.coffee.config.json.AbstractJsonConfig;
import com.flipkart.coffee.samples.library.BulkheadConfig;
import com.flipkart.coffee.samples.library.ServiceCallConfig;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ServiceCallConfigJsonImpl extends AbstractJsonConfig implements ServiceCallConfig {

  private final String configPath;
  private final Supplier<List<Supplier<ServiceCallConfig>>> _fallbacks;

  private BulkheadConfig bulkhead;

  public ServiceCallConfigJsonImpl(
      String configPath,
      Map<String, Object> vajramConfigs,
      Supplier<List<Supplier<ServiceCallConfig>>> fallbacks) {
    super(configPath, vajramConfigs);
    this.configPath = configPath;
    this._fallbacks = fallbacks;
  }

  @Override
  public BulkheadConfig bulkhead() {
    if (bulkhead == null) {
      this.bulkhead =
          new BulkheadConfigJsonImpl(
              configPath + CONFIG_PATH_SEP + "bulkhead",
              this.<Map<String, Object>>_getValue("bulkhead").orElse(Map.of()),
              () ->
                  _fallbacks.get().stream()
                      .map(supp -> ((Supplier<BulkheadConfig>) () -> supp.get().bulkhead()))
                      .toList());
    }
    return bulkhead;
  }
}

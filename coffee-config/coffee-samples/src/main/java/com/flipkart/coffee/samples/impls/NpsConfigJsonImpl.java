package com.flipkart.coffee.samples.impls;

import com.flipkart.coffee.config.FallbackTo;
import com.flipkart.coffee.config.json.AbstractJsonConfig;
import com.flipkart.coffee.samples.library.NpsConfig;
import com.flipkart.coffee.samples.library.CallPathPriority;
import com.flipkart.coffee.samples.library.ServiceCallConfig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class NpsConfigJsonImpl extends AbstractJsonConfig implements NpsConfig {
  private final Map<String, Map<CallPathPriority, ServiceCallConfig>> vajramConfigs =
      new LinkedHashMap<>();
  private final String configPath;

  public NpsConfigJsonImpl(String configPath, Map<String, Object> calConfigMap) {
    super(configPath, calConfigMap);
    this.configPath = configPath;
  }

  @Override
  public ServiceCallConfig serviceCallConfigs(
      @FallbackTo("ANY_DATA_PROVIDER") String dataProviderName,
      @FallbackTo("P_DEFAULT") CallPathPriority priority) {
    ServiceCallConfig serviceCallConfig =
        vajramConfigs.getOrDefault(dataProviderName, Map.of()).get(priority);
    if (serviceCallConfig == null) {
      serviceCallConfig =
          vajramConfigs(
              dataProviderName,
              priority,
              () -> {
                List<Supplier<ServiceCallConfig>> fallbacks;
                fallbacks = new ArrayList<>();
                boolean defaultPriority = CallPathPriority.P_DEFAULT.equals(priority);
                boolean defaultVajramId = "ANY_VAJRAM".equals(dataProviderName);
                if (!defaultPriority || !defaultVajramId) {
                  if (!defaultPriority) {
                    fallbacks.add(
                        () ->
                            vajramConfigs(dataProviderName, CallPathPriority.P_DEFAULT, List::of));
                  }
                  if (!defaultVajramId) {
                    fallbacks.add(() -> vajramConfigs("ANY_VAJRAM", priority, List::of));
                  }
                  fallbacks.add(
                      () -> vajramConfigs("ANY_VAJRAM", CallPathPriority.P_DEFAULT, List::of));
                }
                return fallbacks;
              });
    }
    return serviceCallConfig;
  }

  private ServiceCallConfig vajramConfigs(
      @FallbackTo("ANY_VAJRAM") String vajramId,
      @FallbackTo("P_DEFAULT") CallPathPriority priority,
      Supplier<List<Supplier<ServiceCallConfig>>> fallbacks) {
    ServiceCallConfig serviceCallConfig =
        vajramConfigs.getOrDefault(vajramId, Map.of()).get(priority);
    if (serviceCallConfig == null) {
      try (LockResource ignored = _lock()) {
        serviceCallConfig =
            vajramConfigs
                .computeIfAbsent(vajramId, s -> new LinkedHashMap<>())
                .computeIfAbsent(
                    priority,
                    s ->
                        new ServiceCallConfigJsonImpl(
                            configPath + CONFIG_PATH_SEP + "vajramConfigs",
                            this.<Map<String, Map<String, Map<String, Object>>>>_getValue(
                                    "vajramConfigs")
                                .orElse(Map.of())
                                .getOrDefault(vajramId, Map.of())
                                .getOrDefault(priority.name(), Map.of()),
                            fallbacks));
      }
    }
    return serviceCallConfig;
  }
}

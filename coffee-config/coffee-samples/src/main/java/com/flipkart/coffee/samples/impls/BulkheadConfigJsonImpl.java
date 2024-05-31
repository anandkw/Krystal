package com.flipkart.coffee.samples.impls;

import com.flipkart.coffee.config.json.AbstractJsonConfig;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import com.flipkart.coffee.samples.library.BulkheadConfig;
import com.flipkart.coffee.samples.library.BulkheadType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BulkheadConfigJsonImpl extends AbstractJsonConfig implements BulkheadConfig {

  private final Supplier<List<Supplier<BulkheadConfig>>> _fallbacks;

  protected BulkheadConfigJsonImpl(
      String configPath,
      Map<String, Object> map,
      Supplier<List<Supplier<BulkheadConfig>>> fallbacks) {
    super(configPath, map);
    this._fallbacks = fallbacks;
  }

  @Override
  public Optional<Boolean> enabled() {
    return this.<Boolean>_getValue("enabled")
        .or(
            () ->
                _fallbacks.get().stream()
                    .map(supp -> supp.get().enabled().orElse(null))
                    .filter(Objects::nonNull)
                    .findFirst());
  }

  @Override
  public Optional<BulkheadType> type() {
    return this.<BulkheadType>_getValue("type")
        .or(
            () ->
                _fallbacks.get().stream()
                    .map(supp -> supp.get().type().orElse(null))
                    .filter(Objects::nonNull)
                    .findFirst());
  }

  @Override
  public Optional<Integer> maxConcurrency() {
    return this.<Integer>_getValue("maxConcurrency")
        .or(
            () ->
                _fallbacks.get().stream()
                    .map(supp -> supp.get().maxConcurrency().orElse(null))
                    .filter(Objects::nonNull)
                    .findFirst());
  }
}

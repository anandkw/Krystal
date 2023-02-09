package com.flipkart.krystal.vajram.samples.benchmarks.calculator.divider;

import com.flipkart.krystal.data.Inputs;
import com.flipkart.krystal.datatypes.IntegerType;
import com.flipkart.krystal.vajram.inputs.Input;
import com.flipkart.krystal.vajram.inputs.VajramInputDefinition;
import com.flipkart.krystal.vajram.samples.benchmarks.calculator.divider.DividerInputUtil.AllInputs;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public final class DividerImpl extends Divider {
  @Override
  public ImmutableCollection<VajramInputDefinition> getInputDefinitions() {
    return ImmutableList.of(
        Input.builder().name("number_one").type(IntegerType.integer()).isMandatory().build(),
        Input.builder().name("number_two").type(IntegerType.integer()).isMandatory().build());
  }

  @Override
  public Integer executeCompute(Inputs inputs) {
    return divide(
        new AllInputs(
            inputs.getInputValueOrThrow("number_one"),
            inputs.getInputValueOrDefault("number_two", null)));
  }
}

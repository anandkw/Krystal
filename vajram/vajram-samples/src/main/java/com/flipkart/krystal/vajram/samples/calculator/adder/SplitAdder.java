package com.flipkart.krystal.vajram.samples.calculator.adder;

import static com.flipkart.krystal.vajram.facets.SingleExecute.executeWith;
import static com.flipkart.krystal.vajram.facets.SingleExecute.skipExecution;

import com.flipkart.krystal.vajram.ComputeVajram;
import com.flipkart.krystal.vajram.facets.Dependency;
import com.flipkart.krystal.vajram.facets.Input;
import com.flipkart.krystal.vajram.facets.Output;
import com.flipkart.krystal.vajram.VajramDef;
import com.flipkart.krystal.vajram.facets.SingleExecute;
import com.flipkart.krystal.vajram.facets.Using;
import com.flipkart.krystal.vajram.facets.resolution.sdk.Resolve;
import com.flipkart.krystal.vajram.samples.calculator.adder.SplitAdderFacetUtil.SplitAdderFacets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@VajramDef
@SuppressWarnings("initialization.field.uninitialized")
public abstract class SplitAdder extends ComputeVajram<Integer> {
  static class _Facets {
    @Input List<Integer> numbers;

    @Dependency(onVajram = SplitAdder.class)
    Optional<Integer> splitSum1;

    @Dependency(onVajram = SplitAdder.class)
    Optional<Integer> splitSum2;

    @Dependency(onVajram = Adder.class)
    Optional<Integer> sum;
  }

  @Resolve(dep = splitSum1_i, depInputs = numbers_i)
  public static SingleExecute<ArrayList<Integer>> numbersForSubSplitter1(
      @Using(numbers_i) List<Integer> numbers) {
    if (numbers.size() < 2) {
      return skipExecution(
          "Skipping splitters as count of numbers is less than 2. Will call adder instead");
    } else {
      int subListSize = numbers.size() / 2;
      return executeWith(new ArrayList<>(numbers.subList(0, subListSize)));
    }
  }

  @Resolve(dep = splitSum2_i, depInputs = numbers_i)
  public static SingleExecute<ArrayList<Integer>> numbersForSubSplitter2(
      @Using(numbers_i) List<Integer> numbers) {
    if (numbers.size() < 2) {
      return skipExecution(
          "Skipping splitters as count of numbers is less than 2. Will call adder instead");
    } else {
      int subListSize = numbers.size() / 2;
      return executeWith(new ArrayList<>(numbers.subList(subListSize, numbers.size())));
    }
  }

  @Resolve(dep = sum_i, depInputs = numberOne_i)
  public static SingleExecute<Integer> adderNumberOne(@Using(numbers_i) List<Integer> numbers) {
    if (numbers.size() == 1) {
      return executeWith(numbers.get(0));
    } else if (numbers.isEmpty()) {
      return skipExecution("No numbers provided. Skipping adder call");
    } else {
      return skipExecution("More than 1 numbers provided. SplitAdders will be called instead");
    }
  }

  @Resolve(dep = sum_i, depInputs = numberTwo_i)
  public static Integer adderNumberTwo(@Using(numbers_i) List<Integer> numbers) {
    return 0;
  }

  @Output
  static Integer add(SplitAdderFacets facets) {
    return facets.splitSum1().orElse(0) + facets.splitSum2().orElse(0) + facets.sum().orElse(0);
  }
}

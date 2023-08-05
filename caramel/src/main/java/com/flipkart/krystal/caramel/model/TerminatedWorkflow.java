package com.flipkart.krystal.caramel.model;

import java.util.function.Consumer;
import java.util.function.Function;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface TerminatedWorkflow<INPUT, ROOT extends WorkflowPayload, OUTPUT>
    extends WorkflowCompletionStage, Function<INPUT, OUTPUT>, Consumer<INPUT> {

  interface WorkflowPostProcessor<INPUT, ROOT extends WorkflowPayload, OUTPUT>
      extends TerminatedWorkflow<INPUT, ROOT, OUTPUT> {}
}

package com.soap.app;

import com.soap.domain.ServiceRecord;

import java.util.List;
import java.util.function.Function;

public sealed interface TransformResult permits TransformResult.Ok, TransformResult.Fail {
    <T> T fold(Function<Fail, T> onFail, Function<Ok, T> onOk);

    record Ok(ServiceRecord record) implements TransformResult {
        @Override
        public <T> T fold(Function<Fail, T> onFail, Function<Ok, T> onOk) {
            return onOk.apply(this);
        }
    }
    record Fail(List<ValidationError> errors) implements TransformResult {
        @Override
        public <T> T fold(Function<Fail, T> onFail, Function<Ok, T> onOk) {
            return onFail.apply(this);
        }
    }
}

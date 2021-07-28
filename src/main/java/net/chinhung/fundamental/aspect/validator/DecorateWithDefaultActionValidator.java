package net.chinhung.fundamental.aspect.validator;

import net.chinhung.fundamental.aspect.ActionService;

import java.util.function.Function;

public class DecorateWithDefaultActionValidator<A extends ActionService<Action, Result>, Action, Result> implements Function<A, A> {

    @Override
    public A apply(A decoratee) {
        return (A) new ValidateActionDecorator<Action, Result>(decoratee, new DefaultValidator<Action>());
    }
}

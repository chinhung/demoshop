package net.chinhung.fundamental.aspect;

import java.util.function.Supplier;

public class SpyDecorator<Action, Result> implements
        CommandService<Action, Result>, QueryService<Action, Result> {

    private ActionService<Action, Result> decoratee;
    public boolean executeIsCalled = false;
    public Supplier<Boolean> preCondition;

    @Override
    public Result execute(Action action) {
        if (decoratee == null) {
            throw new RuntimeException("no decoratee yet");
        }
        if (preCondition != null && !preCondition.get()) {
            throw new RuntimeException("pre-condition not satisfied");
        }
        executeIsCalled = true;
        return decoratee.execute(action);
    }

    public SpyDecorator<Action, Result> setDecratee(ActionService<Action, Result> decoratee) {
        this.decoratee = decoratee;
        return this;
    }

    public SpyDecorator<Action, Result> setPreCondition(Supplier<Boolean> preCondition) {
        this.preCondition = preCondition;
        return this;
    }
}

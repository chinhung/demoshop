package net.chinhung.fundamental.aspect.listener;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

public class AroundDecorator<Action, Result> implements
        CommandService<Action, Result>, QueryService<Action, Result> {

    private final ActionService<Action, Result> decoratee;
    private final AroundListener<Action, Result> listener;

    public AroundDecorator(ActionService<Action, Result> decoratee, AroundListener<Action, Result> listener) {
        this.decoratee = decoratee;
        this.listener = listener;
    }

    @Override
    public Result execute(Action action) {
        listener.onBefore(action);
        Result result = decoratee.execute(action);
        listener.onAfter(action, result);
        return result;
    }
}

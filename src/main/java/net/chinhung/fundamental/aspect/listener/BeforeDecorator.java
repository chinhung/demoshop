package net.chinhung.fundamental.aspect.listener;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

public class BeforeDecorator<Action, Result> implements
        CommandService<Action, Result>, QueryService<Action, Result> {

    private final ActionService<Action, Result> decoratee;
    private final BeforeListener<Action> listener;

    public BeforeDecorator(ActionService<Action, Result> decoratee, BeforeListener<Action> listener) {
        this.decoratee = decoratee;
        this.listener = listener;
    }

    @Override
    public Result execute(Action action) {
        listener.onBefore(action);
        Result result = decoratee.execute(action);
        return result;
    }
}

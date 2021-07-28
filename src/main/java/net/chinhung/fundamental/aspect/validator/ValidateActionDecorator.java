package net.chinhung.fundamental.aspect.validator;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

public class ValidateActionDecorator<Action, Result> implements
        CommandService<Action, Result>, QueryService<Action, Result> {

    private final ActionService<Action, Result> decoratee;
    private final Validator<Action> validator;

    public ValidateActionDecorator(ActionService<Action, Result> decoratee, Validator<Action> validator) {
        this.decoratee = decoratee;
        this.validator = validator;
    }

    @Override
    public Result execute(Action action) {
        if (validator.validate(action)) {
            return decoratee.execute(action);
        } else {
            throw new RuntimeException("invalid action: " + action);
        }
    }
}

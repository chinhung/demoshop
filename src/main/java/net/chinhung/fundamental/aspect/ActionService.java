package net.chinhung.fundamental.aspect;

public interface ActionService<Action, Result> {

    Result execute(Action action);
}

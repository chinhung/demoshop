package net.chinhung.fundamental.aspect.listener;

public interface AfterListener<Action, Result> {
    void onAfter(Action action, Result result);
}

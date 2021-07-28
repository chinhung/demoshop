package net.chinhung.fundamental.aspect.listener;

public interface AroundListener<Action, Result> {
    void onBefore(Action action);
    void onAfter(Action action, Result result);
}

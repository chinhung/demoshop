package net.chinhung.fundamental.aspect.listener;

public interface BeforeListener<Action> {
    void onBefore(Action action);
}

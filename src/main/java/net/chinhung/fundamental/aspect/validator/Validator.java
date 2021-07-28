package net.chinhung.fundamental.aspect.validator;

public interface Validator<Target> {
    boolean validate(Target target);
}

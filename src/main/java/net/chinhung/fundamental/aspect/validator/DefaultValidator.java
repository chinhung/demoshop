package net.chinhung.fundamental.aspect.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DefaultValidator<Target> implements Validator<Target> {

    private final javax.validation.Validator validator;

    public DefaultValidator() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public boolean validate(Target target) {
        final Set<ConstraintViolation<Target>> constraintViolations = validator.validate(target);
        if (constraintViolations.size() == 0) {
            return true;
        }
        System.out.println(constraintViolations);
        return false;
    }
}

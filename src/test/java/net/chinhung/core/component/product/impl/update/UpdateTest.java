package net.chinhung.core.component.product.impl.update;

import net.chinhung.core.component.product.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;
import static util.TestUtil.ANY_INT;

public class UpdateTest {

    private javax.validation.Validator validator;

    @BeforeEach
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Stream<Arguments> testIdValidation() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(ANY_STRING, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testIdValidation(String id, boolean isValid) {
        final Update command = new Update(id, ANY_STRING, ANY_INT);

        final Set<ConstraintViolation<Update>> constraintViolations = validator.validateProperty(command, "id");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }

    private static Stream<Arguments> testNameValidation() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(ANY_STRING, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testNameValidation(String name, boolean isValid) {
        final Update command = new Update(ANY_STRING, name, ANY_INT);

        final Set<ConstraintViolation<Update>> constraintViolations = validator.validateProperty(command, "name");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }

    private static Stream<Arguments> testPriceValidation() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(-1, false),
                Arguments.of(0, true),
                Arguments.of(1, true),
                Arguments.of(Integer.MAX_VALUE, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testPriceValidation(Integer price, boolean isValid) {
        final Update command = new Update(ANY_STRING, ANY_STRING, price);

        final Set<ConstraintViolation<Update>> constraintViolations = validator.validateProperty(command, "price");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

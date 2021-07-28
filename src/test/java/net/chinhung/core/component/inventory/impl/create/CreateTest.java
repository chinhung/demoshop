package net.chinhung.core.component.inventory.impl.create;

import net.chinhung.core.component.inventory.Create;
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

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class CreateTest {

    private javax.validation.Validator validator;

    @BeforeEach
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
        final Create command = new Create(name, ANY_STRING, ANY_INT);

        final Set<ConstraintViolation<Create>> constraintViolations = validator.validateProperty(command, "name");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }

    private static Stream<Arguments> testProductIdValidation() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(ANY_STRING, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testProductIdValidation(String productId, boolean isValid) {
        final Create command = new Create(ANY_STRING, productId, ANY_INT);

        final Set<ConstraintViolation<Create>> constraintViolations = validator.validateProperty(command, "productId");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }

    private static Stream<Arguments> testStockQuantityValidation() {
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
    public void testStockQuantityValidation(Integer stockQuantity, boolean isValid) {
        final Create command = new Create(ANY_STRING, ANY_STRING, stockQuantity);

        final Set<ConstraintViolation<Create>> constraintViolations = validator.validateProperty(command, "stockQuantity");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

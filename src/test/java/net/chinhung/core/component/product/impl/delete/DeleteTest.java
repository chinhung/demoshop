package net.chinhung.core.component.product.impl.delete;

import net.chinhung.core.component.product.Delete;
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

public class DeleteTest {

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
        final Delete command = new Delete(id);

        final Set<ConstraintViolation<Delete>> constraintViolations = validator.validateProperty(command, "id");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

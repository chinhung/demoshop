package net.chinhung.core.component.inventory.impl.find_by_product_id;

import net.chinhung.core.component.inventory.FindByProductId;
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

public class FindByProductIdTest {

    private javax.validation.Validator validator;

    @BeforeEach
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
    public void testProductIdValidation(String id, boolean isValid) {
        final FindByProductId command = new FindByProductId(id);

        final Set<ConstraintViolation<FindByProductId>> constraintViolations = validator.validateProperty(command, "productId");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

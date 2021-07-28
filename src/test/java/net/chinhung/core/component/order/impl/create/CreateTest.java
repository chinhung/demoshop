package net.chinhung.core.component.order.impl.create;

import net.chinhung.core.component.order.Create;
import net.chinhung.core.order.CoreItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.*;
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

    private static Stream<Arguments> testCoreItemsValidation() {
        final CoreItem invalidCoreItem = new CoreItem("", ANY_STRING, ANY_INT);
        final CoreItem validCoreItem = new CoreItem(ANY_STRING, ANY_STRING, ANY_INT);

        return Stream.of(
                Arguments.of(new ArrayList<>(), false),
                Arguments.of(Collections.singletonList(invalidCoreItem), false),
                Arguments.of(Collections.singletonList(validCoreItem), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testCoreItemsValidation(final List<CoreItem> coreItems, final boolean isValid) {
        final Create command = new Create(coreItems);

        final Set<ConstraintViolation<Create>> constraintViolations = validator.validate(command);

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

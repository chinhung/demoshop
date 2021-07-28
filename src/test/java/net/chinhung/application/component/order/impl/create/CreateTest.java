package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.order.Item;
import net.chinhung.core.order.CoreItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    private static Stream<Arguments> testItemsValidation() {
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
    public void testItemsValidation(final List<CoreItem> coreItems, final boolean isValid) {
        final Create command = new Create(coreItems.stream().map(Item::new).collect(Collectors.toList()));

        final Set<ConstraintViolation<Create>> constraintViolations = validator.validate(command);

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

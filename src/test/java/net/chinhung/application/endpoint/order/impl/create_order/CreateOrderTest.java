package net.chinhung.application.endpoint.order.impl.create_order;

import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.OrderLine;
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
import java.util.stream.Stream;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class CreateOrderTest {

    private javax.validation.Validator validator;

    @BeforeEach
    public void init() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Stream<Arguments> testItemsValidation() {
        final OrderLine invalidOrderLine = new OrderLine("", ANY_STRING, ANY_INT);
        final OrderLine validCoreLine = new OrderLine(ANY_STRING, ANY_STRING, ANY_INT);

        return Stream.of(
                Arguments.of(new ArrayList<>(), false),
                Arguments.of(Collections.singletonList(invalidOrderLine), false),
                Arguments.of(Collections.singletonList(validCoreLine), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testItemsValidation(final List<OrderLine> coreLines, final boolean isValid) {
        final CreateOrder command = new CreateOrder();
        command.setOrderLines(coreLines);

        final Set<ConstraintViolation<CreateOrder>> constraintViolations = validator.validate(command);

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

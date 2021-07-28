package net.chinhung.core.component.order.impl.update;

import net.chinhung.core.component.order.Update;
import net.chinhung.core.order.CoreStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;

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
        final CoreStatus anyStatus = CoreStatus.APPROVED;
        final Update command = new Update(id, anyStatus);

        final Set<ConstraintViolation<Update>> constraintViolations = validator.validateProperty(command, "id");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }

    private static Stream<Arguments> testCoreStatusValidation() {
        final Function<CoreStatus, Arguments> fromCoreStatus = coreStatus -> Arguments.of(coreStatus, true);
        final Arguments ofNull = Arguments.of(null, false);

        final List<Arguments> returned = Stream.of(CoreStatus.values()).map(fromCoreStatus).collect(Collectors.toList());
        returned.add(ofNull);
        return returned.stream();
    }

    @ParameterizedTest
    @MethodSource
    public void testCoreStatusValidation(CoreStatus coreStatus, boolean isValid) {
        final Update command = new Update(ANY_STRING, coreStatus);

        final Set<ConstraintViolation<Update>> constraintViolations = validator.validateProperty(command, "coreStatus");

        Assertions.assertEquals(isValid, constraintViolations.size() == 0);
    }
}

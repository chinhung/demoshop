package net.chinhung.fundamental.aspect.validator;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidateActionDecoratorTest {

    private Object anyAction = new Object();
    private Object anyResult = new Object();

    private ActionService mockDecoratee = (action) -> {
        if (!anyAction.equals(action)) {
            Assertions.fail();
        }
        return anyResult;
    };

    private SpyDecorator spyDecorator;

    @BeforeEach
    public void init() {
        spyDecorator = new SpyDecorator();
        spyDecorator.setDecratee(mockDecoratee);
    }

    @Test
    public void testExecute_Valid() {
        final Validator isValid = (action) -> {
            if (spyDecorator.executeIsCalled) {
                Assertions.fail("action validation should executed before the decoratee");
            }

            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
            return true;
        };
        final ValidateActionDecorator validateActionDecorator = new ValidateActionDecorator(spyDecorator, isValid);

        Object result = validateActionDecorator.execute(anyAction);

        Assertions.assertEquals(anyResult, result);
    }

    @Test
    public void testExecute_Invalid() {
        final Validator isValid = (action) -> {
            if (spyDecorator.executeIsCalled) {
                Assertions.fail("action validation should executed before the decoratee");
            }

            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
            return false;
        };
        final ValidateActionDecorator validateActionDecorator = new ValidateActionDecorator(spyDecorator, isValid);

        Assertions.assertThrows(RuntimeException.class, () -> {
            validateActionDecorator.execute(anyAction);
        });
    }
}

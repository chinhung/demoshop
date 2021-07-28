package net.chinhung.fundamental.aspect.listener;

import net.chinhung.fundamental.aspect.ActionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AfterDecoratorTest {

    private Object anyAction = new Object();
    private Object anyResult = new Object();

    @Test
    public void testExecute() {
        final ActionService mockDecoratee = (action) -> {
            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
            return anyResult;
        };
        final AfterListener mockListener = (action, result) -> {
            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
            if (!anyResult.equals(result)) {
                Assertions.fail();
            }
        };
        final AfterDecorator afterDecorator = new AfterDecorator(mockDecoratee, mockListener);

        Object result = afterDecorator.execute(anyAction);

        Assertions.assertEquals(anyResult, result);
    }
}

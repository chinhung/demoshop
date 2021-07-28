package net.chinhung.fundamental.aspect.listener;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeforeDecoratorTest {

    private Object anyAction = new Object();
    private Object anyResult = new Object();

    @Test
    public void testExecute() {
        final SpyDecorator spyDecorator = new SpyDecorator();
        final ActionService mockDecoratee = (action) -> {
            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
            return anyResult;
        };
        spyDecorator.setDecratee(mockDecoratee);
        final BeforeListener mockListener = (action) -> {
            if (spyDecorator.executeIsCalled) {
                Assertions.fail("before decorator should been called before the decoratee");
            }

            if (!anyAction.equals(action)) {
                Assertions.fail();
            }
        };
        final BeforeDecorator beforeDecorator = new BeforeDecorator(spyDecorator, mockListener);

        Object result = beforeDecorator.execute(anyAction);

        Assertions.assertEquals(anyResult, result);
    }
}

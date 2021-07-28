package net.chinhung.fundamental.aspect.listener;

import net.chinhung.fundamental.aspect.ActionService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AroundDecoratorTest {

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
        final AroundListener mockListener = new AroundListener() {
            @Override
            public void onBefore(Object action) {
                if (spyDecorator.executeIsCalled) {
                    Assertions.fail("on before should been called before the decoratee");
                }

                if (!anyAction.equals(action)) {
                    Assertions.fail();
                }
            }

            @Override
            public void onAfter(Object action, Object result) {
                if (!anyAction.equals(action)) {
                    Assertions.fail();
                }

                if (!anyResult.equals(result)) {
                    Assertions.fail();
                }
            }
        };
        final AroundDecorator aroundDecorator = new AroundDecorator(spyDecorator, mockListener);

        Object result = aroundDecorator.execute(anyAction);

        Assertions.assertEquals(anyResult, result);
    }
}

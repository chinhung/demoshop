package net.chinhung.fundamental.aspect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SpyDecoratorTest {

    @Test
    public void testExecute() {
        final SpyDecorator spyDecorator = new SpyDecorator();
        final ActionService decoratee = Mockito.mock(ActionService.class);
        spyDecorator.setDecratee(decoratee);
        Assertions.assertFalse(spyDecorator.executeIsCalled);

        spyDecorator.execute(Mockito.any());

        Assertions.assertTrue(spyDecorator.executeIsCalled);
    }

    @Test
    public void testNoDecoratee() {
        final SpyDecorator spyDecorator = new SpyDecorator();

        Assertions.assertThrows(RuntimeException.class, () -> {
            spyDecorator.execute(new Object());
        });
    }

    @Test
    public void testPreCondition_True() {
        final SpyDecorator spyDecorator = new SpyDecorator();
        final ActionService decoratee = Mockito.mock(ActionService.class);
        spyDecorator.setDecratee(decoratee);
        spyDecorator.setPreCondition(() -> true);

        spyDecorator.execute(Mockito.any());

        Assertions.assertTrue(spyDecorator.executeIsCalled);
    }

    @Test
    public void testPreCondition_False() {
        final SpyDecorator spyDecorator = new SpyDecorator();
        final ActionService decoratee = Mockito.mock(ActionService.class);
        spyDecorator.setDecratee(decoratee);
        spyDecorator.setPreCondition(() -> false);

        Assertions.assertThrows(RuntimeException.class, () -> {
            spyDecorator.execute(new Object());
        });
    }
}

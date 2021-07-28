package net.chinhung.fundamental.domain_event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CompositeEventHandlerTest {

    private final Object anyEvent = new Object();
    private final Object anyAnalysedResult = new Object();
    private final Object result1 = new Object();
    private final Object result2 = new Object();

    @Test
    public void testHandle() {
        final EventHandler mockHandler1 = getMockEventHandler(anyEvent, result1);
        final EventHandler mockHandler2 = getMockEventHandler(anyEvent, result2);
        final List<EventHandler> handlers = Arrays.asList(mockHandler1, mockHandler2);
        final CompositeAnalyser mockAnalyser = getMockCompositeAnalyser(Arrays.asList(result1, result2), anyAnalysedResult);
        final CompositeEventHandler compositeEventHandler = new CompositeEventHandler(handlers, mockAnalyser);

        final Object analysedResult = compositeEventHandler.handle(anyEvent);

        Assertions.assertEquals(anyAnalysedResult, analysedResult);
    }

    private EventHandler getMockEventHandler(final Object exceptedEvent, final Object result) {
        return event -> {
            if (!exceptedEvent.equals(event)) {
                Assertions.fail();
            }
            return result;
        };
    }

    private CompositeAnalyser getMockCompositeAnalyser(final List<Object> exceptedResults, final Object analysedResult) {
        return results -> {
            exceptedResults.stream().forEach(result -> {
                if (!results.contains(result)) {
                    Assertions.fail();
                }
            });
            if (results.size() != exceptedResults.size()) {
                Assertions.fail();
            }
            return analysedResult;
        };
    }
}

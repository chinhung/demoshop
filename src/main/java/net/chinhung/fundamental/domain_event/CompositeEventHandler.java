package net.chinhung.fundamental.domain_event;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeEventHandler<DomainEvent, HandleResult> implements EventHandler<DomainEvent, HandleResult> {

    private final List<EventHandler<DomainEvent, HandleResult>> handlers;
    private final CompositeAnalyser<HandleResult> analyser;

    public CompositeEventHandler(
        List<EventHandler<DomainEvent, HandleResult>> handlers,
        CompositeAnalyser<HandleResult> analyser
    ) {
        this.handlers = handlers;
        this.analyser = analyser;
    }

    @Override
    public HandleResult handle(DomainEvent domainEvent) {
        List<HandleResult> results = handlers.stream()
            .map(handler -> handler.handle(domainEvent)).collect(Collectors.toList());
        return analyser.analyze(results);
    }
}

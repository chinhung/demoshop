package net.chinhung.fundamental.domain_event;

public interface EventHandler<DomainEvent, HandleResult> {
    HandleResult handle(DomainEvent domainEvent);
}

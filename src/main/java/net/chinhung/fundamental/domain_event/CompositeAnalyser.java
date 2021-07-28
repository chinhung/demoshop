package net.chinhung.fundamental.domain_event;

import java.util.List;

public interface CompositeAnalyser<HandleResult> {
    HandleResult analyze(List<HandleResult> results);
}

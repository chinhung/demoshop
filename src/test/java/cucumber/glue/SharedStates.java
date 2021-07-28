package cucumber.glue;

import net.chinhung.application.Application;
import net.chinhung.application.CompositeRoot;
import net.chinhung.application.Resource;
import net.chinhung.platform.resource.InMemoryInventoryDataSource;
import net.chinhung.platform.resource.InMemoryOrderDataSource;
import net.chinhung.platform.resource.InMemoryProductDataSource;
import net.chinhung.platform.resource.InMemoryResource;
import io.cucumber.guice.ScenarioScoped;

@ScenarioScoped
public class SharedStates {

    private final Application application;

    public SharedStates() {
        final Resource resource = new InMemoryResource(new InMemoryProductDataSource(), new InMemoryInventoryDataSource(), new InMemoryOrderDataSource());
        this.application = CompositeRoot.composite(resource);
    }

    public Application getApplication() {
        return application;
    }
}

package cucumber.glue;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@ScenarioScoped
public class CanaryStepdefs {

    @Inject
    public CanaryStepdefs(final SharedStates sharedStates) {
        if (sharedStates.getApplication() == null) {
            throw new RuntimeException("Application is not init");
        }
    }

    @Then("true to be true")
    public void trueToBeTrue() {
        Assertions.assertTrue(true);
    }
}

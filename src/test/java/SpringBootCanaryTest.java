import net.chinhung.platform.ShopApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ShopApplication.class)
public class SpringBootCanaryTest {

    @Test
    public void contextLoads() {
        Assertions.assertTrue(true);
    }
}

package net.chinhung.platform;

import net.chinhung.application.Application;
import net.chinhung.application.CompositeRoot;
import net.chinhung.application.Resource;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.platform.resource.InMemoryInventoryDataSource;
import net.chinhung.platform.resource.InMemoryOrderDataSource;
import net.chinhung.platform.resource.InMemoryProductDataSource;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import net.chinhung.platform.resource.InMemoryResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BeanConfiguration {

    private Application application;

    @PostConstruct
    public void startApplication() {
        Resource resource = new InMemoryResource(new InMemoryProductDataSource(), new InMemoryInventoryDataSource(), new InMemoryOrderDataSource());
        this.application = CompositeRoot.composite(resource);
    }

    @Bean
    public ProductEndpoint productEndpoint() {
        return application.getProductEndpoint();
    }

    @Bean
    public OrderEndpoint orderEndpoint() {
        return application.getOrderEndpoint();
    }
}
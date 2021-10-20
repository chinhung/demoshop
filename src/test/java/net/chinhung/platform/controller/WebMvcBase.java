package net.chinhung.platform.controller;

import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.reset;

@WebMvcTest
public class WebMvcBase {

    @Autowired
    public WebApplicationContext wac;

    @MockBean
    public ProductEndpoint productEndpoint;

    @MockBean
    public OrderEndpoint orderEndpoint;

    public MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @AfterEach
    public void tearDown() {
        reset(orderEndpoint);
        reset(productEndpoint);
    }
}

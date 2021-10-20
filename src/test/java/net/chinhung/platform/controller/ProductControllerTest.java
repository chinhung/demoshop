package net.chinhung.platform.controller;

import net.chinhung.application.endpoint.product.ProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends WebMvcBase {

    @Test
    public void findProducts() throws Exception {
        Mockito.when(productEndpoint.findProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/apple/product/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createProduct() throws Exception {
        Mockito.when(productEndpoint.createProduct(any())).thenReturn(new ProductDTO());

        MockHttpServletRequestBuilder req = post("/apple/product/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"book\",\"price\":100}");

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

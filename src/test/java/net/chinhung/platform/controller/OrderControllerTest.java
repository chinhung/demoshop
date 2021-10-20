package net.chinhung.platform.controller;

import net.chinhung.application.endpoint.order.OrderDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest extends WebMvcBase {

    @Test
    public void findOrders() throws Exception {
        Mockito.when(orderEndpoint.findOrders()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/apple/order/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(orderEndpoint.findById("someOrderId")).thenReturn(new OrderDTO());

        mockMvc.perform(get("/apple/order/order/someOrderId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createOrder() throws Exception {
        Mockito.when(orderEndpoint.createOrder(any())).thenReturn(new OrderDTO());

        MockHttpServletRequestBuilder req = post("/apple/order/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"orderLines\":[]}");

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

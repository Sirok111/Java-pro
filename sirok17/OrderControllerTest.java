package sirok17.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sirok15.model.Order;
import sirok15.model.Product;
import sirok15.service.OrderService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    private OrderService service;
    private OrderController controller;

    @BeforeEach
    void setUp() {
        service = mock(OrderService.class);
        controller = new OrderController(service);
    }

    @Test
    void testGetOrder() {
        Order order = new Order();
        order.setId(1L);
        when(service.getOrder(1L)).thenReturn(order);

        Order result = controller.getOrder(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testAddOrder() {
        Order order = new Order();
        when(service.createOrder(any())).thenReturn(order);

        Order result = controller.addOrder(order);

        assertNotNull(result);
    }

    @Test
    void testDeleteOrder() {
        controller.deleteOrder(1L);
        verify(service).deleteOrder(1L);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        controller.addProduct(1L, product);
        verify(service).addProduct(1L, product);
    }

    @Test
    void testRemoveProduct() {
        controller.removeProduct(1L, 10L);
        verify(service).removeProduct(1L, 10L);
    }
}

package sirok14.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sirok14.model.Order;
import sirok14.model.Product;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService service;

    @BeforeEach
    void setUp() {
        service = new OrderService();
    }

    @Test
    void testAddOrder() {
        Order order = new Order(null, "John Doe", List.of());
        Order saved = service.addOrder(order);
        assertNotNull(saved.getId());
        assertEquals("John Doe", saved.getCustomerName());
    }

    @Test
    void testGetOrder_Success() {
        Order order = service.addOrder(new Order(null, "Jane", List.of()));
        Order found = service.getOrder(order.getId());
        assertEquals("Jane", found.getCustomerName());
    }

    @Test
    void testGetOrder_NotFound() {
        assertThrows(NoSuchElementException.class, () -> service.getOrder(999L));
    }

    @Test
    void testUpdateOrder() {
        Order order = service.addOrder(new Order(null, "Old Name", List.of()));
        Order updated = service.updateOrder(order.getId(), new Order(null, "New Name", List.of()));
        assertEquals("New Name", updated.getCustomerName());
    }

    @Test
    void testAddProduct() {
        Order order = service.addOrder(new Order(null, "Test", List.of()));
        Product product = new Product(1L, "Pizza", 10.0);
        service.addProduct(order.getId(), product);

        Order updated = service.getOrder(order.getId());
        assertEquals(1, updated.getProducts().size());
        assertEquals("Pizza", updated.getProducts().get(0).getName());
    }

    @Test
    void testRemoveProduct() {
        Order order = service.addOrder(new Order(null, "Test", List.of()));
        Product product = new Product(1L, "Burger", 5.0);
        service.addProduct(order.getId(), product);
        service.removeProduct(order.getId(), 1L);

        Order updated = service.getOrder(order.getId());
        assertTrue(updated.getProducts().isEmpty());
    }

    @Test
    void testDeleteOrder() {
        Order order = service.addOrder(new Order(null, "Delete Me", List.of()));
        service.deleteOrder(order.getId());
        assertThrows(NoSuchElementException.class, () -> service.getOrder(order.getId()));
    }
}


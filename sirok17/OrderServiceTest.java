package sirok17.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sirok15.model.Order;
import sirok15.model.Product;
import sirok15.repository.OrderRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        when(repository.save(any())).thenReturn(order);

        Order result = service.createOrder(order);

        assertNotNull(result);
        verify(repository).save(order);
    }

    @Test
    void testGetOrderFound() {
        Order order = new Order();
        order.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(order));

        Order result = service.getOrder(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testAddProduct() {
        Order order = new Order();
        Product product = new Product();
        product.setId(10L);

        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(any())).thenReturn(order);

        service.addProduct(1L, product);

        assertTrue(order.getProducts().contains(product));
    }
}

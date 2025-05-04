package sirok15.service;

import org.springframework.stereotype.Service;
import sirok14.model.Order;
import sirok14.model.Product;
import sirok14.repository.OrderRepository;

import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order getOrder(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existing = getOrder(id);
        existing.setCustomerName(updatedOrder.getCustomerName());
        return repository.save(existing);
    }

    public Order addProduct(Long orderId, Product product) {
        Order order = getOrder(orderId);
        order.getProducts().add(product);
        return repository.save(order);
    }

    public Order removeProduct(Long orderId, Long productId) {
        Order order = getOrder(orderId);
        order.getProducts().removeIf(p -> p.getId().equals(productId));
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}


package sirok15.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    private Long id; // Можна зробити автоінкрементом, якщо потрібно

    private String name;
    private double price;

    // Constructors, getters, setters
}

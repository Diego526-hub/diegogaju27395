package com.example.question4_Ecommerce_.api.service;

import com.example.question4_Ecommerce_.api.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private Long nextId = 1L;

    public ProductService() {
        initializeProducts();
    }

    private void initializeProducts() {
        addProduct(new Product(nextId++, "iPhone 14", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        addProduct(new Product(nextId++, "Samsung Galaxy S23", "Android flagship phone", 899.99, "Electronics", 30, "Samsung"));
        addProduct(new Product(nextId++, "MacBook Pro", "Professional laptop", 2499.99, "Electronics", 20, "Apple"));
        addProduct(new Product(nextId++, "Nike Air Max", "Running shoes", 129.99, "Footwear", 100, "Nike"));
        addProduct(new Product(nextId++, "Adidas Ultraboost", "Comfortable running shoes", 149.99, "Footwear", 80, "Adidas"));
        addProduct(new Product(nextId++, "Sony WH-1000XM5", "Noise cancelling headphones", 399.99, "Electronics", 0, "Sony"));
        addProduct(new Product(nextId++, "Levi's 501 Jeans", "Classic denim jeans", 69.99, "Clothing", 150, "Levi's"));
        addProduct(new Product(nextId++, "North Face Jacket", "Waterproof winter jacket", 249.99, "Clothing", 45, "North Face"));
        addProduct(new Product(nextId++, "Dell XPS 13", "Compact laptop", 1299.99, "Electronics", 25, "Dell"));
        addProduct(new Product(nextId++, "Puma Sneakers", "Casual sneakers", 79.99, "Footwear", 120, "Puma"));
    }

    public List<Product> getAllProducts(Integer page, Integer limit) {
        List<Product> allProducts = new ArrayList<>(products.values());
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, allProducts.size());
            return start < allProducts.size() ? allProducts.subList(start, end) : new ArrayList<>();
        }
        return allProducts;
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByBrand(String brand) {
        return products.values().stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Product> searchProducts(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword) || 
                           p.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return products.values().stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Product> getInStockProducts() {
        return products.values().stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    public Product addProduct(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(nextId++);
        }
        products.put(product.getProductId(), product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        if (products.containsKey(id)) {
            product.setProductId(id);
            products.put(id, product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public Optional<Product> updateStock(Long id, int quantity) {
        Product product = products.get(id);
        if (product != null) {
            product.setStockQuantity(quantity);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}

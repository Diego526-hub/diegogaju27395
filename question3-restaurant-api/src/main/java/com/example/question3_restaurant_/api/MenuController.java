package com.example.question3_restaurant_.api;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private Map<Long, MenuItem> menuItems = new HashMap<>();
    private Long nextId = 1L;

    public MenuController() {
        menuItems.put(nextId, new MenuItem(nextId++, "Spring Rolls", "Crispy vegetable rolls with sweet chili sauce", 6.99, "Appetizer", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Caesar Salad", "Fresh romaine lettuce with parmesan and croutons", 8.99, "Appetizer", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Grilled Salmon", "Atlantic salmon with lemon butter sauce", 24.99, "Main Course", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Beef Steak", "Prime ribeye steak with garlic mashed potatoes", 32.99, "Main Course", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Chicken Alfredo", "Fettuccine pasta with creamy alfredo sauce", 18.99, "Main Course", false));
        menuItems.put(nextId, new MenuItem(nextId++, "Chocolate Lava Cake", "Warm chocolate cake with vanilla ice cream", 7.99, "Dessert", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Tiramisu", "Classic Italian coffee-flavored dessert", 6.99, "Dessert", true));
        menuItems.put(nextId, new MenuItem(nextId++, "Fresh Lemonade", "Homemade lemonade with mint", 3.99, "Beverage", true));
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems.values());
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItems.get(id);
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {
        return menuItems.values().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems(@RequestParam(defaultValue = "true") boolean available) {
        return menuItems.values().stream()
                .filter(item -> item.isAvailable() == available)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<MenuItem> searchMenuItems(@RequestParam String name) {
        return menuItems.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        menuItems.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id) {
        MenuItem item = menuItems.get(id);
        if (item != null) {
            item.setAvailable(!item.isAvailable());
        }
        return item;
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItems.remove(id);
    }
}

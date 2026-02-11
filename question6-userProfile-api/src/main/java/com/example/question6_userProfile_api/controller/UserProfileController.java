package com.example.question6_userProfile_api.controller;

import com.example.question6_userProfile_api.model.ApiResponse;
import com.example.question6_userProfile_api.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private final List<UserProfile> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        user.setUserId(counter.incrementAndGet());
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User profile created successfully", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(u -> ResponseEntity.ok(new ApiResponse<>(true, "User found", u)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long id, @RequestBody UserProfile updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                updatedUser.setUserId(id);
                users.set(i, updatedUser);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile updated successfully", updatedUser));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(id));
        if (removed) {
            return ResponseEntity.ok(new ApiResponse<>(true, "User profile deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@PathVariable String username) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getUsername().toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", result));
    }

    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", result));
    }

    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int minAge, @RequestParam int maxAge) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getAge() >= minAge && u.getAge() <= maxAge)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", result));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(u -> {
                    u.setActive(true);
                    return ResponseEntity.ok(new ApiResponse<>(true, "User profile activated successfully", u));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(u -> {
                    u.setActive(false);
                    return ResponseEntity.ok(new ApiResponse<>(true, "User profile deactivated successfully", u));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }
}

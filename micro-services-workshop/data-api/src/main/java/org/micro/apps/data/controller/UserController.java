package org.micro.apps.data.controller;

import lombok.RequiredArgsConstructor;
import org.micro.apps.common.dto.User;
import org.micro.apps.data.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tibinatomy
 */

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.create(user));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/get/email")
    public ResponseEntity<User> getByMail(@RequestBody User user) {
        try {
            if (null != user.getEmail())
                return ResponseEntity.ok(userService.getByEmail(user.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/get/id")
    public ResponseEntity<User> getById(@RequestBody User user) {
        try {
            if (null != user.getId())
                return ResponseEntity.ok(userService.get(user.getId()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/delete/id")
    public ResponseEntity<User> delete(@RequestBody User user) {
        try {
            if (null != user.getId()) {
                userService.delete(user.getId());
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }
}

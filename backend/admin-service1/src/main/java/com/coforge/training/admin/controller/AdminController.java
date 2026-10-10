package com.coforge.training.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.admin.dto.ProductDTO;
import com.coforge.training.admin.dto.PurchaseDTO;
import com.coforge.training.admin.dto.UserDTO;
import com.coforge.training.admin.model.Admin;
import com.coforge.training.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ================= ADMIN =================

    @PostMapping("/save")
    public Admin saveAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin) {

        return adminService.login(
                admin.getUsername(),
                admin.getPassword()
        );

    }

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin deleted successfully";
    }

    // ================= USERS =================

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @PutMapping("/users/activate/{id}")
    public UserDTO activateUser(@PathVariable Long id) {
        return adminService.activateUser(id);
    }

    // ================= PRODUCTS =================

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return adminService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return adminService.getProductById(id);
    }

    // ================= PURCHASES =================

    @GetMapping("/purchases")
    public List<PurchaseDTO> getAllPurchases() {
        return adminService.getAllPurchases();
    }

    @GetMapping("/purchases/{id}")
    public PurchaseDTO getPurchase(@PathVariable Long id) {
        return adminService.getPurchaseById(id);
    }

}
package com.coforge.training.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.admin.dto.ProductDTO;
import com.coforge.training.admin.dto.PurchaseDTO;
import com.coforge.training.admin.dto.UserDTO;
import com.coforge.training.admin.feign.FinanceServiceClient;
import com.coforge.training.admin.feign.ProductServiceClient;
import com.coforge.training.admin.feign.UserServiceClient;
import com.coforge.training.admin.model.Admin;
import com.coforge.training.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private FinanceServiceClient financeServiceClient;

    // ================= ADMIN =================

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Admin login(String username, String password) {

        System.out.println("====================================");
        System.out.println("Username Entered : " + username);
        System.out.println("Password Entered : " + password);

        Admin admin = adminRepository.findByUsername(username).orElse(null);

        if (admin == null) {
            System.out.println("No admin found with username : " + username);
            return null;
        }

        System.out.println("Admin Found : " + admin.getAdminName());
        System.out.println("Username in DB : " + admin.getUsername());
        System.out.println("Password in DB : " + admin.getPassword());

        if (admin.getPassword().equals(password)) {
            System.out.println("Login Successful");
            return admin;
        }

        System.out.println("Password Mismatch");
        return null;
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    // ================= USER =================

    @Override
    public List<UserDTO> getAllUsers() {
        return userServiceClient.getAllUsers();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userServiceClient.getUserById(id);
    }

    @Override
    public UserDTO activateUser(Long id) {
        return userServiceClient.activateUser(id);
    }

    // ================= PRODUCT =================

    @Override
    public List<ProductDTO> getAllProducts() {
        return productServiceClient.getAllProducts();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productServiceClient.getProductById(id);
    }

    // ================= PURCHASE =================

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return financeServiceClient.getAllPurchases();
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        return financeServiceClient.getPurchaseById(id);
    }

}
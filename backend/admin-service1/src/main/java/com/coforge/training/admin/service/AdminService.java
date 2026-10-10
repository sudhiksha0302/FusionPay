package com.coforge.training.admin.service;

import java.util.List;

import com.coforge.training.admin.dto.ProductDTO;
import com.coforge.training.admin.dto.PurchaseDTO;
import com.coforge.training.admin.dto.UserDTO;
import com.coforge.training.admin.model.Admin;

public interface AdminService {

    // ================= ADMIN =================

    Admin saveAdmin(Admin admin);

    List<Admin> getAllAdmins();

    Admin getAdminById(Long adminId);

    Admin getAdminByUsername(String username);

    Admin login(String username, String password);

    void deleteAdmin(Long adminId);

    // ================= USER SERVICE =================

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO activateUser(Long id);

    // ================= PRODUCT SERVICE =================

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    // ================= FINANCE SERVICE =================

    List<PurchaseDTO> getAllPurchases();

    PurchaseDTO getPurchaseById(Long id);

}
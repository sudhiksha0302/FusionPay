package com.coforge.training.userservice.service;

import java.util.List;

import com.coforge.training.userservice.dto.BankResponseDTO;
import com.coforge.training.userservice.dto.CardResponseDTO;
import com.coforge.training.userservice.dto.ChangePasswordRequestDTO;
import com.coforge.training.userservice.dto.ForgotPasswordRequestDTO;
import com.coforge.training.userservice.dto.LoginRequestDTO;
import com.coforge.training.userservice.dto.LoginResponseDTO;
import com.coforge.training.userservice.dto.RegisterRequestDTO;
import com.coforge.training.userservice.dto.SendOtpRequestDTO;
import com.coforge.training.userservice.dto.UserResponseDTO;
import com.coforge.training.userservice.dto.VerifyOtpRequestDTO;

public interface UserService {

    UserResponseDTO register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    BankResponseDTO getBankDetails(Long userId);

    CardResponseDTO getCardDetails(Long userId);
    
    String forgotPassword(ForgotPasswordRequestDTO request);
    
    UserResponseDTO activateUser(Long id);
    
    String sendOtp(SendOtpRequestDTO request);

    String verifyOtp(VerifyOtpRequestDTO request);

    String changePassword(ChangePasswordRequestDTO request);

}
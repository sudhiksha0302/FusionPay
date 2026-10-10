package com.coforge.training.userservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coforge.training.userservice.dto.*;
import com.coforge.training.userservice.entity.*;
import com.coforge.training.userservice.enums.CardStatus;
import com.coforge.training.userservice.enums.CardType;
import com.coforge.training.userservice.exception.ResourceAlreadyExistsException;
import com.coforge.training.userservice.exception.ResourceNotFoundException;
import com.coforge.training.userservice.repository.*;
import com.coforge.training.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BankDetailsRepository bankRepository;
    private final CardDetailsRepository cardRepository;

    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {

        System.out.println("========== REGISTER START ==========");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceAlreadyExistsException("Email already exists");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new ResourceAlreadyExistsException("Username already exists");

        System.out.println("Step 1: Validation Passed");

        User user = User.builder()
                .name(request.getName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(request.getPassword())
                .address(request.getAddress())
                .verified(false)
                .activated(false)
                .build();

        userRepository.save(user);

        System.out.println("Step 2: User Saved. ID = " + user.getUserId());

        BankDetails bank = BankDetails.builder()
                .bankName(request.getBankName())
                .accountNumber(request.getAccountNumber())
                .ifscCode(request.getIfscCode())
                .user(user)
                .build();

        bankRepository.save(bank);

        System.out.println("Step 3: Bank Details Saved");

        Double totalLimit;
        Double joiningFee;

        if (request.getCardType() == CardType.GOLD) {

            totalLimit = 50000.0;
            joiningFee = 500.0;

        } else {

            totalLimit = 100000.0;
            joiningFee = 1000.0;

        }

        System.out.println("Step 4: Card Type = " + request.getCardType());
        System.out.println("Joining Fee = " + joiningFee);
        System.out.println("Total Limit = " + totalLimit);

        String cardNumber = generateUniqueCardNumber();

        System.out.println("Generated Card Number = " + cardNumber);

        CardDetails card = CardDetails.builder()
                .cardType(request.getCardType())
                .cardNumber(cardNumber)
                .expiryDate(LocalDate.now().plusYears(5))
                .cardStatus(CardStatus.PENDING)
                .joiningFee(joiningFee)
                .totalLimit(totalLimit)
                .availableLimit(totalLimit)
                .user(user)
                .build();

        System.out.println("Step 5: Saving Card...");

        cardRepository.save(card);

        System.out.println("Step 6: Card Saved Successfully");
        System.out.println("========== REGISTER END ==========");

        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .username(user.getUsername())
                .verified(user.isVerified())
                .activated(user.isActivated())
                .build();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid Username"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return LoginResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .message("Login Successful")
                .build();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .verified(user.isVerified())
                .activated(user.isActivated())
                .build();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> UserResponseDTO.builder()
                        .userId(user.getUserId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .address(user.getAddress())
                        .username(user.getUsername())
                        .verified(user.isVerified())
                        .activated(user.isActivated())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public BankResponseDTO getBankDetails(Long userId) {

        BankDetails bank = bankRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bank Details Not Found"));

        return BankResponseDTO.builder()
                .bankId(bank.getBankId())
                .bankName(bank.getBankName())
                .accountNumber(bank.getAccountNumber())
                .ifscCode(bank.getIfscCode())
                .build();
    }

    @Override
    public CardResponseDTO getCardDetails(Long userId) {

        CardDetails card = cardRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Card Details Not Found"));

        return CardResponseDTO.builder()
                .cardId(card.getCardId())
                .cardType(card.getCardType())
                .cardNumber(card.getCardNumber())
                .joiningFee(card.getJoiningFee())
                .totalLimit(card.getTotalLimit())
                .availableLimit(card.getAvailableLimit())
                .expiryDate(card.getExpiryDate())
                .cardStatus(card.getCardStatus())
                .build();
    }
    @Override
    public String forgotPassword(ForgotPasswordRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setPassword(request.getNewPassword());

        userRepository.save(user);

        return "Password Updated Successfully";

    }
    
 // ================= FORGOT PASSWORD =================

    @Override
    public String sendOtp(SendOtpRequestDTO request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Phone Number not found"));

        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        user.setOtp(otp);
        user.setOtpVerified(false);

        userRepository.save(user);

        // Demo purpose (later replace with SMS service)
        return "OTP Sent Successfully. Demo OTP: " + otp;
    }

    @Override
    public String verifyOtp(VerifyOtpRequestDTO request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Phone Number not found"));

        if (!user.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        user.setOtpVerified(true);

        userRepository.save(user);

        return "OTP Verified Successfully";
    }

    @Override
    public String changePassword(ChangePasswordRequestDTO request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Phone Number not found"));

        if (!user.isOtpVerified()) {
            throw new RuntimeException("OTP Verification Required");
        }

        user.setPassword(request.getNewPassword());

        // Clear OTP after successful password change
        user.setOtp(null);
        user.setOtpVerified(false);

        userRepository.save(user);

        return "Password Changed Successfully";
    }

    // ==========================
    // Utility Methods
    // ==========================

    private String generateUniqueCardNumber() {

        Random random = new Random();

        while (true) {

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 16; i++) {
                builder.append(random.nextInt(10));
            }

            String cardNumber = builder.toString();

            if (!cardRepository.existsByCardNumber(cardNumber)) {
                return cardNumber;
            }
        }
    }
    
    @Override
    public UserResponseDTO activateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setVerified(true);
        user.setActivated(true);

        userRepository.save(user);

        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .address(user.getAddress())
                .verified(user.isVerified())
                .activated(user.isActivated())
                .build();
    }

}
package com.example.demo.Service;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.CustomerDetail;
import com.example.demo.Entity.OtpRequest;
import com.example.demo.Entity.OtpResponse;
import com.example.demo.Entity.OtpStatus;
import com.example.demo.Entity.OtpValidation;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.config.Twilloconfig;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService {

    @Autowired
    private Twilloconfig twilloconfig;
    @Autowired
    private CustomerRepo customerRepo;

    private final ConcurrentMap<String, String> otpmap = new ConcurrentHashMap<>();

    private String generateOtp() {
        return new DecimalFormat("000000")
                   .format(new Random().nextInt(999999));
    }

    public OtpResponse sendSms(OtpRequest otpRequest) {
        OtpResponse otpResponse = null;
        if (otpRequest == null) {
            otpResponse = new OtpResponse(OtpStatus.FAILED, "OtpRequest is null");
            return otpResponse;
        }
        
        try {
            Twilio.init(twilloconfig.getAccount_Sid(), twilloconfig.getAuth_Token());
            PhoneNumber to = new PhoneNumber(otpRequest.getNumber());
            PhoneNumber from = new PhoneNumber(twilloconfig.getTrail_Number());
            String otp = generateOtp();
            String otpmessage = "Dear Customer, OTP For Jobizz" + otp;
            Message message = Message.creator(to, from, otpmessage).create();

            otpmap.put(otpRequest.getNumber(), otp); // Store the OTP in the map
            System.out.println("Stored OTP for " + otpRequest.getNumber() + ": " + otp);
            otpResponse = new OtpResponse(OtpStatus.DELIVERED, otpmessage);
            return otpResponse;
        } catch (Exception e) {
            otpResponse = new OtpResponse(OtpStatus.FAILED, e.getMessage());
            return otpResponse;
        }
    }

    public String validateOtp(OtpValidation otpValidation) {
        if (otpValidation == null) {
            return "OtpValidation is null";
        }
        
        String otp = otpmap.get(otpValidation.getNumber());
        if (otp == null) {
            return "OTP not found for the given number";
        }

        if (otp.equals(otpValidation.getOneTimePassword())) {
            otpmap.remove(otpValidation.getNumber());
            if (!customerRepo.findById(otpValidation.getNumber()).isPresent()) {
                CustomerDetail customerDetail = new CustomerDetail();
                customerDetail.setPhonenumber(otpValidation.getNumber());
                customerRepo.save(customerDetail);
            }
            return "OTP is Valid";
        } else {
            return "OTP is Invalid";
        }
    }

    public boolean postData(CustomerDetail customerDetail, String id) {
        customerRepo.save(customerDetail);
        return true;
    }

    public CustomerDetail getData(String id) {
        return customerRepo.findById(id).orElse(null);
    }
}
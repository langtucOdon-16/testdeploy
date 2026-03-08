/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lớp tiện ích dùng để tạo mã OTP
 */
public class OTPUtil {
    
    public static String generateOTP() {
        int otp = (int) (Math.random()*900000) + 100000;
        return String.valueOf(otp);
    }
}

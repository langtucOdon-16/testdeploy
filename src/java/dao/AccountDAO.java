/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AccountDTO;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lop AccountDAO chiu trach nhiem truy xuat va thao tac du lieu
 * tai khoan tu co so du lieu
 */
public class AccountDAO extends DBContext {

    //Lấy tài khoản theo email và password trong cơ sở dữ liệu
    public AccountDTO getAccountByEmailAndPassword(String email, String password) {
        String sql = """
                     Select accountId, username, password, email, fullName, role
                     From Account
                     Where email = ? And password = ?
                     """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AccountDTO acc = new AccountDTO();
                acc.setAccountId(rs.getInt("accountId"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setFullName(rs.getString("fullName"));
                acc.setRole(rs.getInt("role"));
                return acc;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving account by email and password", e);
        }
        return null;
    }

    //Thêm tài khoản mới vào cơ sở dữ liệu
    public void insertAccount(String username, String password, String email, String fullName, int role) {
        String sql = "INSERT INTO Account(username, password, email, fullName, role) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, fullName);
            ps.setInt(5, role);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting account", e);
        }
    }

    //Lấy account theo email trong cơ sở dữ liệu
    public AccountDTO getAccountByEmail(String email) {
        String sql = """
                    Select accountId, username, password, email, fullName, role
                    From Account
                    Where email = ? 
                    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AccountDTO acc = new AccountDTO();
                acc.setAccountId(rs.getInt("accountId"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setFullName(rs.getString("fullName"));
                acc.setRole(rs.getInt("role"));
                return acc;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving account by email", e);
        }
        return null;
    }

    //Cập nhật mật khẩu cho 1 tài khoản cụ thể
    public boolean updatePassword(String email, String newPassword) {
        String sql = "Update Account Set password = ? Where email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating password", e);
        }
    }

    //Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM Account WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("Error checking email existence", e);
        }
    }

    //Kiểm tra xem username đã tồn tại trong cơ sở dữ liệu chưa
    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM Account WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error checking username existence", e);
        }
    }

    //Cập nhật mật khẩu theo accountId
    public boolean updatePassword(int accountId, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE accountId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating password", e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AddressDTO;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lop AddressDAO chiu trach nhiem truy xuat va thao tac du lieu
 * dia chi tu co so du lieu
 */
public class AddressDAO extends DBContext {

    //Truy xuất tất cả các địa chỉ từ một tài khoản cụ thể
    public List<AddressDTO> getByAccountId(int accountId) {
        List<AddressDTO> list = new ArrayList<>();
        String sql = """
            SELECT addressId, addressName, phone
            FROM Address
            WHERE accountId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AddressDTO a = new AddressDTO();
                a.setAddressId(rs.getInt("addressId"));
                a.setAddressName(rs.getString("addressName"));
                a.setPhone(rs.getString("phone"));
                list.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving addresses by accountId", e);
        }
        return list;
    }

    //Chèn địa chỉ mới cho 1 tài khoản cụ thể
    public boolean insertAddress(int accountId, String addressName, String phone) {
        String sql = "INSERT INTO Address(accountId, addressName, phone) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.setString(2, addressName);
            ps.setString(3, phone);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Cập nhật một địa chỉ hiện có
    public boolean updateAddress(int addressId, int accountId,
            String addressName, String phone) {

        String sql = """
            UPDATE Address
            SET addressName = ?, phone = ?
            WHERE addressId = ? AND accountId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, addressName);
            ps.setString(2, phone);
            ps.setInt(3, addressId);
            ps.setInt(4, accountId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating address", e);
        }
    }

    //Xoá địa chỉ người dùng 
    public boolean deleteAddress(int addressId, int accountId) {

        String sql = """
            DELETE FROM Address
            WHERE addressId = ? AND accountId = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, addressId);
            ps.setInt(2, accountId);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting address", e);
        }
    }

    public String selectAddressName(int addressId) {
        String sql = "Select addressName from Address where addressId=?";
        String address = "";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, addressId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = rs.getString("addressName");
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting address", e);
        }
    }
}

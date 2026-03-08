/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.CommentDTO;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lop CommentDAO chiu trach nhiem truy xuat va thao tac du lieu
 * comment tu co so du lieu
 */
public class CommentDAO extends DBContext {

    //Lấy danh sách comment theo productId
    public List<CommentDTO> getCommentsByProductId(int productId) {

        List<CommentDTO> list = new ArrayList<>();

        String sql = """
                     SELECT c.commentId,
                            c.accountId,
                            c.content,
                            c.status,
                            c.parentId,
                            c.time,
                            c.productId,
                            a.username,
                            a.role
                     FROM Comment c
                     JOIN Account a ON c.accountId = a.accountId
                     WHERE c.productId = ?
                     ORDER BY c.time ASC
                     """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CommentDTO comment = new CommentDTO();

                comment.setCommentId(rs.getInt("commentId"));
                comment.setAccountId(rs.getInt("accountId"));
                comment.setContent(rs.getString("content"));
                comment.setStatus(rs.getBoolean("status"));

                int parentId = rs.getInt("parentId");
                if (rs.wasNull()) {
                    comment.setParentId(null);
                } else {
                    comment.setParentId(parentId);
                }

                comment.setTime(rs.getTimestamp("time"));
                comment.setProductId(rs.getInt("productId"));
                comment.setUsername(rs.getString("username"));
                comment.setRole(rs.getInt("role"));

                list.add(comment);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving comments", e);
        }

        return list;
    }

    //Lấy comment theo id
    public CommentDTO getCommentById(int commentId) {

        String sql = "SELECT * FROM Comment WHERE commentId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                CommentDTO c = new CommentDTO();

                c.setCommentId(rs.getInt("commentId"));
                c.setAccountId(rs.getInt("accountId"));
                c.setContent(rs.getString("content"));

                int parentId = rs.getInt("parentId");
                if (rs.wasNull()) {
                    c.setParentId(null);
                } else {
                    c.setParentId(parentId);
                }

                c.setProductId(rs.getInt("productId"));

                return c;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error get comment by id", e);
        }

        return null;
    }
    
    //Thêm comment
    public void insertComment(int accountId,
                              String content,
                              Integer parentId,
                              int productId) {

        String sql = """
                     INSERT INTO Comment (accountId, content, parentId, productId)
                     VALUES (?, ?, ?, ?)
                     """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ps.setString(2, content);

            if (parentId == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, parentId);
            }

            ps.setInt(4, productId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }
}
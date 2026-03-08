/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lop mo hinh Comment đại diện cho thuộc tính comment trong hệ thống
 */
public class CommentDTO {

    private int commentId;
    private int accountId;
    private String username;
    private int role;
    private String content;
    private boolean status;
    private Integer parentId;   
    private Date time;
    private int productId;

    public CommentDTO() {
    }

    public CommentDTO(int commentId, int accountId, String username, int role,
                      String content, boolean status,
                      Integer parentId, Date time, int productId) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.username = username;
        this.role = role;
        this.content = content;
        this.status = status;
        this.parentId = parentId;
        this.time = time;
        this.productId = productId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
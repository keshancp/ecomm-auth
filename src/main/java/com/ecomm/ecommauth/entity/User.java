package com.ecomm.ecommauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String createBy;

    private LocalDateTime createDate;

    @OneToMany(mappedBy="user")
    private Set<UserRole> userRoleList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", userRoleList=" + userRoleList +
                '}';
    }
}

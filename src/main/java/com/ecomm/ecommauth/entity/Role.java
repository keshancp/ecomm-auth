package com.ecomm.ecommauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role_code;

    private String role_name;

    private String role_descritption;

    private String createBy;

    private LocalDateTime createDate;

    @OneToMany(mappedBy="role")
    private Set<UserRole> userRoleList;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_code='" + role_code + '\'' +
                ", role_name='" + role_name + '\'' +
                ", role_descritption='" + role_descritption + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", userRoleList=" + userRoleList +
                '}';
    }
}

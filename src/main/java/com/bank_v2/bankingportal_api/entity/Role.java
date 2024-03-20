package com.bank_v2.bankingportal_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType roleName;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private RoleType roleType;

    public enum RoleType {
        ROLE_ADMIN(1),
        ROLE_USER(2);

        private final int id;

        RoleType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

}

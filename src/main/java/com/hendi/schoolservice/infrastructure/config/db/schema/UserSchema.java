package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.user.model.UserAccountModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class UserSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_role_id"), nullable = false)
    private UserRoleSchema role;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 300)
    private String password;

    @CreatedBy
    @Column(nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public UserSchema(UserAccountModel userAccountModel) {
        this.id = userAccountModel.getId();
        this.role = new UserRoleSchema(userAccountModel.getRole());
        this.username = userAccountModel.getUsername();
        this.password = userAccountModel.getPassword();
        this.createdBy = userAccountModel.getCreatedBy();
        this.updatedBy = userAccountModel.getUpdatedBy();
        this.createdAt = userAccountModel.getCreatedAt();
        this.updatedAt = userAccountModel.getUpdatedAt();
    }

    public UserAccountModel toUserAccountModel() {
        UserAccountModel userAccountModel = new UserAccountModel(
                this.username,
                this.password,
                this.role.toUserRoleModel());
        userAccountModel.setId(this.id);
        userAccountModel.setCreatedAt(this.createdAt);
        userAccountModel.setUpdatedAt(this.updatedAt);
        userAccountModel.setCreatedBy(this.createdBy);
        userAccountModel.setUpdatedBy(this.updatedBy);
        return userAccountModel;
    }

    public static List<UserSchema> toUserSchemaList(List<UserAccountModel> userAccountModels) {
        return userAccountModels.stream().map(UserSchema::new).toList();
    }

}

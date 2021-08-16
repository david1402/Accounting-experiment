package com.school.project.model.entities;

import com.school.project.model.AbstractEntity;
import com.school.project.model.types.UserAccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ACCOUNT")
public class UserAccount extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ACCOUNT_ROLE")
    @Enumerated(EnumType.STRING)
    private UserAccountType accountRole;

}

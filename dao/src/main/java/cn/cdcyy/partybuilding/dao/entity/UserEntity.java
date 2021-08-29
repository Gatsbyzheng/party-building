package cn.cdcyy.partybuilding.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

}

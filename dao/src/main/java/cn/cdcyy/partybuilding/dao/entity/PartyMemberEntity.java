package cn.cdcyy.partybuilding.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicInsert
@SelectBeforeUpdate
@DynamicUpdate
@Table(name = "party_member")
public class PartyMemberEntity extends BaseEntity {

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private Integer sex;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Date joinPartyDate;

    @Column(nullable = false)
    private Date realJoinPartyDate;

    @Column(nullable = false)
    private Integer partyMemberType;

}

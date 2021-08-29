package cn.cdcyy.partybuilding.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicInsert
@SelectBeforeUpdate
@DynamicUpdate
@Table(name = "points")
public class PointsEntity extends BaseEntity {

    @Column(nullable = false)
    private Long partyMemberId;

    @Column(nullable = false)
    private String partyMemberName;

    @Column(nullable = false)
    private Integer basePoints;

    @Column(nullable = false)
    private Integer rewardPoints;

    @Column(nullable = false)
    private Integer learnPoints;

    @Column(nullable = false)
    private Integer businessPoints;

    @Column(nullable = false)
    private Integer disciplinePoints;
}

package cn.cdcyy.partybuilding.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicInsert
@SelectBeforeUpdate
@DynamicUpdate
@Table(name = "news")
public class NewsEntity extends BaseEntity {
}

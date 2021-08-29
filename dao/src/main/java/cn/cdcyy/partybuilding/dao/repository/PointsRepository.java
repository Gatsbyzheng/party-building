package cn.cdcyy.partybuilding.dao.repository;

import cn.cdcyy.partybuilding.dao.entity.PointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<PointsEntity, Long> {
}

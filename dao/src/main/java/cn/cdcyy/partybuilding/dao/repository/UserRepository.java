package cn.cdcyy.partybuilding.dao.repository;

import cn.cdcyy.partybuilding.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

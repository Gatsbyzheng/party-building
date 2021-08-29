package cn.cdcyy.partybuilding.dao.repository;

import cn.cdcyy.partybuilding.dao.entity.PartyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMemberEntity, Long> {
}

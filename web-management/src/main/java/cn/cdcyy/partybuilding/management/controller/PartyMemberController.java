package cn.cdcyy.partybuilding.management.controller;

import cn.cdcyy.partybuilding.common.CommonResponse;
import cn.cdcyy.partybuilding.common.pojo.vo.PageParamPartyMemberVO;
import cn.cdcyy.partybuilding.dao.entity.PartyMemberEntity;
import cn.cdcyy.partybuilding.dao.entity.PointsEntity;
import cn.cdcyy.partybuilding.dao.repository.PartyMemberRepository;
import cn.cdcyy.partybuilding.dao.repository.PointsRepository;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("party-member")
public class PartyMemberController {

    private final PartyMemberRepository partyMemberRepository;

    private final PointsRepository pointsRepository;

    public PartyMemberController(PartyMemberRepository partyMemberRepository,
                                 PointsRepository pointsRepository) {
        this.partyMemberRepository = partyMemberRepository;
        this.pointsRepository = pointsRepository;
    }

    @PostMapping
    public CommonResponse<String> saveOrUpdate(@RequestBody PartyMemberEntity partyMemberEntity) {
        if (partyMemberEntity.getId() != null) {
            Optional<PartyMemberEntity> memberEntity = partyMemberRepository.findById(partyMemberEntity.getId());
            partyMemberEntity.setCreateTime(memberEntity.get().getCreateTime());
        }
        partyMemberEntity.setUpdateTime(new Date());
        partyMemberRepository.save(partyMemberEntity);
        if (partyMemberEntity.getId() == null) {
            PointsEntity pointsEntity = new PointsEntity();
            pointsEntity.setId(partyMemberEntity.getId());
            pointsEntity.setPartyMemberName(partyMemberEntity.getNickName());
            pointsEntity.setBasePoints(0);
            pointsEntity.setLearnPoints(0);
            pointsEntity.setBusinessPoints(0);
            pointsEntity.setDisciplinePoints(0);
            pointsEntity.setRewardPoints(0);
            pointsRepository.save(pointsEntity);
        }
        return CommonResponse.success("成功");
    }

    @GetMapping("{id}")
    public CommonResponse<PartyMemberEntity> getById(@PathVariable Long id) {
        return CommonResponse.success(partyMemberRepository.getById(id));
    }

    @PostMapping("getList")
    public CommonResponse<Page<PartyMemberEntity>> getList(@RequestBody PageParamPartyMemberVO pageParamPartyMemberVO) {
        int page = pageParamPartyMemberVO.getPage();
        int size = pageParamPartyMemberVO.getSize();
        String nickName = pageParamPartyMemberVO.getNickName();
        Page<PartyMemberEntity> partyMemberEntityPage;
        if (StringUtils.hasText(nickName)) {
            PartyMemberEntity partyMemberEntity = new PartyMemberEntity();
            partyMemberEntity.setNickName(nickName);
            partyMemberEntityPage = partyMemberRepository.findAll(Example.of(partyMemberEntity, ExampleMatcher.matching()
                    .withMatcher("nickName", ExampleMatcher.GenericPropertyMatcher::contains)),
                    PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
        } else {
            partyMemberEntityPage = partyMemberRepository.findAll(
                    PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
        }
        return CommonResponse.success(partyMemberEntityPage);
    }

}

package cn.cdcyy.partybuilding.management.controller;

import cn.cdcyy.partybuilding.common.CommonResponse;
import cn.cdcyy.partybuilding.common.pojo.vo.PageParamPartyMemberVO;
import cn.cdcyy.partybuilding.dao.entity.PointsEntity;
import cn.cdcyy.partybuilding.dao.repository.PointsRepository;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("points")
public class PointsController {

    private final PointsRepository pointsRepository;

    public PointsController(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    @GetMapping("{id}")
    public CommonResponse<PointsEntity> getOneById(@PathVariable Long id) {
        return CommonResponse.success(pointsRepository.getById(id));
    }

    @PostMapping
    public CommonResponse<String> saveOrUpdate(@RequestBody PointsEntity pointsEntity) {
        if (pointsEntity.getId() != null) {
            PointsEntity memberEntity = pointsRepository.getById(pointsEntity.getId());
            pointsEntity.setCreateTime(memberEntity.getCreateTime());
        }
        pointsEntity.setUpdateTime(new Date());
        pointsRepository.save(pointsEntity);
        return CommonResponse.success("成功");
    }

    @PostMapping("getList")
    public CommonResponse<Page<PointsEntity>> getList(@RequestBody PageParamPartyMemberVO pageParamPartyMemberVO) {
        int page = pageParamPartyMemberVO.getPage();
        int size = pageParamPartyMemberVO.getSize();
        String nickName = pageParamPartyMemberVO.getNickName();
        Page<PointsEntity> partyMemberEntityPage;
        if (StringUtils.hasText(nickName)) {
            PointsEntity partyMemberEntity = new PointsEntity();
            partyMemberEntity.setPartyMemberName(nickName);
            partyMemberEntityPage = pointsRepository.findAll(Example.of(partyMemberEntity, ExampleMatcher.matching()
                            .withMatcher("partyMemberName", ExampleMatcher.GenericPropertyMatcher::contains)),
                    PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
        } else {
            partyMemberEntityPage = pointsRepository.findAll(
                    PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime")));
        }
        return CommonResponse.success(partyMemberEntityPage);
    }
}

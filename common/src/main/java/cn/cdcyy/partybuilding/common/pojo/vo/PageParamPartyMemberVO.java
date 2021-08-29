package cn.cdcyy.partybuilding.common.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageParamPartyMemberVO extends PageParamVO {

    private String nickName;
}

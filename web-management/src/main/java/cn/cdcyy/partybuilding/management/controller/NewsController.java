package cn.cdcyy.partybuilding.management.controller;

import cn.cdcyy.partybuilding.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {

    @GetMapping("{newsId}")
    public CommonResponse<String> getOneNews(@PathVariable Long newsId) {
        return CommonResponse.success("hello");
    }
}

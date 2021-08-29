package cn.cdcyy.partybuilding.common;

import lombok.Getter;

@Getter
public enum CommonResponseEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "服务器错误"),
    PARAMETER_ERROR(400,"参数错误" ),
    UNAUTHORIZED(401,"未认证"),
    FORBIDDEN(403,"没有权限"),
    METHOD_NOT_ALLOWED(405, "方法不支持当前请求方式");


    CommonResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;

    private final String message;
}

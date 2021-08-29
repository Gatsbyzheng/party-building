package cn.cdcyy.partybuilding.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {

    private CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonResponse(CommonResponseEnum commonResponseEnum) {
        this.code = commonResponseEnum.getCode();
        this.message = commonResponseEnum.getMessage();
    }

    private final Integer code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(CommonResponseEnum.SUCCESS.getCode(),
                CommonResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResponse<T> fail() {
        return new CommonResponse<>(CommonResponseEnum.FAIL.getCode(),
                CommonResponseEnum.FAIL.getMessage());
    }
    public static <T> CommonResponse<T> fail(Integer code,String  message){
        return new CommonResponse<>(code,message);
    }

    public static <T> CommonResponse<T> parameterError(){
        return new CommonResponse<>(CommonResponseEnum.PARAMETER_ERROR);
    }
}

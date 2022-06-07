package icu.redamancy.common.utils.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.redamancy.common.utils.result.ResultCode;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author redamancy
 * @Date 2022/5/15 15:58
 * @Version 1.0
 */
@Data
public class ErrorResult implements Serializable {

    private Integer code;
    private String message;
    private boolean success = false;
    @JsonIgnore
    private ResultCode resultCode;

    public static ErrorResult error() {
        ErrorResult result = new ErrorResult();
        result.setResultCode(ResultCode.INTERNAL_SERVER_ERROR);
        return result;
    }

    public static ErrorResult error(String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.code());
        result.setMessage(message);
        return result;
    }

    public static ErrorResult error(Integer code, String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static ErrorResult error(ResultCode resultCode, String message) {
        ErrorResult result = new ErrorResult();
        result.setResultCode(resultCode);
        result.setMessage(message);
        return result;
    }
}

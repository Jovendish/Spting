package icu.redamancy.common.utils.result;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 6308315887056661996L;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<String, Object>();


    public Result setResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public Result setResult(ResultCode resultCode, Map<String,Object> data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.setData(data);
        return this;
    }
}

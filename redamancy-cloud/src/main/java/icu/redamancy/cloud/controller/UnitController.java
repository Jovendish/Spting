package icu.redamancy.cloud.controller;

import icu.redamancy.cloud.service.HouseNumberService;
import icu.redamancy.common.model.pojo.cloud.HouseNumber;
import icu.redamancy.common.model.pojo.cloud.Unit;
import icu.redamancy.common.utils.exceptionhandling.BaseException;
import icu.redamancy.common.utils.result.ResponseResult;
import icu.redamancy.common.utils.result.ResultCode;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author redamancy
 * @Date 2022/6/7 13:55
 * @Version 1.0
 */

@RestController
@ResponseResult
public class UnitController {

    @Resource
    private HouseNumberService houseNumberService;

    public Map<String, Object> addHouseNumber(@RequestParam(name = "unit") int unit, @RequestParam(name = "houseNumber") String houseNumber) {
        Boolean isOk = houseNumberService.addHouseNumber(new HouseNumber().setHousNumber(houseNumber), new Unit().setUnit(unit));
        Map<String, Object> map = new HashMap<>();

        if (isOk) {
            return map;
        } else {
            throw new BaseException(ResultCode.FEEDBACK.code(), "添加失败");
        }

    }
}

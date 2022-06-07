package icu.redamancy.cloud.controller;

import icu.redamancy.cloud.service.CloudService;
import icu.redamancy.common.model.bo.DeclareBo;
import icu.redamancy.common.model.pojo.cloud.EntityDeclare;
import icu.redamancy.common.utils.exceptionhandling.BaseException;
import icu.redamancy.common.utils.result.ResponseResult;
import icu.redamancy.common.utils.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author redamancy
 * @Date 2022/6/7 09:50
 * @Version 1.0
 */

@RestController
@ResponseResult
public class CloudController {

    @Autowired
    private CloudService cloudService;


    @PostMapping("cloud")
    public Map<String, Object> addCloud(EntityDeclare declare,
                                        @RequestParam(name = "file") MultipartFile[] file) {

        Boolean isOk = false;
        try {
            isOk = cloudService.addDeclare(declare, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isOk) {
            throw new BaseException(ResultCode.FEEDBACK.code(), "添加成功");
        } else {
            throw new BaseException(ResultCode.INTERNAL_SERVER_ERROR.code(), "失败");
        }
    }

    @GetMapping("declareHome")
    public Map<String, Object> declareHome() {
        return null;
    }

    @GetMapping("cloud")
    public Map<String, Object> getDeclare(@RequestParam(name = "current") Integer current,
                                          @RequestParam(name = "size") Integer size,
                                          @RequestParam(name = "updateTime") Long updateTime) {

        Map<String, Object> map = new HashMap<>();
        List<DeclareBo> declareBos = cloudService.getDeclare(current, size, updateTime);
        map.put("declareList", declareBos);

        return map;

    }
}

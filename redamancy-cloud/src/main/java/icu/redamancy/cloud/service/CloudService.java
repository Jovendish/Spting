package icu.redamancy.cloud.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.redamancy.common.feignclient.PictureClient;
import icu.redamancy.common.model.bo.DeclareBo;
import icu.redamancy.common.model.dao.auth.mapper.UserMapper;
import icu.redamancy.common.model.dao.cloud.DeclareDaoServiceImpl;
import icu.redamancy.common.model.dao.picture.service.PictureService;
import icu.redamancy.common.model.pojo.auth.EntityUser;
import icu.redamancy.common.model.pojo.cloud.EntityDeclare;
import icu.redamancy.common.model.pojo.picture.Picture;
import icu.redamancy.common.utils.jjwt.ParsingUserJwtInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author redamancy
 * @* @Date 2022/6/7 10:15
 * @Version 1.0
 */
@Service
public class CloudService {

    @Value("${spring.application.name}")
    private String objectName;

    @Resource
    private DeclareDaoServiceImpl declareDaoService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PictureClient pictureClient;

    @Resource
    private StringRedisTemplate stringRedisTemplatel;

    @Resource
    private PictureService pictureService;

    @Autowired
    private ObjectMapper objectMapper;

    public Boolean addDeclare(EntityDeclare entityDeclare, MultipartFile[] file) throws IOException {

        String userId = ParsingUserJwtInfo
                .GetParsingUserJwtInfo()
                .getUserId();

        List<Long> idList = null;
        idList = pictureClient.addPicture(objectName, userId, file);
        
        String idJson = objectMapper.writeValueAsString(idList);
        entityDeclare.setPictureList(idJson);

        userMapper.update(null, new UpdateWrapper<EntityUser>().eq("id", userId).set("name", entityDeclare.getName()));
        return declareDaoService.save(entityDeclare);

    }

    public void init() {
        List<EntityDeclare> declareList;
        List<DeclareBo> declareListBo;

        Page<EntityDeclare> page = declareDaoService.lambdaQuery().orderByDesc(EntityDeclare::getUpdateTime).page(new Page<>(1, 10));
        declareList = page.getRecords();
        declareListBo = declareList.stream().map(v -> {
            DeclareBo declareBo = new DeclareBo();
            BeanUtils.copyProperties(v, declareBo);
            if (!ObjectUtils.isEmpty(v.getPictureList()) && !v.getPictureList().equals("null")) {

                List<Long> id = JSON.parseArray(v.getPictureList(), Long.class);
                List<Picture> pictureList = pictureService.listByIds(id);
                declareBo.setPictureList(pictureList);
            }

            return declareBo;
        }).collect(Collectors.toList());

        String jsonList = null;
        try {
            jsonList = objectMapper.writeValueAsString(declareListBo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stringRedisTemplatel.opsForValue().set("cloud:declare:home:init", jsonList);
    }

    public List<DeclareBo> getDeclare(Integer current, Integer size, Long updateTime) {

        List<DeclareBo> declareBos;

        Page<EntityDeclare> page = declareDaoService
                .lambdaQuery()
                .orderByDesc(EntityDeclare::getUpdateTime)
                .lt(EntityDeclare::getUpdateTime, updateTime)
                .page(new Page<>(1, size));

        declareBos = page.getRecords().stream().map(v -> {
            DeclareBo declareBo = new DeclareBo();
            BeanUtils.copyProperties(v, declareBo);
            if (!ObjectUtils.isEmpty(v.getPictureList()) && !v.getPictureList().equals("null")) {
                List<Long> id = JSON.parseArray(v.getPictureList(), Long.class);
                List<Picture> pictureList = pictureService.listByIds(id);

                declareBo.setPictureList(pictureList);

            }
            return declareBo;

        }).collect(Collectors.toList());
        return declareBos;
    }
}

package icu.redamancy.common.model.dao.auth;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.redamancy.common.model.dao.auth.mapper.UserMapper;
import icu.redamancy.common.model.dao.auth.service.UserService;
import icu.redamancy.common.model.pojo.auth.EntityUser;

/**
 * @Author redamancy
 * @Date 2022/6/7 14:55
 * @Version 1.0
 */
public class UserDaoServiceImpl extends ServiceImpl<UserMapper, EntityUser> implements UserService {
}

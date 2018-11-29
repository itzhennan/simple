package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.UserService;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.ValidateUtils;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/7/15 23:08
 * @Description
 */
@Service
public class UserServiceImpl extends HibernateServiceSupport<User> implements UserService {
    @Override
    public User findByNameAndPassword(String username, String password) {
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("userName",username);
        map.put("password",password);
        List<User> datas = findDatas(map);
        return ValidateUtils.isNotEmpty(datas) ? datas.get(0) : null;
    }

    @Override
    public User findByName(String username) {
        List<User> datas = findDatas("userName", username);
        return ValidateUtils.isNotEmpty(datas) ? datas.get(0) : null;
    }
}

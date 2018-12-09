package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.base.entity.Role;
import cn.zznlin.simple.common.utils.genarate.GenarateServiceCodeUtil;

/**
 * @author zhennan zhang
 * @date 2018/12/7 14:18
 * @description
 */
public class SimpleUtils {

    // 生成Dao 和 Service
    public void genarateDaoAndService(Class clazz) throws Exception {
        // 生成Dao
//        GenarateDaoCodeUtil daoCodeUtil = new GenarateDaoCodeUtil(clazz);
//        daoCodeUtil.generateDaoFile();
//        daoCodeUtil.generateDaoImplFile();
        // 生成Service
        GenarateServiceCodeUtil serviceCodeUtil = new GenarateServiceCodeUtil(clazz);
        serviceCodeUtil.generateServiceFile();
        serviceCodeUtil.generateServiceImplFile();
    }

    public static void main(String[] args) throws Exception {
        new SimpleUtils().genarateDaoAndService(Role.class);
    }

}

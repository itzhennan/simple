package cn.zznlin.simple.admin.calendar.dao.impl;

import cn.zznlin.simple.admin.calendar.dao.CalendarDao;
import cn.zznlin.simple.admin.calendar.entity.CalendarInfo;
import cn.zznlin.simple.admin.calendar.pojo.CalendarBean;
import cn.zznlin.simple.admin.calendar.pojo.CalendarSourceCond;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import cn.zznlin.simple.common.utils.ReflectionUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:13
 * @description
 */
@Repository
public class CalendarDaoImpl extends HibernateDaoSupport<CalendarInfo> implements CalendarDao {

    @Override
    public List<CalendarBean> getEventBetweenStartAndEnd(CalendarSourceCond calendarSourceCond) {
        List<CalendarBean> list = Lists.newArrayList();
        List<Map<String, Object>> pageByNative = findPageByNative(calendarSourceCond.getEventBetweenStartAndEndSql());
        for (Map<String, Object> map:pageByNative) {
            CalendarBean calendarBean = ReflectionUtils.fillClassForMap(map, CalendarBean.class);
            list.add(calendarBean);
        }
        return list;
    }
}

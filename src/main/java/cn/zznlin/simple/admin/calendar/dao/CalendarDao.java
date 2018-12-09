package cn.zznlin.simple.admin.calendar.dao;

import cn.zznlin.simple.admin.calendar.entity.CalendarInfo;
import cn.zznlin.simple.admin.calendar.pojo.CalendarBean;
import cn.zznlin.simple.admin.calendar.pojo.CalendarSourceCond;
import cn.zznlin.simple.common.orm.dao.BaseDao;

import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:03
 * @description
 */
public interface CalendarDao extends BaseDao<CalendarInfo> {
    List<CalendarBean> getEventBetweenStartAndEnd(CalendarSourceCond calendarSourceCond);
}

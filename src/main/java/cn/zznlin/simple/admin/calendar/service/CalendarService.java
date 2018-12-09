package cn.zznlin.simple.admin.calendar.service;

import cn.zznlin.simple.admin.calendar.entity.CalendarInfo;
import cn.zznlin.simple.admin.calendar.pojo.CalendarBean;
import cn.zznlin.simple.admin.calendar.pojo.CalendarSourceCond;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.service.BaseService;

import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:14
 * @description
 */
public interface CalendarService extends BaseService<CalendarInfo> {
    Long saveOrUpdate(CalendarBean calendarBean, User currentUser);

    List<CalendarBean> getEventBetweenStartAndEnd(CalendarSourceCond calendarSourceCond);

    Long updateTitle(CalendarBean calendarBean, User currentUser);

    Long updateAllTime(CalendarBean calendarBean, User currentUser);

    Long updateEndTime(CalendarBean calendarBean, User currentUser);

    void deleteEvent(CalendarBean calendarBean, User currentUser);
}

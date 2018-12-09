package cn.zznlin.simple.admin.calendar.service.impl;

import cn.zznlin.simple.admin.calendar.dao.CalendarDao;
import cn.zznlin.simple.admin.calendar.entity.CalendarInfo;
import cn.zznlin.simple.admin.calendar.pojo.CalendarBean;
import cn.zznlin.simple.admin.calendar.pojo.CalendarSourceCond;
import cn.zznlin.simple.admin.calendar.service.CalendarService;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.DateUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import cn.zznlin.simple.common.utils.ValidateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:15
 * @description
 */
@Service
public class CalendarServiceImpl extends HibernateServiceSupport<CalendarInfo> implements CalendarService {

    // 获得当前service所属的DAO
    private CalendarDao getCurrentDao(){
        return (CalendarDao) baseDao;
    }

    /**
     * 保存 日历
     * @param calendarBean
     * @param currentUser
     */
    @Override
    public Long saveOrUpdate(CalendarBean calendarBean, User currentUser) {
        CalendarInfo  calendarInfo = null;
        if(ValidateUtils.isNotEmpty(calendarBean.getId()) && calendarBean.getId() > 0 ){
            calendarInfo = get(calendarBean.getId());
        }else{
            calendarInfo = new CalendarInfo();
            calendarInfo.setUser(currentUser);
        }
        calendarInfo.setTitle(calendarBean.getTitle());
        calendarInfo.setColor(calendarBean.getColor());
        if(calendarBean.getType() == 1){
            // 天
            calendarInfo.setEnd(DateUtils.str2Long(calendarBean.getEnd()));
            calendarInfo.setStart(DateUtils.str2Long(calendarBean.getStart()));
        }else{
            // 时间
            calendarInfo.setEnd(DateUtils.str2Long(calendarBean.getEnd(),DateUtils.PATTERN_DATETIME_ISO8601));
            calendarInfo.setStart(DateUtils.str2Long(calendarBean.getStart(),DateUtils.PATTERN_DATETIME_ISO8601));
        }
        calendarInfo.setUrl(calendarBean.getUrl());
        calendarInfo.setType(calendarBean.getType());

        saveOrUpdate(calendarInfo);
        return calendarInfo.getId();
    }


    /**
     * 更新 日历  标题
     * @param calendarBean
     * @param currentUser
     */
    @Override
    public Long updateTitle(CalendarBean calendarBean, User currentUser) {
        if(ValidateUtils.isNotEmpty(calendarBean.getId()) && calendarBean.getId() > 0 && StringUtils.isNoEmpty(calendarBean.getTitle()) ){
            CalendarInfo calendarInfo = get(calendarBean.getId());
            calendarInfo.setTitle(calendarBean.getTitle());
            update(calendarInfo);
            return calendarInfo.getId();
        }
        return null;
    }

    /**
     * 更新 日历  时间，整体拖动的时间
     * @param calendarBean
     * @param currentUser
     */
    @Override
    public Long updateAllTime(CalendarBean calendarBean, User currentUser) {
        if(ValidateUtils.isNotEmpty(calendarBean.getId()) && calendarBean.getId() > 0
                && StringUtils.isNoEmpty(calendarBean.getStart())
                && calendarBean.getTime() != 0){

            CalendarInfo calendarInfo = get(calendarBean.getId());

            // 封装开始时间和结束时间
            calendarInfo.setStart(calendarInfo.getStart() + calendarBean.getTime());
            if(calendarInfo.getEnd() != 0){
                calendarInfo.setEnd(calendarInfo.getEnd() + calendarBean.getTime());
            }else{
                // 整天变时间
                if(calendarBean.getType() == 0){
                    calendarInfo.setEnd(calendarInfo.getStart() + 7200000L);
                }
            }
            calendarInfo.setType(calendarBean.getType());

            update(calendarInfo);
            return calendarInfo.getId();
        }
        return null;
    }

    /**
     *  调整Event大小后，更改EndTime 时间
     * @param calendarBean  数据Pojo
     * @param currentUser  当前登录用户
     * @return  id         操作数据ID
     */
    @Override
    public Long updateEndTime(CalendarBean calendarBean, User currentUser) {
        if(ValidateUtils.isNotEmpty(calendarBean.getId()) && calendarBean.getId() > 0
                && StringUtils.isNoEmpty(calendarBean.getEnd())
                && calendarBean.getTime() != 0){

            CalendarInfo calendarInfo = get(calendarBean.getId());

            // 封装开始时间和结束时间
            if(calendarInfo.getEnd() != 0){
                calendarInfo.setEnd(calendarInfo.getEnd() + calendarBean.getTime());
            }else{
                // 结束时间 = 开始时间 + 俩个小时 + 跳转后的大小
                calendarInfo.setEnd(calendarInfo.getStart() + 7200000L + calendarBean.getTime() );
            }

            update(calendarInfo);
            return calendarInfo.getId();
        }
        return null;
    }

    /**
     * 删除某个Event
     * @param calendarBean
     * @param currentUser
     */
    @Override
    public void deleteEvent(CalendarBean calendarBean, User currentUser) {
        if(ValidateUtils.isNotEmpty(calendarBean.getId()) && calendarBean.getId() > 0){
            CalendarInfo calendarInfo = get(calendarBean.getId());
            calendarInfo.setIsDel(1);
            update(calendarInfo);
        }
    }

    /**
     * 获得 start 和 end 之间的 Event
     * @param calendarSourceCond
     */
    @Override
    public List<CalendarBean> getEventBetweenStartAndEnd(CalendarSourceCond calendarSourceCond) {
        String start = calendarSourceCond.getStart();
        String end = calendarSourceCond.getEnd();
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            return null;
        }
        if(start.indexOf("T") == -1){
            // 如果不存在T 说明是查询月份 2018-11-25
            start += "T00:00:00";
            end += "T00:00:00";
        }
        Long startLong = DateUtils.str2Long(start,DateUtils.PATTERN_DATETIME_ISO8601);
        Long endLong = DateUtils.str2Long(end,DateUtils.PATTERN_DATETIME_ISO8601);
        calendarSourceCond.setStartLong(startLong);
        calendarSourceCond.setEndLong(endLong);

        List<CalendarBean> list = getCurrentDao().getEventBetweenStartAndEnd(calendarSourceCond);

        return list;
    }

}

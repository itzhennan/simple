package cn.zznlin.simple.admin.calendar.pojo;

import cn.zznlin.simple.base.entity.User;

/**
 * @Author zhennan
 * @Date 2018/12/7 22:48
 * @Description
 *
 */
public class CalendarSourceCond {

    private User currentUser;

    private Long time;
    private String start;
    private String end;

    Long startLong = -99L;
    Long endLong = -99L;

    public String getEventBetweenStartAndEndSql() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT sc.id, sc.title, CASE WHEN sc.type = 1 THEN from_unixtime(sc.`start` / 1000, '%Y-%m-%d') ELSE from_unixtime( sc.`start` / 1000, '%Y-%m-%dT%H:%i:%s' ) END 'start', CASE WHEN sc.type = 1 THEN from_unixtime(sc.`end` / 1000, '%Y-%m-%d') ELSE from_unixtime( sc.`end` / 1000, '%Y-%m-%dT%H:%i:%s' ) END 'end', sc.url, sc.color ")
        .append(" FROM simple_calendar sc ")
        .append(" WHERE 1=1 ")
        .append(" AND sc.`start` >= ").append(startLong)
        .append(" AND sc.`end` < ").append(endLong)
        .append(" AND sc.is_del = 0 ")
        .append(" AND sc.user_id = ").append(currentUser.getUserId());
        return sb.toString();
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getStartLong() {
        return startLong;
    }

    public void setStartLong(Long startLong) {
        this.startLong = startLong;
    }

    public Long getEndLong() {
        return endLong;
    }

    public void setEndLong(Long endLong) {
        this.endLong = endLong;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}

package cn.zznlin.simple.admin.calendar.controller;

import cn.zznlin.simple.admin.calendar.pojo.CalendarBean;
import cn.zznlin.simple.admin.calendar.pojo.CalendarSourceCond;
import cn.zznlin.simple.admin.calendar.service.CalendarService;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.bean.ReturnJsonBean;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.helper.JSONHelper;
import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:19
 * @description
 *      日历类控制器
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController extends CommonController{

    @Autowired
    private CalendarService calendarService;

    /**
     *  保存日历中的某个Event
     * @param calendarBean
     * @param returnJsonBean
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonBean save(@RequestBody CalendarBean calendarBean, ReturnJsonBean returnJsonBean){
        try {
            User currentUser = getCurrentUser();
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarBean));
            Long id = calendarService.saveOrUpdate(calendarBean, currentUser);
            returnJsonBean.setMsg(StringUtils.getStr(id));
        }catch (Exception e){
            e.printStackTrace();
            returnJsonBean.setCode(2);
        }finally {
            return returnJsonBean;
        }
    }

    /**
     *  保存日历中的某个Event 的Title
     * @param calendarBean
     * @param returnJsonBean
     * @return
     */
    @RequestMapping(value = "/editTitle",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonBean editTitle(@RequestBody CalendarBean calendarBean, ReturnJsonBean returnJsonBean){
        try {
            User currentUser = getCurrentUser();
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarBean));
            calendarService.updateTitle(calendarBean,currentUser);
        }catch (Exception e){
            e.printStackTrace();
            returnJsonBean.setCode(2);
        }finally {
            return returnJsonBean;
        }
    }

    /**
     *  保存日历中的某个Event 的Time
     * @param calendarBean
     * @param returnJsonBean
     * @return
     */
    @RequestMapping(value = "/editAllTime",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonBean editAllTime(@RequestBody CalendarBean calendarBean, ReturnJsonBean returnJsonBean){
        try {
            User currentUser = getCurrentUser();
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarBean));
            calendarService.updateAllTime(calendarBean,currentUser);
        }catch (Exception e){
            e.printStackTrace();
            returnJsonBean.setCode(2);
        }finally {
            return returnJsonBean;
        }
    }

    /**
     *  跳转日历中的某个Event的大小后 改变 endTime
     * @param calendarBean
     * @param returnJsonBean
     * @return
     */
    @RequestMapping(value = "/editEndTime",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonBean editEndTime(@RequestBody CalendarBean calendarBean, ReturnJsonBean returnJsonBean){
        try {
            User currentUser = getCurrentUser();
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarBean));
            Long id = calendarService.updateEndTime(calendarBean,currentUser);
        }catch (Exception e){
            e.printStackTrace();
            returnJsonBean.setCode(2);
        }finally {
            return returnJsonBean;
        }
    }

    /**
     *  删除某个Event
     * @param calendarBean
     * @param returnJsonBean
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonBean delete(@RequestBody CalendarBean calendarBean, ReturnJsonBean returnJsonBean){
        try {
            User currentUser = getCurrentUser();
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarBean));
            calendarService.deleteEvent(calendarBean,currentUser);
        }catch (Exception e){
            e.printStackTrace();
            returnJsonBean.setCode(2);
        }finally {
            return returnJsonBean;
        }
    }


    /**
     * 获得 start 和 end 之间的 Event
     * @param calendarSourceCond
     * @return
     */
    @RequestMapping(value = "/source",method = RequestMethod.POST)
    @ResponseBody
    public List<CalendarBean> source(CalendarSourceCond calendarSourceCond){
        List<CalendarBean> list = Lists.newArrayList();
        LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(calendarSourceCond));
        calendarSourceCond.setCurrentUser(getCurrentUser());
        list =  calendarService.getEventBetweenStartAndEnd(calendarSourceCond);
        return list;
    }


    @Override
    protected String getModule() {
        return null;
    }

}

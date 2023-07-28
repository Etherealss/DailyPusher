package cn.seven.dailypusher.common.cron.service;

import cn.seven.dailypusher.common.cron.remote.XxlJobFeign;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobInfo;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobTaskIdParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XxlJobService {
    private final XxlJobCookieHolder xxlJobCookieHolder;
    private final XxlJobFeign xxlJobFeign;

    public Integer createJob(XxlJobInfo params) {
        return xxlJobFeign.insertJob(params, xxlJobCookieHolder.getCookie());
    }

    public void updateJob(XxlJobInfo params) {
        xxlJobFeign.updateJob(params, xxlJobCookieHolder.getCookie());
    }

    public void deleteJob(Integer taskId) {
        xxlJobFeign.deleteJob(this.build(taskId), xxlJobCookieHolder.getCookie());
    }

    public void runJob(Integer taskId) {
        xxlJobFeign.runJob(this.build(taskId), xxlJobCookieHolder.getCookie());
    }

    public void stopJob(Integer taskId) {
        xxlJobFeign.stopJob(this.build(taskId), xxlJobCookieHolder.getCookie());
    }

    private XxlJobTaskIdParam build(Integer taskId) {
        return new XxlJobTaskIdParam(taskId);
    }
}

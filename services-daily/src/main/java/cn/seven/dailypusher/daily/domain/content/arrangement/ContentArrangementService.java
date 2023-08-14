package cn.seven.dailypusher.daily.domain.content.arrangement;

import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
import cn.seven.dailypusher.motto.domain.service.MottoService;
import cn.seven.dailypusher.motto.infrastructure.client.MottoResponse;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.weather.domain.service.WeatherService;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherRequest;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 内容编排
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContentArrangementService {
    private final WeatherService weatherService;
    private final MottoService mottoService;

    public String arrangement(ContentResponse content, ProjectResponse project) {
        StringBuilder builder = new StringBuilder();
        builder.append(content.getBriefing()).append("\n");
        if (content.getContainWeather()) {
            appendWeather(content, builder);
        }
        if (content.getContainMotto()) {
            appendMotto(builder);
        }
        if (project != null) {
            appendProject(project, builder);
        }
        if (builder.lastIndexOf("\n") == builder.length() - 1) {
            builder.deleteCharAt(builder.length() - 2);
        }
        return builder.toString();
    }

    private void appendMotto(StringBuilder builder) {
        MottoResponse mottoResponse = mottoService.getMotto();
        builder.append(mottoResponse.buildReport()).append("\n");
    }

    private void appendWeather(ContentResponse content, StringBuilder builder) {
        WeatherRequest weatherRequest = new WeatherRequest()
                .setCity(content.getCityForWeather());
        WeatherResponse weatherResponse = weatherService.getWeather(weatherRequest);
        builder.append(weatherResponse.buildReport()).append("\n");
    }

    private void appendProject(ProjectResponse project, StringBuilder builder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        builder.append("项目名称：").append(project.getProjectName()).append("\n")
                .append("项目部门：").append(project.getProjectDepartment()).append("\n")
                .append("项目负责人：").append(project.getProjectLeader()).append("\n")
                .append("负责人手机号：").append(project.getPhone()).append("\n")
                .append("项目开始日期：").append(dateFormat.format(project.getStartDate())).append("\n")
                .append("项目截止日期：").append(dateFormat.format(project.getEndDate())).append("\n")
                .append("项目任务总数：").append(project.getTaskCount()).append("\n")
                .append("项目进度：").append(project.getProgress()).append("%\n")
                .append("已解决任务数：").append(project.getSolvedTaskCount()).append("\n")
                .append("交付达成率：").append(project.getDeliveryRate()).append("%\n")
                .append("需求总数：").append(project.getDemandCount()).append("\n")
                .append("需求解决数：").append(project.getSolvedDemandCount()).append("\n")
                .append("需求达成率：").append(project.getDemandRate()).append("%\n")
                .append("缺陷总数：").append(project.getBugCount()).append("\n")
                .append("缺陷解决数：").append(project.getSolvedBugCount()).append("\n")
                .append("缺陷达成率：").append(project.getBugRate()).append("%\n");
    }
}

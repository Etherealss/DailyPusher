package cn.seven.dailypusher.user.infrastructure.client.response;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectResponse {

    // 项目ID
    Long projectId;


    // 项目名称
    String projectName;


    // 项目部门
    String projectDepartment;

    // 项目负责人
    String projectLeader;

    // 负责人手机号
    String phone;

    // 项目开始日期
    String startDate;

    // 项目截止日期
    String endDate;

    // 项目状态 0-未开始 1-进行中 2-已结束
    Integer status;

    // 项目任务总数
    Integer taskCount;

    // 已解决任务数
    Integer solvedTaskCount;

    // 交付达成率 0-100 solutionCount/taskCount
    Double deliveryRate;

    // 需求总数
    Integer demandCount;

    // 需求解决数
    Integer solvedDemandCount;

    // 需求达成率 0-100 solvedDemandCount/demandCount
    Double demandRate;

    // 缺陷总数
    Integer bugCount;

    // 缺陷解决数
    Integer solvedBugCount;

    // 缺陷达成率 0-100 solvedBugCount/bugCount
    Double bugRate;

    //  项目进度 0-100 加权平均 progress = (deliveryRate * 0.5 + demandRate * 0.3 + bugRate * 0.2)
    Double progress;
}

package cn.seven.dailypusher.user.infrastructure.client.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectRequest {


    // 项目名称
    @NotBlank
    String projectName;


    // 项目部门
    @NotBlank
    String projectDepartment;

    // 项目负责人
    @NotBlank
    String projectLeader;

    // 项目开始日期
    @NotBlank
    Date startDate;

    // 项目截止日期
    @NotBlank
    Date endDate;

    // 项目状态 0-未开始 1-进行中 2-已结束
    @NotBlank
    Integer status;

    // 项目任务总数
    @NotBlank
    Integer taskCount;

    // 已解决任务数
    @NotBlank
    Integer solvedTaskCount;

    // 需求总数
    @NotBlank
    Integer demandCount;

    // 需求解决数
    @NotBlank
    Integer solvedDemandCount;

    // 缺陷总数
    @NotBlank
    Integer bugCount;

    // 缺陷解决数
    @NotBlank
    Integer solvedBugCount;
}

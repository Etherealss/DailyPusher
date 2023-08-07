package cn.seven.dailypusher.user.domain.project;

import cn.seven.dailypusher.common.base.enums.ProjectStatus;
import cn.seven.dailypusher.common.base.pojo.entity.IdentifiedEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * 项目实体类
 */
@TableName("project")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity extends IdentifiedEntity {


    // 项目名称
    @TableField(value = "project_name")
    String projectName;


    // 项目部门
    @TableField(value = "project_department")
    String projectDepartment;

    // 项目负责人
    @TableField(value = "project_leader")
    String projectLeader;

    @TableField(value = "phone")
    String phone;

    // 项目开始日期
    @TableField(value = "start_date")
    Date startDate;

    // 项目截止日期
    @TableField(value = "end_date")
    Date endDate;

    // 项目状态 0-未开始 1-进行中 2-已结束
    @TableField(value = "status")
    ProjectStatus status;

    // 项目任务总数
    @TableField(value = "task_count")
    Integer taskCount;

    // 已解决任务数
    @TableField(value = "solve_task_count")
    Integer solvedTaskCount;

    // 需求总数
    @TableField(value = "demand_count")
    Integer demandCount;

    // 需求解决数
    @TableField(value = "solve_demand_count")
    Integer solvedDemandCount;

    // 缺陷总数
    @TableField(value = "bug_count")
    Integer bugCount;

    // 缺陷解决数
    @TableField(value = "solve_bug_count")
    Integer solvedBugCount;
}

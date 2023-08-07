package cn.seven.dailypusher.user.infrastructure.convert.impl;

import cn.seven.dailypusher.user.domain.project.ProjectEntity;
import cn.seven.dailypusher.user.infrastructure.client.request.ProjectRequest;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.user.infrastructure.convert.IProjectConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ProjectConvertImpl implements IProjectConverter {
    @Override
    public ProjectEntity toEntity(ProjectRequest projectRequest) {
        if (projectRequest == null) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setProjectName(projectRequest.getProjectName());
        projectEntity.setProjectDepartment(projectRequest.getProjectDepartment());
        projectEntity.setProjectLeader(projectRequest.getProjectLeader());
        projectEntity.setStartDate(projectRequest.getStartDate());
        projectEntity.setEndDate(projectRequest.getEndDate());
        projectEntity.setStatus(projectRequest.getStatus());
        projectEntity.setTaskCount(projectRequest.getTaskCount());
        projectEntity.setSolvedTaskCount(projectRequest.getSolvedTaskCount());
        projectEntity.setDemandCount(projectRequest.getDemandCount());
        projectEntity.setSolvedDemandCount(projectRequest.getSolvedDemandCount());
        projectEntity.setBugCount(projectRequest.getBugCount());
        projectEntity.setSolvedBugCount(projectRequest.getSolvedBugCount());
        projectEntity.setPhone(projectRequest.getPhone());

        return projectEntity;
    }

    @Override
    public ProjectResponse toResponse(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setProjectId(entity.getId());
        projectResponse.setProjectName(entity.getProjectName());
        projectResponse.setProjectDepartment(entity.getProjectDepartment());
        projectResponse.setProjectLeader(entity.getProjectLeader());
        projectResponse.setStartDate(entity.getStartDate());
        projectResponse.setEndDate(entity.getEndDate());
        projectResponse.setStatus(entity.getStatus());
        projectResponse.setTaskCount(entity.getTaskCount());
        projectResponse.setSolvedTaskCount(entity.getSolvedTaskCount());
        projectResponse.setDemandCount(entity.getDemandCount());
        projectResponse.setSolvedDemandCount(entity.getSolvedDemandCount());
        projectResponse.setBugCount(entity.getBugCount());
        projectResponse.setSolvedBugCount(entity.getSolvedBugCount());
        projectResponse.setPhone(entity.getPhone());


        double deliveryRate = BigDecimal.valueOf(((double) projectResponse.getSolvedTaskCount() / projectResponse.getTaskCount()) * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        ;
        double demandRate = BigDecimal.valueOf(((double) projectResponse.getSolvedDemandCount() / projectResponse.getDemandCount()) * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double bugRate = BigDecimal.valueOf(((double) projectResponse.getSolvedBugCount() / projectResponse.getBugCount()) * 100).setScale(2, RoundingMode.HALF_UP).doubleValue();


        // 计算并设置交付达成率
        projectResponse.setDeliveryRate(deliveryRate);

        // 计算并设置需求达成率
        projectResponse.setDemandRate(demandRate);

        // 计算并设置缺陷达成率
        projectResponse.setBugRate(bugRate);

        // 计算并设置任务进度
        projectResponse.setProgress(0.5 * projectResponse.getDeliveryRate() + 0.3 * projectResponse.getDemandRate() + 0.2 * projectResponse.getBugRate());


        return projectResponse;
    }

}

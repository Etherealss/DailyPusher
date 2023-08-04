package cn.seven.dailypusher.user.infrastructure.convert.impl;

import cn.seven.dailypusher.user.domain.project.ProjectEntity;
import cn.seven.dailypusher.user.infrastructure.client.request.ProjectRequest;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.user.infrastructure.convert.IProjectConverter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ProjectConvertImpl implements IProjectConverter {
    @Override
    public ProjectEntity toEntity(ProjectRequest projectRequest) {
        if ( projectRequest == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setProjectName( projectRequest.getProjectName() );
        projectEntity.setProjectDepartment( projectRequest.getProjectDepartment() );
        projectEntity.setProjectLeader( projectRequest.getProjectLeader() );
        projectEntity.setStartDate( projectRequest.getStartDate() );
        projectEntity.setEndDate( projectRequest.getEndDate() );
        projectEntity.setStatus( projectRequest.getStatus() );
        projectEntity.setTaskCount( projectRequest.getTaskCount() );
        projectEntity.setSolvedTaskCount( projectRequest.getSolvedTaskCount() );
        projectEntity.setDemandCount( projectRequest.getDemandCount() );
        projectEntity.setSolvedDemandCount( projectRequest.getSolvedDemandCount() );
        projectEntity.setBugCount( projectRequest.getBugCount() );
        projectEntity.setSolvedBugCount( projectRequest.getSolvedBugCount() );
        projectEntity.setPhone( projectRequest.getPhone() );

        return projectEntity;
    }

    @Override
    public ProjectResponse toResponse(ProjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setProjectName( entity.getProjectName() );
        projectResponse.setProjectDepartment( entity.getProjectDepartment() );
        projectResponse.setProjectLeader( entity.getProjectLeader() );
        if ( entity.getStartDate() != null ) {
            projectResponse.setStartDate( new SimpleDateFormat().format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            projectResponse.setEndDate( new SimpleDateFormat().format( entity.getEndDate() ) );
        }
        projectResponse.setStatus( entity.getStatus() );
        projectResponse.setTaskCount( entity.getTaskCount() );
        projectResponse.setSolvedTaskCount( entity.getSolvedTaskCount() );
        projectResponse.setDemandCount( entity.getDemandCount() );
        projectResponse.setSolvedDemandCount( entity.getSolvedDemandCount() );
        projectResponse.setBugCount( entity.getBugCount() );
        projectResponse.setSolvedBugCount( entity.getSolvedBugCount() );
        projectResponse.setPhone( entity.getPhone() );

        // 计算并设置交付达成率
        projectResponse.setDeliveryRate(projectResponse.getSolvedTaskCount()/ projectResponse.getTaskCount());

        // 计算并设置需求达成率
        projectResponse.setDemandRate(projectResponse.getSolvedDemandCount()/ projectResponse.getDemandCount());

        // 计算并设置缺陷达成率
        projectResponse.setBugRate(projectResponse.getSolvedBugCount()/ projectResponse.getBugCount());

        // 计算并设置任务进度
        projectResponse.setProgress(0.5* projectResponse.getDeliveryRate()+0.3* projectResponse.getDemandRate()+0.2* projectResponse.getBugRate());



        return projectResponse;
    }

}

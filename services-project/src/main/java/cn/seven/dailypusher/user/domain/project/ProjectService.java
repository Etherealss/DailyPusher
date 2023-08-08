package cn.seven.dailypusher.user.domain.project;

import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.user.infrastructure.convert.IProjectConverter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService extends ServiceImpl<ProjectRepository, ProjectEntity> {

    private final IProjectConverter projectConverter;

    // 分页查询
    public PageDTO<ProjectResponse> page(int currentPage, int size) {
        log.info("分页查询 currentPage: {}, size: {}", currentPage, size);
        Page<ProjectEntity> page = lambdaQuery()
                .page(new Page<>(currentPage, size));
        List<ProjectResponse> collect = page.getRecords().stream()
                .map(projectConverter::toResponse)
                .collect(Collectors.toList());
        return new PageDTO<>(collect, page);
    }


    public ProjectResponse getById(Long id) {
        ProjectEntity contentEntity = requireEntity(id);
        return projectConverter.toResponse(contentEntity);
    }

    private ProjectEntity requireEntity(Long id) {
        return this.lambdaQuery()
                .eq(ProjectEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ProjectEntity.class, id.toString()));
    }

    public void checkNameDuplicate(ProjectEntity entity) {
        lambdaQuery()
                .eq(ProjectEntity::getProjectName, entity.getProjectName())
                .oneOpt()
                .ifPresent((e) -> {
                    throw new ParamErrorException("项目名已存在");
                });
    }

    public void checkNameDuplicate4Update(Long id, ProjectEntity entity) {
        ProjectEntity projectEntity = lambdaQuery()
                .eq(ProjectEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ProjectEntity.class, id.toString()));
        // 如果项目名没有改变，就不用检查了
        if (projectEntity.getProjectName().equals(entity.getProjectName())) {
            return;
        }
        // 否则检查新名字是否重名
        checkNameDuplicate(entity);
    }

    /**
     * 新建项目时要检验入参是否合法
     */

    public void checkCount(ProjectEntity entity) {
        // 已解决的数量<=总数量
        if (entity.getSolvedDemandCount() > entity.getDemandCount()
                || entity.getSolvedBugCount() > entity.getBugCount()
                || entity.getSolvedTaskCount() > entity.getTaskCount()
                || entity.getStartDate().after(entity.getEndDate())) {
            throw new ParamErrorException();
        }
    }

    // 开启事务
    @Transactional(rollbackFor = Exception.class)
    public long create(ProjectEntity entity) {
        checkNameDuplicate(entity);
        checkCount(entity);
        this.save(entity);
        return entity.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, ProjectEntity entity) {
        checkNameDuplicate4Update(id, entity);
        checkCount(entity);
        entity.setId(id);
        this.lambdaUpdate()
                .eq(ProjectEntity::getId, id)
                .update(entity);
    }

    public String getPhone(Long id) {
        ProjectEntity projectEntity = requireEntity(id);
        return projectEntity.getPhone();
    }
}

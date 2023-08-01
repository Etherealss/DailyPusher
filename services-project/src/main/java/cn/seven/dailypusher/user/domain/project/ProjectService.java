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
        ProjectEntity contentEntity = this.lambdaQuery()
                .eq(ProjectEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ProjectEntity.class, id.toString()));

        return projectConverter.toResponse(contentEntity);
    }

    /**
     * 新建项目时要检验入参是否合法
     */
    @Override
    public boolean save(ProjectEntity entity) {
        // 检查项目名是否唯一
        if (lambdaQuery().eq(ProjectEntity::getProjectName, entity.getProjectName()).oneOpt().isPresent()) {
            throw new ParamErrorException("项目名已存在");
        }

        // 已解决的数量<=总数量
       if (entity.getSolvedDemandCount()>entity.getDemandCount() || entity.getSolvedBugCount()> entity.getBugCount() || entity.getSolvedTaskCount()> entity.getTaskCount()) {
               throw new ParamErrorException();
       }
        return super.save(entity);
    }



}

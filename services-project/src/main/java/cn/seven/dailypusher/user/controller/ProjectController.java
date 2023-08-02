package cn.seven.dailypusher.user.controller;

import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.common.base.pojo.dto.Result;
import cn.seven.dailypusher.common.base.web.GlobalExceptionHandler;
import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.user.domain.project.ProjectService;
import cn.seven.dailypusher.user.infrastructure.client.request.ProjectRequest;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.user.infrastructure.convert.IProjectConverter;
import cn.seven.dailypusher.user.domain.project.ProjectEntity;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@ResponseAdvice
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IProjectConverter projectConverter;

    @PostMapping("/projects")
    public Long addProject(@RequestBody @Validated ProjectRequest projectRequest) {
        ProjectEntity projectEntity = projectConverter.toEntity(projectRequest);
        projectService.check(projectEntity);
        projectService.save(projectEntity);
        return projectEntity.getId();

    }

    @PutMapping("/projects/{id}")
    public void updateProject(@PathVariable Long id,@RequestBody @Validated ProjectRequest projectRequest) {
        ProjectEntity projectEntity = projectConverter.toEntity(projectRequest);
        projectService.check(projectEntity);
        projectEntity.setId(id);
        log.info("projectEntity: {}", projectEntity);
        projectService.updateById(projectEntity);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.removeById(id);
    }

    @GetMapping("/projects/{id}")
    public ProjectResponse getProject(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @GetMapping("/pages/projects")
    public PageDTO<ProjectResponse> page(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "current", defaultValue = "1") int currentPage) {
        return projectService.page(currentPage, size);
    }



}

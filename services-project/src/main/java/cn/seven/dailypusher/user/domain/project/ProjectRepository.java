package cn.seven.dailypusher.user.domain.project;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectRepository extends BaseMapper<ProjectEntity> {
}

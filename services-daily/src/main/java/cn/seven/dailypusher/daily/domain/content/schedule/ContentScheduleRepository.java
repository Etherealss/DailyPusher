package cn.seven.dailypusher.daily.domain.content.schedule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Mapper
@Repository
public interface ContentScheduleRepository extends BaseMapper<ContentScheduleEntity> {
}

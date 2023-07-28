package cn.seven.dailypusher.daily.domain.briefing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Mapper
@Repository
public interface BriefingRepository extends BaseMapper<BriefingEntity> {
}

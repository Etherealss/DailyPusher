package cn.seven.dailypusher.user.mapper;

import cn.seven.dailypusher.user.infrastructure.pojo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wtk
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}

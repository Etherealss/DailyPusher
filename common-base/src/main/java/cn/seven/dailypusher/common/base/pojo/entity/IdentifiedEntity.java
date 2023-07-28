package cn.seven.dailypusher.common.base.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 带ID的基础实体类
 * @author wtk
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
public abstract class IdentifiedEntity extends BaseEntity {
    /**
     * 雪花算法ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected Long id;
}

package cn.seven.dailypusher.user.infrastructure.pojo.entity;

import cn.seven.dailypusher.common.base.pojo.entity.IdentifiedEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author wtk
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends IdentifiedEntity {

    @TableField(value = "username")
    String username;

    @TableField(value = "password")
    String password;
}
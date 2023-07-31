package cn.seven.dailypusher.user.infrastructure.pojo.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author wtk
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBriefDTO {
    Long id;

    String username;

    Date createTime;
}

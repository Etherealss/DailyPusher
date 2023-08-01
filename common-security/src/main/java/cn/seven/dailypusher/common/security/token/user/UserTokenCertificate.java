package cn.seven.dailypusher.common.security.token.user;

import cn.seven.dailypusher.common.security.token.TokenCertificate;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author wtk
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTokenCertificate extends TokenCertificate {

    Long userId;

    String username;
}

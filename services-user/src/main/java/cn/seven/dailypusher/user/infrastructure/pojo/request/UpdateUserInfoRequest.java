package cn.seven.dailypusher.user.infrastructure.pojo.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author wtk
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserInfoRequest {
    @Pattern(regexp = "^[a-fA-F0-9]{64}$", message = "密码应该加密，是一个具有64位的十六进制字符串")
    @NotBlank
    String curPassword;

    @Pattern(regexp = "^[a-fA-F0-9]{64}$", message = "密码应该加密，是一个具有64位的十六进制字符串")
    @NotBlank
    String newPassword;
}

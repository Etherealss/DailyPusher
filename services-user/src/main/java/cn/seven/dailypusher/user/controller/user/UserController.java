package cn.seven.dailypusher.user.controller.user;

import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.common.security.auth.annotation.AnonymousAccess;
import cn.seven.dailypusher.common.security.token.user.UserSecurityContextHolder;
import cn.seven.dailypusher.user.infrastructure.pojo.dto.UserBriefDTO;
import cn.seven.dailypusher.user.infrastructure.pojo.request.CreateUserRequest;
import cn.seven.dailypusher.user.infrastructure.pojo.request.UpdateUserInfoRequest;
import cn.seven.dailypusher.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.List;

/**
 * @author wtk
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@ResponseAdvice
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    @AnonymousAccess
    public Long register(@RequestBody @Validated CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/users/usernames/{username}")
    @AnonymousAccess
    public Boolean usernameExist(@PathVariable @Pattern(
            regexp = "^[a-zA-Z0-9_-]{3,16}$",
            message = "用户名只能包含这些字符“a-zA-Z0-9_-”，长度为3-16"
    ) String username) {
        log.trace("查看用户名是否已存在：{}", username);
        return userService.checkUsernameExists(username);
    }

    @GetMapping("/users/ids/{userId}")
    @AnonymousAccess
    public UserBriefDTO findById(@PathVariable Long userId) {
        return userService.getBriefById(userId);
    }

    @GetMapping("/users")
    public UserBriefDTO findCurUser() {
        Long userId = UserSecurityContextHolder.require().getUserId();
        return userService.getBriefById(userId);
    }

    @GetMapping("/list/users/ids")
    @AnonymousAccess
    public List<UserBriefDTO> findBatchById(@RequestParam(value = "ids", defaultValue = "") List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return userService.getBatchBriefsByIds(ids);
    }

    @PutMapping("/users")
    public void updateById(@RequestBody @Validated UpdateUserInfoRequest req) {
        userService.update(UserSecurityContextHolder.require().getUserId(), req);
    }
}

package cn.seven.dailypusher.user.controller.user;

import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.common.security.auth.annotation.AnonymousAccess;
import cn.seven.dailypusher.common.security.token.user.UserTokenCertificate;
import cn.seven.dailypusher.user.infrastructure.pojo.request.UserLoginRequest;
import cn.seven.dailypusher.user.service.user.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wtk
 */
@Slf4j
@RestController
@RequestMapping("/auth/users/token")
@RequiredArgsConstructor
@ResponseAdvice
public class UserTokenController {

    private final UserAuthService userAuthService;

    @PostMapping
    @AnonymousAccess
    public UserTokenCertificate login(@RequestBody @Validated UserLoginRequest req) {
        return userAuthService.login(req);
    }

    @GetMapping("/{token}")
    @AnonymousAccess
    public Boolean verifyToken(@PathVariable String token) {
        return userAuthService.checkLogin(token);
    }

    @DeleteMapping("/{token}")
    public void logout(@PathVariable String token) {
        userAuthService.logout(token);
    }
}

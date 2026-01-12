package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ruoyi.common.core.domain.model.GoogleBindBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.google.GoogleAuthenticator;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Value("${ruoyi.name}")
    private String projectName;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        try {
            // 生成令牌
            LoginUser loginUser = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                    loginBody.getUuid(),loginBody.getGoogleCode());
            //验证通过，如果用户没有绑定谷歌则返回谷歌信息提供给用户绑定，不返回token，需要用户拿到谷歌信息强制绑定
            ajax.put(Constants.TOKEN, loginUser.getToken());
        } catch (CustomException e) {
            String message = e.getMessage();
            if (message.startsWith("GOOGLE_CODE_REQUIRED:")) {
               return AjaxResult.error("验证码不能为空");
            } else if (message.startsWith("GOOGLE_BIND_REQUIRED:")) {
                String[] parts = message.split(":");
                if (parts.length >= 3) {
                    String username = parts[1];
                    String googleSecret = parts[2];
                    ajax.put("needGoogleBind", true);
                    ajax.put("googleSecret", googleSecret);
                    ajax.put("googleCode", "otpauth://totp/" + projectName + "@" + username + "?secret=" + googleSecret);
                    return ajax;
                }
            }
            // 其他自定义异常则抛出
            throw e;
        }
        return ajax;
    }

    @PostMapping("/sys/google/bind")
    public AjaxResult googleBind(@Validated @RequestBody GoogleBindBody body)
    {
        AjaxResult ajax = AjaxResult.success();
        String secret = body.getSecret();
        String username = body.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        String googleCode = user.getGoogleCode();
        if (googleCode == null || !StringUtils.equals(googleCode, body.getSecret())) {
            return AjaxResult.error("密钥匹配失败");
        }

        String expectedCode = GoogleAuthenticator.getTOTPCode(user.getGoogleCode());
        //验证谷歌验证码是否正确
        if (!StringUtils.equals(expectedCode, body.getCode())) {
            return AjaxResult.error("谷歌验证码错误");
        }
        //绑定谷歌验证码成功
        boolean result  = userService.bindGoogle(user);
        return result?AjaxResult.success("绑定成功"):AjaxResult.error("绑定失败");
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}

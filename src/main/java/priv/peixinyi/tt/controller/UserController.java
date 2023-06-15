package priv.peixinyi.tt.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import priv.peixinyi.tt.entity.User;
import priv.peixinyi.tt.exception.IRException;
import priv.peixinyi.tt.pojo.Result;
import priv.peixinyi.tt.pojo.request.*;
import priv.peixinyi.tt.service.TravelRecordService;
import priv.peixinyi.tt.service.UserService;
import priv.peixinyi.tt.utils.PasswordUtils;

/**
 * 用户控制层
 *
 * @author peixinyi
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    TravelRecordService travelRecordService;

    /**
     * 登录
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>
     * @author peixinyi
     * @since 10:06 2023/6/15
     */
    @PostMapping("login")
    public Result<User> login(@RequestBody UserLoginRequest request) {
        // 校验参数
        if (StringUtils.isEmpty(request.getName())) {
            throw new IRException("用户名不能为空");
        }

        if (StringUtils.isEmpty(request.getPassword())) {
            throw new IRException("密码不能为空");
        }
        User user = userService.getUserByUsername(request.getName());
        if (ObjectUtils.isEmpty(user)) {
            throw new IRException("用户不存在");
        }
        String encrypt = PasswordUtils.encrypt(request.getPassword(), user.getSalt());
        if (!encrypt.equals(user.getPassword())) {
            throw new IRException("密码错误");
        }
        StpUtil.login(user.getId());
        user.setSaTokenInfo(StpUtil.getTokenInfo());
        return Result.success(user);
    }

    /**
     * 注册
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>
     * @author peixinyi
     * @since 10:07 2023/6/15
     */
    @PostMapping("register")
    public Result<User> register(@RequestBody UserRegisterRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userService.addUser(user);
        return Result.success(null);
    }

    /**
     * 修改用户
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>
     * @author peixinyi
     * @since 23:04 2023/6/14
     */
    @PatchMapping
    public Result<User> updateUser(@RequestBody UserUpdateRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user = userService.updateUser(user);
        return Result.success(user);
    }

    /**
     * 删除用户
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>
     * @author peixinyi
     * @since 23:06 2023/6/14
     */
    @DeleteMapping
    public Result<String> logoff(@RequestBody UserLogoffRequest request) {
        if (!userService.checkPassword(StpUtil.getLoginIdAsInt(), request.getPassword())) {
            return Result.error("密码错误");
        }
        userService.removeById(StpUtil.getLoginIdAsInt());
        travelRecordService.removeByUserId(StpUtil.getLoginIdAsInt());
        return Result.success();
    }

    /**
     * 刷新用户的OpenId
     *
     * @return priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>
     * @author peixinyi
     * @since 23:08 2023/6/14
     */
    @PutMapping("/refreshOpenId")
    public Result<User> refreshOpenId() {
        userService.refreshOpenId(StpUtil.getLoginIdAsInt());
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return Result.ok(user);
    }

}

package priv.peixinyi.tt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import priv.peixinyi.tt.context.TTContext;
import priv.peixinyi.tt.pojo.PageRequest;
import priv.peixinyi.tt.pojo.enums.SubState;
import priv.peixinyi.tt.entity.SubscriptionInform;
import priv.peixinyi.tt.entity.User;
import priv.peixinyi.tt.pojo.Result;
import priv.peixinyi.tt.pojo.request.SubPageRequest;
import priv.peixinyi.tt.pojo.response.SubResponse;
import priv.peixinyi.tt.service.SubscriptionInformService;
import priv.peixinyi.tt.service.UserService;

/**
 * 订阅服务
 *
 * @author peixinyi
 */
@RestController
@RequestMapping("/sub")
public class SubController {

    @Resource
    UserService userService;

    @Resource
    SubscriptionInformService subscriptionInformService;

    /**
     * 申请订阅
     *
     * @param userId
     * @return priv.peixinyi.tt.pojo.Result<java.lang.String>
     * @author peixinyi
     * @since 22:30 2023/7/24
     */
    @PostMapping("/{userId}")
    public Result<String> applySub(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return Result.error("目标用户不存在...");
        }

        LambdaQueryWrapper<SubscriptionInform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubscriptionInform::getUserId, TTContext.getUserId());
        queryWrapper.eq(SubscriptionInform::getSubId, userId);
        SubscriptionInform one = subscriptionInformService.getOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(one)) {
            return Result.error("已经订阅过了...");
        }
        SubscriptionInform subscriptionInform = new SubscriptionInform();
        subscriptionInform.setUserId(TTContext.getUserId());
        subscriptionInform.setSubId(userId);
        subscriptionInform.setStatus(SubState.APPLY_FOR.getCode());
        subscriptionInformService.save(subscriptionInform);
        return Result.success("订阅申请成功...");
    }

    /**
     * 取消订阅
     *
     * @param userId
     * @return priv.peixinyi.tt.pojo.Result<java.lang.String>
     * @author peixinyi
     * @since 22:38 2023/7/24
     */
    @DeleteMapping("/{userId}")
    public Result<String> cancelSub(@PathVariable Integer userId) {
        LambdaQueryWrapper<SubscriptionInform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubscriptionInform::getUserId, TTContext.getUserId());
        queryWrapper.eq(SubscriptionInform::getSubId, userId);
        SubscriptionInform one = subscriptionInformService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return Result.error("订阅信息不存在...");
        }
        subscriptionInformService.remove(queryWrapper);
        return Result.success("取消订阅成功...");
    }


    /**
     * 获取我订阅的列表
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page < priv.peixinyi.tt.pojo.response.SubResponse>>
     * @author peixinyi
     * @since 22:42 2023/7/24
     */
    @PostMapping("/my/page")
    public Result<Page<SubResponse>> getMySubList(@RequestBody PageRequest<SubPageRequest> request) {
        Page<SubResponse> page = new Page<>(request.getCurrentPage(), request.getMaxLine());
        Page<SubResponse> subResponsePage = subscriptionInformService.getBaseMapper().selectMySubPage(page, TTContext.getUserId());
        subResponsePage.getRecords().forEach(subResponse -> {
            subResponse.setStatusDesc(SubState.getDesc(subResponse.getStatus()));
        });
        return Result.ok(subResponsePage);
    }

    /**
     * 获取订阅我的列表
     *
     * @param request
     * @return priv.peixinyi.tt.pojo.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page < priv.peixinyi.tt.pojo.response.SubResponse>>
     * @author peixinyi
     * @since 22:53 2023/7/24
     */
    @PostMapping("/me/page")
    public Result<Page<SubResponse>> getSubToMe(@RequestBody PageRequest<SubPageRequest> request) {
        Page<SubResponse> page = new Page<>(request.getCurrentPage(), request.getMaxLine());
        Page<SubResponse> subResponsePage = subscriptionInformService.getBaseMapper().selectSubMePage(page, TTContext.getUserId());
        subResponsePage.getRecords().forEach(subResponse -> {
            subResponse.setStatusDesc(SubState.getDesc(subResponse.getStatus()));
        });
        return Result.ok(subResponsePage);
    }


    /**
     * 同意订阅
     *
     * @param subId
     * @return priv.peixinyi.tt.pojo.Result<java.lang.String>
     * @author peixinyi
     * @since 22:55 2023/7/24
     */
    @PostMapping("/agree/{id}")
    public Result<String> agreeSub(@PathVariable Integer id) {
        LambdaQueryWrapper<SubscriptionInform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubscriptionInform::getId, id);
        queryWrapper.eq(SubscriptionInform::getSubId, TTContext.getUserId());
        SubscriptionInform one = subscriptionInformService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return Result.error("订阅信息不存在...");
        }
        one.setStatus(SubState.AGREE.getCode());
        subscriptionInformService.updateById(one);
        return Result.success("同意订阅成功...");
    }

    /**
     * 拒绝订阅
     *
     * @param subId
     * @return priv.peixinyi.tt.pojo.Result<java.lang.String>
     * @author peixinyi
     * @since 22:57 2023/7/24
     */
    @PostMapping("/refuse/{subId}")
    public Result<String> refuseSub(@PathVariable Integer subId) {
        LambdaQueryWrapper<SubscriptionInform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubscriptionInform::getUserId, TTContext.getUserId());
        queryWrapper.eq(SubscriptionInform::getSubId, subId);
        SubscriptionInform one = subscriptionInformService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return Result.error("订阅信息不存在...");
        }
        one.setStatus(SubState.REFUSE.getCode());
        subscriptionInformService.updateById(one);
        return Result.success("拒绝订阅成功...");
    }

}

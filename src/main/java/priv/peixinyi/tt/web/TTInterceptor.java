package priv.peixinyi.tt.web;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import priv.peixinyi.tt.context.TTContext;
import priv.peixinyi.tt.entity.User;
import priv.peixinyi.tt.service.UserService;

/**
 * @author peixinyi
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class TTInterceptor implements WebMvcConfigurer {

    UserService userService;

    private static final String OPEN_ID = "OPEN_ID";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String openId = getOpenId(request);
                if (ObjectUtils.isNotEmpty(openId)) {
                    User userByOpenId = userService.getUserByOpenId(openId);
                    if (ObjectUtils.isNotEmpty(userByOpenId)) {
                        TTContext.setUserId(userByOpenId.getId());
                        TTContext.setOpenId(userByOpenId.getOpenId());
                    }
                }
                return true;
            }
        }).addPathPatterns("/**");
        registry.addInterceptor(new SaInterceptor(
                        handle -> {
                            if (ObjectUtils.isEmpty(TTContext.getOpenId())) {
                                StpUtil.checkLogin();
                            }
                        }
                )).addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/travelRecord/drive");
    }

    public String getOpenId(HttpServletRequest request) {
        String openId = request.getHeader(OPEN_ID);
        if (ObjectUtils.isEmpty(openId)) {
            openId = request.getParameter(OPEN_ID);
        }
        return ObjectUtils.isEmpty(openId) ? null : openId;
    }

}

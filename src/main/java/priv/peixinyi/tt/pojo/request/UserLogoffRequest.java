package priv.peixinyi.tt.pojo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注销请求
 *
 * @author peixinyi
 */
@Data
@NoArgsConstructor
public class UserLogoffRequest {

    /**
     * 密码
     */
    private String password;

}

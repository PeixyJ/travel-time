package priv.peixinyi.tt.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author peixinyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

}

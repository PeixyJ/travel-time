package priv.peixinyi.tt.pojo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订阅返回信息
 *
 * @author peixinyi
 */
@Data
@NoArgsConstructor
public class SubResponse {

    /**
     * 当前用户ID
     */
    private Integer userId;

    /**
     * 当前用户昵称
     */
    private String nickname;

    /**
     * 订阅用户ID
     */
    private Integer subId;

    /**
     * 订阅用户昵称
     */
    private String subNickname;

    /**
     * 订阅状态
     */
    private Integer status;

    /**
     * 订阅状态描述
     */
    private String statusDesc;

}

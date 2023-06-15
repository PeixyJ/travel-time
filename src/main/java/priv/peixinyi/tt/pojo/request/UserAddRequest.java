package priv.peixinyi.tt.pojo.request;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户新增请求
 *
 * @author peixinyi
 */
@Data
@NoArgsConstructor
public class UserAddRequest {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 家里的经纬度
     */
    private String homeLat;

    /**
     * 公司经纬度
     */
    private String companyLat;

    /**
     * Bark通知ID
     */
    private String barkId;

}

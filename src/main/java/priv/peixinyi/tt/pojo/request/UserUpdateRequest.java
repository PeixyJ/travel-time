package priv.peixinyi.tt.pojo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户修改请求
 *
 * @author peixinyi
 */
@Data
@NoArgsConstructor
public class UserUpdateRequest {

    private Integer id;

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

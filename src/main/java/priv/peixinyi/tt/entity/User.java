package priv.peixinyi.tt.entity;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${description}
 *
 * @author peixinyi
 */

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @JsonIgnore
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    @JsonIgnore
    private String salt;

    /**
     * OpenId
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 家里的经纬度
     */
    @TableField(value = "home_lat")
    private String homeLat;

    /**
     * 公司经纬度
     */
    @TableField(value = "company_lat")
    private String companyLat;

    /**
     * Bark通知ID
     */
    @TableField(value = "bark_id")
    private String barkId;

    @TableField(exist = false)
    private SaTokenInfo saTokenInfo;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_PASSWORD = "password";

    public static final String COL_SALT = "salt";

    public static final String COL_OPEN_ID = "open_id";

    public static final String COL_HOME_LAT = "home_lat";

    public static final String COL_COMPANY_LAT = "company_lat";

    public static final String COL_BARK_ID = "bark_id";
}
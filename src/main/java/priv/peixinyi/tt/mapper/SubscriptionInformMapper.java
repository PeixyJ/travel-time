package priv.peixinyi.tt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import priv.peixinyi.tt.entity.SubscriptionInform;
import priv.peixinyi.tt.pojo.response.SubResponse;

/**
 * ${description}
 *
 * @author peixinyi
 */
public interface SubscriptionInformMapper extends BaseMapper<SubscriptionInform> {

    public Page<SubResponse> selectMySubPage(Page<SubResponse> page, @Param("userId") Integer userId);

    public Page<SubResponse> selectSubMePage(Page<SubResponse> page, @Param("userId") Integer userId);

}
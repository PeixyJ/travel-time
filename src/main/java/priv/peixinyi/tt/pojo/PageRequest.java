package priv.peixinyi.tt.pojo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页请求
 *
 * @author peixinyi
 */
@Data
public class PageRequest<T> {

    /**
     * 当前页
     */
    private Integer currentPage = 1;


    /**
     * 每页显示
     */
    private Integer maxLine = 10;

    /**
     * 请求条件
     */
    private T query;

    /**
     * 排序字段
     */
    private List<OrderItem> sort;


    public Integer getCurrentPage() {
        if (ObjectUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        return currentPage;
    }

    public Integer getMaxLine() {
        if (ObjectUtils.isEmpty(maxLine)) {
            maxLine = 10;
        }
        if (maxLine <= 0) {
            maxLine = 10;
        }
        if (maxLine > 100) {
            maxLine = 100;
        }
        return maxLine;
    }

    public T getQuery() {
        if (ObjectUtils.isEmpty(query)) {
            return (T) new Object();
        }
        return query;
    }

    /**
     * 获取排序
     *
     * @return java.util.List<com.baomidou.mybatisplus.core.metadata.OrderItem>
     * @author peixinyi
     * @since 15:14 2023/5/15
     */
    public List<OrderItem> getSort() {
        if (ObjectUtils.isEmpty(sort)) {
            return new ArrayList<>();
        }
        checkSortExist();
        return sort;
    }

    /**
     * 检查排序字段是否存在
     *
     * @return void
     * @author peixinyi
     * @since 16:24 2023/4/22
     */
    public void checkSortExist() {
        for (Field field : this.query.getClass().getDeclaredFields()) {
            if (sort.isEmpty()) {
                return;
            }
            for (OrderItem s : sort) {
                if (field.getName().equals(s.getColumn())) {
                    return;
                }
            }
        }
        throw new RuntimeException("sort field is not exist");
    }

    public PageRequest() {
    }

}

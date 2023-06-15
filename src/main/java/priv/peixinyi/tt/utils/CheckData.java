package priv.peixinyi.tt.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.util.StringUtils;
import priv.peixinyi.tt.exception.IRException;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * 检查数据
 *
 * @author peixinyi
 */
public interface CheckData<T> {


    /**
     * 检查数据是否重复
     *
     * @param field         字段
     * @param object        对象
     * @param errorMessage 异常枚举
     * @return void
     * @author peixinyi
     * @since 13:30 2023/5/16
     */
    default void checkDataRepeat(String field, Object object, String errorMessage) {
        QueryWrapper<T> query = new QueryWrapper<>();
        query.eq(field, object);
        checkDataRepeat(query, errorMessage, object);
    }

    /**
     * 检查数据是否重复
     *
     * @param queryWrapper
     * @param object
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 11:36 2023/5/16
     */
    default void checkDataRepeat(QueryWrapper<T> queryWrapper, String errorMessage, Object... object) {
        for (int i = 0; i < object.length; i++) {
            if (ObjectUtils.isEmpty(object[i])) {
                return;
            }
        }
        queryWrapper.last("limit 1");
        long count = iCount(queryWrapper);
        if (count > 0) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * 检查数据是否重复
     *
     * @param excludeId
     * @param field
     * @param object
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 11:48 2023/5/16
     */
    default void checkDataRepeat(Integer excludeId, String field, Object object, String errorMessage) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(field, object);
        queryWrapper.ne("id", excludeId);
        checkDataRepeat(excludeId, queryWrapper, errorMessage, object);
    }

    /**
     * 检查数据是否重复
     *
     * @param excludeId
     * @param queryWrapper
     * @param object
     * @param errMessage
     * @return void
     * @author peixinyi
     * @since 11:48 2023/5/16
     */
    default void checkDataRepeat(Integer excludeId, QueryWrapper<T> queryWrapper, String errMessage, Object... object) {
        for (int i = 0; i < object.length; i++) {
            if (ObjectUtils.isEmpty(object[i])) {
                return;
            }
        }
        queryWrapper.ne("id", excludeId);
        queryWrapper.last("limit 1");
        long count = iCount(queryWrapper);
        if (count > 0) {
            throw new IRException(errMessage);
        }
    }


    /**
     * 检查数据是否重复
     *
     * @param filed
     * @param obj
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:02 2023/5/17
     */
    default void checkExistAllowNullTrueException(String filed, Object obj, String errorMessage) {
        if (StringUtils.isEmpty(filed) || ObjectUtils.isEmpty(obj)) {
            return;
        }
        checkExistTrueException(filed, obj, errorMessage);
    }

    /**
     * 检查数据是否重复
     *
     * @param filed
     * @param obj
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:03 2023/5/17
     */
    default void checkExistAllowNullFalseException(String filed, Object obj, String errorMessage) {
        if (StringUtils.isEmpty(filed) || ObjectUtils.isEmpty(obj)) {
            throw new IRException(errorMessage);
        }
        checkExistFalseException(filed, obj, errorMessage);
    }

    /**
     * 检查数据是否重复
     *
     * @param filed
     * @param obj
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:03 2023/5/17
     */
    default void checkExistTrueException(String filed, Object obj, String errorMessage) {
        if (checkExist(filed, obj)) {
            throw new IRException(errorMessage);
        }
    }

    /**
     * 检查数据是否重复
     *
     * @param queryWrapper
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:03 2023/5/17
     */
    default void checkExistTrueException(QueryWrapper<T> queryWrapper, String errorMessage) {
        if (checkExist(queryWrapper)) {
            throw new IRException(errorMessage);
        }
    }

    /**
     * 检查数据是否重复
     *
     * @param queryWrapper
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:03 2023/5/17
     */
    default void checkExistFalseException(QueryWrapper<T> queryWrapper, String errorMessage) {
        if (!checkExist(queryWrapper)) {
            throw new IRException(errorMessage);
        }
    }

    /**
     * 检查数据是否重复
     *
     * @param filed
     * @param obj
     * @param errorMessage
     * @return void
     * @author peixinyi
     * @since 14:03 2023/5/17
     */
    default void checkExistFalseException(String filed, Object obj, String errorMessage) {
        if (!checkExist(filed, obj)) {
            throw new IRException(errorMessage);
        }
    }


    /**
     * 检查数据是否存在
     *
     * @param filed
     * @param obj
     * @return void
     * @author PeiXy_J
     * @since 13:26 2023/3/17
     */
    default boolean checkExist(String filed, Object obj) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(filed, obj);
        queryWrapper.last("limit 1");
        return checkExist(queryWrapper);
    }

    /**
     * 检查数据是否存在
     *
     * @param queryWrapper
     * @return boolean
     * @author peixinyi
     * @since 11:53 2023/5/16
     */
    default boolean checkExist(QueryWrapper<T> queryWrapper) {
        queryWrapper.last("limit 1");
        return iCount(queryWrapper) > 0;
    }

    /**
     * 检查数据是否存在
     *
     * @param filed
     * @param obj
     * @param errorMessage
     * @return T
     * @author peixinyi
     * @since 13:57 2023/5/16
     */
    default T checkExistWithReturnObj(String filed, Object obj, String errorMessage) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(filed, obj);
        queryWrapper.last("limit 1");
        return checkExistWithReturnObj(queryWrapper, errorMessage);
    }

    /**
     * 检查数据并且返回对象
     *
     * @param queryWrapper
     * @param errorMessage
     * @return T
     * @author peixinyi
     * @since 13:55 2023/5/16
     */
    default T checkExistWithReturnObj(QueryWrapper<T> queryWrapper, String errorMessage) {
        queryWrapper.last("limit 1");
        T one = getOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(one)) {
            return one;
        }
        throw new IRException(errorMessage);
    }

    /**
     * 过滤已存在的数据
     *
     * @param queryWrapper
     * @param filterObjects
     * @return void
     * @author PeiXy_J
     * @since 19:01 2023/3/15
     */
    default List<T> filterExistData(QueryWrapper<T> queryWrapper, List<T> filterObjects, BiPredicate<T, T> predicate) {
        List<T> existObjects = iList(queryWrapper);
        filterObjects.removeIf(filtetObject -> existObjects.stream().anyMatch(existObject -> predicate.test(filtetObject, existObject)));
        return filterObjects;
    }

    /**
     * 计算数量
     *
     * @param queryWrapper
     * @return
     */
    long iCount(QueryWrapper<T> queryWrapper);

    /**
     * 查询列表
     *
     * @param queryWrapper
     * @return java.util.List<T>
     * @author PeiXy_J
     * @since 19:06 2023/3/15
     */
    List<T> iList(QueryWrapper<T> queryWrapper);

    /**
     * 查询单个
     *
     * @param queryWrapper
     * @return T
     * @author peixinyi
     * @since 13:54 2023/5/16
     */
    T getOne(QueryWrapper<T> queryWrapper);
}

package com.intelasers.utils;

import com.intelasers.entity.ResultEntity;


/**
 * @author ling
 */
public class BaseResultUtil {




    /**
     * 返回成功对象
     * @param data
     * @return
     */
    public static ResultEntity resSuccess(Object data){
        ResultEntity result = new ResultEntity();
        result.setStatus(10000);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败对象
     * @param data
     * @return
     */
    public static ResultEntity resFailed(Object data){
        ResultEntity result = new ResultEntity();
        result.setStatus(10001);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    /**
     * 304错误
     * @param data
     * @return
     */
    public static ResultEntity resNetError(Object data){
        ResultEntity result = new ResultEntity();
        result.setStatus(304);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }


}

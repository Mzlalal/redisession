package mzlalal.redisession.entity;

import lombok.Data;

import java.util.LinkedHashMap;

/**
 * @description:  $.ajax后需要接受的JSON
 * @author:       Mzlalal
 */
@Data
public class AjaxJson {
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 错误代码
     */
    private String errorCode = "-1";
    /**
     * 提示信息
     */
    private String msg = "操作成功";
    /**
     * 封装json的map
     */
    private LinkedHashMap<String, Object> body = new LinkedHashMap();

    /**
     * 向json中添加属性，在js中访问，请调用data.map.key
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        body.put(key, value);
    }

    /**
     * 删除key
     * @param key
     */
    public void remove(String key) {
        body.remove(key);
    }

    /**
     * 向json中添加属性，在js中访问，请调用data.msg
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回错误信息
     * @param msg
     */
    public void setErrorMsg(String msg){
        this.success = false;
        this.msg = msg;
    }
}

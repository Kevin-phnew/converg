package metadata;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Kevin
 * @Date 2020/3/4
 * @Desc 连接jdbc，获取数据库元数据
 */
public interface GetMetaData {

//    获取元数据,返回json   new JSONObject().put("k","v")
    abstract JSONObject getMetaData(String... strings);


}

package metadata;

import com.alibaba.fastjson.JSONObject;

public interface GetMetaData {

//    获取元数据,返回json   new JSONObject().put("k","v")
JSONObject getMetaData(String... strings);


}

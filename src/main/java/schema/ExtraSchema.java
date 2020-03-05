package schema;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Kevin
 * @Date 2020/3/4
 * @Desc 解析json并输出schema文件
 */
public class ExtraSchema {

    //输出schema
    public void exportSchema(JSONObject json){
        String schema = getSchema(json);
        //下面进行存储的工作...
    }

    //解析json格式并形成要求的schema
    private String getSchema(JSONObject json){
        return null;
    }

}

import com.alibaba.fastjson.JSONObject;
import com.hetangyuese.netty.util.HttpClientUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @program: netty-root
 * @description: 测试
 * @author: hewen
 * @create: 2019-10-30 10:46
 **/
public class TestJunit {

    // 请求url
    private static final String url = "http://127.0.0.1:9000";

    private Logger log = LoggerFactory.getLogger(TestJunit.class);

    @Test
    public void testClient() {
        JSONObject obj = new JSONObject();
        obj.put("userId", 123);
        obj.put("userType", 1);
        try {
            String response = HttpClientUtil.doPost(url, obj.toJSONString());
            Assert.assertNotNull("响应为空了", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

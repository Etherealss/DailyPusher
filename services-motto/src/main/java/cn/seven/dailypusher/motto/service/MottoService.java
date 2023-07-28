package cn.seven.dailypusher.motto.service;




import cn.seven.dailypusher.motto.vo.MottoVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class MottoService {


    private final RestTemplate restTemplate;

    public MottoVo getMotto() {

        String url ="https://api.xygeng.cn/one";

        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject);
        JSONObject data = jsonObject.getJSONObject("data");
//        System.out.println(data);

        return MottoVo.builder().content(data.getString("content")).origin(data.getString("origin")).tag(data.getString("tag")).build();

    }


}

package cn.seven.dailypusher.motto.domain.service;




import cn.seven.dailypusher.motto.infrastructure.client.MottoResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class MottoService {


    private final RestTemplate restTemplate;

    public MottoResponse getMotto() {

        String url ="https://api.xygeng.cn/one";

        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject);
        JSONObject data = jsonObject.getJSONObject("data");
//        System.out.println(data);

        return MottoResponse.builder().content(data.getString("content")).origin(data.getString("origin")).tag(data.getString("tag")).build();

    }


}

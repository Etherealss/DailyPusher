package cn.seven.dailypusher.motto.infrastructure.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MottoResponse {
     String content; //格言内容
     String origin; //格言出处
     String tag; //格言标签

     public String buildReport() {
            return "格言：" + content + "\n" + "出处：" + origin + "\n" + "标签：" + tag;
     }
}

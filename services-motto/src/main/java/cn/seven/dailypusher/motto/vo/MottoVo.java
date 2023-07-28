package cn.seven.dailypusher.motto.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MottoVo {
   private String content; //格言内容
    private String origin; //格言出处
    private String tag; //格言标签
}
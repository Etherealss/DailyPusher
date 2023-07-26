package cn.seven.dailypusher.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类型常亮类
 * @author wtk
 */
@Getter
@AllArgsConstructor
public class NotificationType {
    public static final String ANSWER_NEW_LIKE = "answerNewLike";
    public static final String COMMENT_NEW_LIKE = "commentNewLike";
    public static final String ANSWER_NEW_COMMENT = "answerNewComment";
    public static final String COMMENT_NEW_REPLY = "commentNewReply";
    public static final String QUESTION_NEW_ANSWER = "questionNewAnswer";
    public static final String QUESTION_COLLECTED = "questionCollected";
    public static final String ANSWER_ACCEPTED = "answerAccepted";
}

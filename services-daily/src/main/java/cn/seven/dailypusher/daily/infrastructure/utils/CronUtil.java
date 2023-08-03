package cn.seven.dailypusher.daily.infrastructure.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王腾坤
 * @date 2023/8/2
 */
public class CronUtil {
    private static Map<Integer, String> dayMap = new HashMap<>(10);

    static {
        dayMap.put(1, "MON");
        dayMap.put(2, "TUE");
        dayMap.put(3, "WED");
        dayMap.put(4, "THU");
        dayMap.put(5, "FRI");
        dayMap.put(6, "SAT");
        dayMap.put(7, "SUN");
    }

    public static String buildCron(int weekDay, String time) {
        if (weekDay <= 0) {
            throw new IllegalArgumentException("weekDay必须大于0");
        }
        // 获取小时和分钟
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        // 根据weekDay生成cron表达式中的星期字段
        StringBuilder weekDayCron = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            if (((weekDay >> i) & 1) == 1) {
                weekDayCron.append(dayMap.get(i + 1)).append(",");
            }
        }
        weekDayCron.deleteCharAt(weekDayCron.length() - 1);
        // 生成cron表达式
        String cronExpression = String.format("0 %d %d ? * %s", minute, hour, weekDayCron);
        return cronExpression;
    }

    /**
     * 根据cron表达式解析时间
     */
    public static String toDayTime(String cron) {
        String[] cronArr = cron.split(" ");
        return cronArr[2] + ":" + cronArr[1];
    }

    /**
     * 根据cron表达式解析星期表达式
     */
    public static int toWeekDayPattern(String cron) {
        String[] cronArr = cron.split(" ");
        String[] weekDayArr = cronArr[5].split(",");
        int weekDayPattern = 0;
        for (String weekDayStr : weekDayArr) {
            for (Map.Entry<Integer, String> entry : dayMap.entrySet()) {
                if (entry.getValue().equals(weekDayStr)) {
                    weekDayPattern += 1 << (entry.getKey() - 1);
                }
            }
        }
        return weekDayPattern;
    }
}
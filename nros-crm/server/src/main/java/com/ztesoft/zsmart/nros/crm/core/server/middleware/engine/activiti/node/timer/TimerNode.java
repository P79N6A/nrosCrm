package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.timer;

import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间节点
 *
 * @author admin
 * @date 2018/6/6
 **/
public class TimerNode {
    private transient Logger logger = LoggerFactory.getLogger(TimerNode.class);

    // crontab表达式
    private String crontab;

    // 延后时间
    private int interval;

    // 延后时间单位
    private int intervalUnit;

    private TimeData timeData;

    private Date from;

    private Date to;

    public String getCrontab() {
        return crontab;
    }

    public void setCrontab(String crontab) {
        this.crontab = crontab;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(int intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public TimeData getTimeData() {
        return timeData;
    }

    public void setTimeData(TimeData timeData) {
        this.timeData = timeData;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public TimerNode(JSONObject timeDataJson) {
        this.timeData = timeDataJson.toJavaObject(TimeData.class);
        if (0 == timeData.scheduleWay) {
            from = timeData.from;
            to = timeData.to;
            StringBuffer sb = new StringBuffer();

            generate(sb, timeData.second, "0");
            generate(sb, timeData.minute, "0");
            generate(sb, timeData.hour, "0");
            if (timeData.endMonth) {
                sb.append("L ");
            }
            else {
                generate(sb, timeData.day, "*");
            }
            generate(sb, timeData.month, "*");
            if (null != timeData.week && timeData.week.length > 0) {
                for (int i = 0; i < timeData.week.length; i++) {
                    timeData.week[i] = timeData.week[i] + 1 > 7 ? 1 : timeData.week[i] + 1;
                }
            }
            generate(sb, timeData.week, "?");

            if (null != timeData.year && timeData.year > 0) {
                sb.append(timeData.year);
            }
            else {
                sb.append("*");
            }
            crontab = sb.toString();

            if (null != timeData.week && timeData.week.length > 0) {
                crontab = StringUtils.replace(crontab, "*", "?", 1);
            }
        }
        else {
            interval = timeData.interval;
            intervalUnit = timeData.intervalUnit;
        }
    }

    private void generate(StringBuffer sb, Integer[] cycle, String holder) {
        if (null != cycle && cycle.length > 0 && null != cycle[0]) {
            sb.append(Arrays.toString(cycle).replace("[", "").replace("]", "").replaceAll(" ", ""));
        }
        else {
            sb.append(holder);
        }
        sb.append(" ");
    }

    private static class TimeData {
        private int scheduleWay = 0;

        private Integer interval;

        private Integer intervalUnit;

        private Date from;

        private Date to;

        private Integer year;

        private Integer[] month;

        private Integer[] week;

        private Integer[] day;

        private Integer[] hour;

        private Integer[] minute;

        private Integer[] second;

        private boolean endMonth = false;

        public int getScheduleWay() {
            return scheduleWay;
        }

        public void setScheduleWay(int scheduleWay) {
            this.scheduleWay = scheduleWay;
        }

        public Integer getInterval() {
            return interval;
        }

        public void setInterval(Integer interval) {
            this.interval = interval;
        }

        public Integer getIntervalUnit() {
            return intervalUnit;
        }

        public void setIntervalUnit(Integer intervalUnit) {
            this.intervalUnit = intervalUnit;
        }

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
            this.to = to;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer[] getMonth() {
            return month;
        }

        public void setMonth(Integer[] month) {
            this.month = month;
        }

        public Integer[] getWeek() {
            return week;
        }

        public void setWeek(Integer[] week) {
            this.week = week;
        }

        public Integer[] getDay() {
            return day;
        }

        public void setDay(Integer[] day) {
            this.day = day;
        }

        public Integer[] getHour() {
            return hour;
        }

        public void setHour(Integer[] hour) {
            this.hour = hour;
        }

        public Integer[] getMinute() {
            return minute;
        }

        public void setMinute(Integer[] minute) {
            this.minute = minute;
        }

        public Integer[] getSecond() {
            return second;
        }

        public void setSecond(Integer[] second) {
            this.second = second;
        }

        public boolean isEndMonth() {
            return endMonth;
        }

        public void setEndMonth(boolean endMonth) {
            this.endMonth = endMonth;
        }
    }
}

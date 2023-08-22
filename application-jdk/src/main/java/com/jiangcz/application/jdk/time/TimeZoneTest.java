package com.jiangcz.application.jdk.time;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

// Asia/Shanghai, Asia/Chongqing, Asia/Hong_Kong, Asia/Macao, ...

        // 东八区时间，与标准时间相差8小时
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs(8*60*60*1000)));

// [Asia/Brunei, Asia/Choibalsan, Asia/Chongqing, Asia/Chungking, Asia/Harbin,
// Asia/Hong_Kong, Asia/Irkutsk, Asia/Kuala_Lumpur, Asia/Kuching, Asia/Macao,
// Asia/Macau, Asia/Makassar, Asia/Manila, Asia/Shanghai, Asia/Singapore,
// Asia/Taipei, Asia/Ujung_Pandang, Asia/Ulaanbaatar, Asia/Ulan_Bator,
// Australia/Perth, Australia/West, CTT, Etc/GMT-8, Hongkong, PRC, Singapore]

        System.out.println(TimeZone.getDefault());

// sun.util.calendar.ZoneInfo[id="GMT+08:00",offset=28800000,
// dstSavings=0,useDaylight=false,transitions=0,lastRule=null]


        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(TimeZone.getDefault());

// sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,
// dstSavings=0,useDaylight=false,transitions=19,lastRule=null]


        System.out.println(TimeZone.getTimeZone("GMT+08:00"));

// sun.util.calendar.ZoneInfo[id="GMT+08:00",offset=28800000,
// dstSavings=0,useDaylight=false,transitions=0,lastRule=null]


        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone.getDisplayName()); // 中国标准时间


        TimeZone timeZone0 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone0.getDisplayName()); // 中国标准时间
        System.out.println(timeZone0.getDisplayName(Locale.ENGLISH)); // China Standard Time




        TimeZone timeZone1 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone1.getDisplayName()); // 中国标准时间
        System.out.println(timeZone1.getDisplayName(false, TimeZone.LONG)); // 中国标准时间
        System.out.println(timeZone1.getDisplayName(false, TimeZone.SHORT)); // CST (China Standard Time)



        TimeZone timeZone2 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone2.getDisplayName()); // 中国标准时间
        System.out.println(timeZone2.getDisplayName(false, TimeZone.LONG, Locale.ENGLISH)); // China Standard Time


        TimeZone timeZone3 = TimeZone.getTimeZone("Asia/Shanghai");
        timeZone3.setID("Asia/Chongqing");
        System.out.println(timeZone3.getID()); // Asia/Chongqing


        TimeZone timeZone4 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone4.getOffset(System.currentTimeMillis())); // 28800000

        TimeZone timeZone5 = TimeZone.getTimeZone("Asia/Shanghai");
// 中国没有夏令时，故为0
        System.out.println(timeZone5.getDSTSavings()); // 0


        TimeZone timeZone6 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone6.getRawOffset()); // 28800000


        TimeZone timeZone7 = TimeZone.getTimeZone("Asia/Shanghai");
        timeZone7.setRawOffset(25200000);
        System.out.println(timeZone7.getRawOffset()); // 25200000

        TimeZone timeZone8 = TimeZone.getTimeZone("Asia/Shanghai");
        ZoneId zoneId = timeZone8.toZoneId();
        System.out.println(zoneId); // Asia/Shanghai


        TimeZone timeZone9 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone9.useDaylightTime()); // false

        TimeZone timeZone10 = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone10.inDaylightTime(new Date())); // false


        TimeZone timeZone11 = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone timeZone12 = TimeZone.getTimeZone("Asia/Chongqing");
        System.out.println(timeZone11.hasSameRules(timeZone12)); // true
    }
}

package com.onebooming.frastructure.utils;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/11 19:32
 * <p>
 * 后台公共函数
 */


import com.onebooming.frastructure.model.MetaDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public final class AdminCommons {

    /**
     * 判断category和cat的交集
     * @param category
     * @param cats
     * @return
     */
    public static boolean existCat(MetaDomain category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                /**
                 * “trim()的作用是去掉字符串两端的多余的空格,注意,是两端的空格,
                 *  且无论两端的空格有多少个都会去掉,当然 中间的那些空格不会被去掉
                 */
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }


    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机返回一种颜色
     * @return
     */
    public static String rand_color() {
        //产生一个0~COLOS.length-1之间的随机数，通过这个随机数作下标，随机返回一种颜色
        int r = Tools.rand(0, COLORS.length - 1);
        return COLORS[r];
    }

}

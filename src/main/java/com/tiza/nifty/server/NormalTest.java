package com.tiza.nifty.server;

import com.tz.earth.GetLocationUtil;
import EnLonLat.EnLonLat;

/**
 * Class descriptions : NormalTest
 * Created by Chauncey on 2014/10/15 19:28.
 */
public class NormalTest {
    public static void main(String[] args) {
        EnLonLat.FreeDll();
        EnLonLat.InitDll();
        double lon = 126.273591D;
        double lat = 39.532681D;
        // 初始化省市区服务器
        GetLocationUtil.init();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            String encLonLat = EnLonLat.getEncLonLat(lon, lat);
            int idx = encLonLat.indexOf(",");
            double d1 = Double.parseDouble(encLonLat.substring(0, idx));
            double d2 = Double.parseDouble(encLonLat.substring(idx + 1));
            String str = GetLocationUtil.getLocation(d1, d2);
            String[] arr = str.split(" ");
            lon += 0.001;
            lat += 0.001;
        }
        System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
    }
}

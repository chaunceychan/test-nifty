package com.tiza.nifty.bean;

import EnLonLat.EnLonLat;
import com.google.common.base.Strings;
import com.tz.earth.GetLocationUtil;
import org.apache.thrift.TException;

/**
 * Class descriptions : LocationServiceImpl
 * Created by Chauncey on 2014/10/14 17:06.
 */
public class LocationServiceImpl implements LocationService.Iface {

    @Override
    public Location getRealLocation(double lng, double lat) throws TException {
        Location location = new Location();
        double d1 = lng;
        double d2 = lat;
        try {
            String encLonLat = EnLonLat.getEncLonLat(lng, lat);
            int idx = encLonLat.indexOf(",");
            d1 = Double.parseDouble(encLonLat.substring(0, idx));
            d2 = Double.parseDouble(encLonLat.substring(idx + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = GetLocationUtil.getLocation(d1, d2);
        String[] arr = str.split(" ");
        location.setElat(d1);
        location.setElng(d2);
        location.setProvince(Strings.nullToEmpty(arr[0]));
        location.setCity(Strings.nullToEmpty(arr[1]));
        location.setArea(Strings.nullToEmpty(arr[2]));
        return location;
    }
}

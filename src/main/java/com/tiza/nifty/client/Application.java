package com.tiza.nifty.client;

import com.tiza.nifty.bean.Location;
import com.tiza.nifty.bean.LocationService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Class descriptions : Application
 * Created by Chauncey on 2014/10/14 17:20.
 */
public class Application {

    public static void main(String[] args) throws TException {
        long start = System.currentTimeMillis();
        TTransport transport = new TSocket("127.0.0.1", 9999);
        try {
            TProtocol protocol = new TCompactProtocol(transport);
            LocationService.Client client = new LocationService.Client(protocol);
            transport.open();
            for (int i = 0; i < 1000000; i++) {
                Location location = client.getRealLocation(117.12345, 34.23456);
//                System.out.println(location);
            }
            System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
        } catch (TException e) {
            e.printStackTrace();
        }
        transport.close();
    }
}

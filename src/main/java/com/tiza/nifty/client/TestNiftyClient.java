package com.tiza.nifty.client;


import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.client.NiftyClient;
import com.facebook.nifty.duplex.TDuplexProtocolFactory;
import com.tiza.nifty.bean.Location;
import com.tiza.nifty.bean.LocationService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TTransport;

import java.net.InetSocketAddress;

/**
 * Class descriptions : TestNiftyClient
 * Created by Chauncey on 2014/10/15 10:30.
 */
public class TestNiftyClient {
    public static void main(String[] args) throws InterruptedException, TException {
        long start = System.currentTimeMillis();
        InetSocketAddress address = new InetSocketAddress("192.168.1.51", 9999);
        NiftyClient client = new NiftyClient();
        FramedClientConnector connector = new FramedClientConnector(address, TDuplexProtocolFactory.fromSingleFactory(new TCompactProtocol.Factory()));
        TTransport transport = client.connectSync(LocationService.Client.class, connector);
        TCompactProtocol protocol = new TCompactProtocol(transport);
        LocationService.Client _client = new LocationService.Client(protocol);
        for (int i = 0; i < 100; i++) {
            Location location = _client.getRealLocation(117.12345, 34.23456);
//            System.out.println(location);
        }
        System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
        client.close();
    }
}

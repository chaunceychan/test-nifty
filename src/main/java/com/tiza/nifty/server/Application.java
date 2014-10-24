package com.tiza.nifty.server;

import EnLonLat.EnLonLat;
import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.tiza.nifty.bean.LocationService;
import com.tiza.nifty.bean.LocationServiceImpl;
import com.tz.earth.GetLocationUtil;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;

/**
 * Class descriptions : Application
 * Created by Chauncey on 2014/10/14 16:58.
 */
public class Application {
    public static void main(String[] args) {
        //初始化经纬度服务
        EnLonLat.FreeDll();
        EnLonLat.InitDll();
        double lon = 126.273591D;
        double lat = 39.532681D;
        EnLonLat.getEncLonLat(lon, lat);
        // 初始化省市区服务器
        GetLocationUtil.init();
        // Create the handler
        LocationService.Iface serviceInterface = new LocationServiceImpl();

        // Create the processor
        TProcessor processor = new LocationService.Processor(serviceInterface);

        // Build the server definition
        ThriftServerDef serverDef = new ThriftServerDefBuilder().protocol(new TCompactProtocol.Factory()).listen(9999).withProcessor(processor)
                .build();

        // Create the server transport
        final NettyServerTransport server = new NettyServerTransport(serverDef);

        // Start the server
        server.start();

        // Arrange to stop the server at shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    server.stop();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}

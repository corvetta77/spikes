package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final HostConfiguration HOST_CONFIGURATION = new HostConfiguration(new ArrayList<>());
    private static final HostsChooser hostChooser = new HostsChooser();

    public static void main(String[] args) throws IOException {
        HOST_CONFIGURATION.registerNewHost("test1");
        HOST_CONFIGURATION.registerNewHost("test2");


        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/getAll", exchange -> {
            String responseContent = String.valueOf(HOST_CONFIGURATION.getHosts());
            exchange.sendResponseHeaders(200,  responseContent.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(responseContent.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        });

        server.createContext("/getOne", exchange -> {
            String responseContent = String.valueOf(hostChooser.findOne(HOST_CONFIGURATION.getHosts()));
            exchange.sendResponseHeaders(200,  responseContent.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(responseContent.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        });

        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
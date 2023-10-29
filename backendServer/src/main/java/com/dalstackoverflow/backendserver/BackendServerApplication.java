package com.dalstackoverflow.backendserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Sreejith Nair
 * This is the main spring run class for DalOverFlow
 */
@SpringBootApplication
public class BackendServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendServerApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(BackendServerApplication.class, args);
        LOGGER.info("------Application startup completed-------");
        InetAddress address = InetAddress.getLocalHost();
        LOGGER.info("Host address: " + address.getHostAddress());
    }
}

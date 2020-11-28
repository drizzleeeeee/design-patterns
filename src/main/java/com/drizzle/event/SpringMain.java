package com.drizzle.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监听器模式不依赖Spring环境启动Demo
 */
@SpringBootApplication
public class SpringMain {

    @Autowired
    private WeatherEventMulticaster weatherEventMulticaster;

    @Autowired
    private RainListener rainListener;

    public static void main(String[] args) {

        SpringApplication.run(SpringMain.class, args);
    }

    @RestController
    class TestController {
        @RequestMapping("/test/event")
        public void eventTest() {

            weatherEventMulticaster.multicastEvent(new SnowEvent());
            weatherEventMulticaster.multicastEvent(new RainEvent());
            weatherEventMulticaster.removeListener(rainListener);
            System.out.println("remove rainListener");
            weatherEventMulticaster.multicastEvent(new SnowEvent());
            weatherEventMulticaster.multicastEvent(new RainEvent());
            weatherEventMulticaster.addListener(rainListener);
            System.out.println("add rainListener");
            weatherEventMulticaster.multicastEvent(new SnowEvent());
            weatherEventMulticaster.multicastEvent(new RainEvent());
        }
    }
}

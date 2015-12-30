package com.esoft.archer.bankcard.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/12/30 0030.
 */

@Component
public class Ceshi implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("111111111111111111111111111");

        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {

            System.out.println("222222222222222222222222222");

        }






    }
}

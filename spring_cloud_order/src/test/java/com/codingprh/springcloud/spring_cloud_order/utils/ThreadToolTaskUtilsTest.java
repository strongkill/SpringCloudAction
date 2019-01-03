package com.codingprh.springcloud.spring_cloud_order.utils;

import com.codingprh.springcloud.spring_cloud_order.SpringCloudOrderApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;

/**
 * @author codingprh
 * @create 2019-01-02 9:53 AM
 */
public class ThreadToolTaskUtilsTest extends SpringCloudOrderApplicationTests {
    @Autowired
    private ExecutorService threadPoolTaskExecutor11;

    @Test
    public void threadPoolExcutor() {
        threadPoolTaskExecutor11.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "1111");
        });

        threadPoolTaskExecutor11.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "22222");
        });

        threadPoolTaskExecutor11.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "33333");
        });
    }

    @Test
    public void testListIterator() {
        List<String> list = Arrays.asList("1111", "222", "333");
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String num = (String) listIterator.next();
            num = "00000";
            listIterator.set(num);
        }
        list.stream().forEach(e -> {
            System.out.println(e);
        });

    }
}
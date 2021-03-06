package com.demo.controller;

import com.demo.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <h1>消费者</h1>
 *
 * <p>全局降级</p>
 *
 * <p>
 * createDate 2022/02/19 19:57:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@AllArgsConstructor
@RequestMapping("consumer2")
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class Consumer2Controller {

    private final PaymentService paymentService;

    /**
     * 正常访问
     * http://localhost/consumer2/ok?id=123
     *
     * @param id id
     */
    @GetMapping("ok")
    // 没有使用@HystrixCommand注解，不会启用降级
    public String ok(Integer id) {
        // id小于0时，运行出错
        if (id < 0) {
            log.info(String.valueOf(10 / 0));
        }
        String s = paymentService.ok(id);
        log.info(s);
        return s;
    }

    /**
     * 错误
     * http://localhost/consumer2/err?id=123
     */
    @GetMapping("err")
    // 使用默认降级处理
    @HystrixCommand
    public String err(Integer id) {
        // id小于0时，运行出错
        if (id < 0) {
            log.info(String.valueOf(10 / 0));
        }
        String s = paymentService.ok(id);
        log.info(s);
        return s;
    }

    /**
     * 超时访问
     * 延时2000毫秒
     * http://localhost/consumer2/timeout?time=2000
     * 当启用压力测试时，正常访问ok时也变慢
     *
     * @param time 毫秒
     */
    @GetMapping("timeout")
    // 降级处理，运行出错或超时1500毫秒调用降级方法
    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {//
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public String timeout(Integer time) {
        // time小于0时，运行出错
        if (time < 0) {
            log.info(String.valueOf(10 / 0));
        }
        String s = paymentService.timeout(time);
        log.info(s);
        return s;
    }

    /**
     * 超时
     *
     * @param time 毫秒
     */
    public String timeoutHandler(Integer time) {
        return "降级处理，运行出错或超时1500毫秒调用降级方法，线程池：" + Thread.currentThread().getName() + "，延时：" + time + "毫秒" + "，UUID：" + UUID.randomUUID();
    }

    /**
     * 统一fallback
     */
    public String globalFallback() {
        return "统一fallback";
    }

}

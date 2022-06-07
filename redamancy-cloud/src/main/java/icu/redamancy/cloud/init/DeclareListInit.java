package icu.redamancy.cloud.init;

import icu.redamancy.cloud.service.CloudService;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;

/**
 * @Author redamancy
 * @Date 2022/6/7 11:03
 * @Version 1.0
 */
public class DeclareListInit implements CommandLineRunner {

    @Resource
    CloudService cloudService;

    @Override
    public void run(String... args) throws Exception {
        cloudService.init();
    }
}

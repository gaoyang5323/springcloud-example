package com.kakuiwong.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @XxlJob("testJobHandler")
    public void demoJobHandler() throws Exception {
        System.out.println("执行成功");
        XxlJobHelper.log("XXL-JOB, Hello World.");
    }
}

package com.kakuiwong.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestJob {

    private final static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @XxlJob("testJobHandler")
    public void demoJobHandler() {
        //调度器传入的自定义参数
        String jobParam = XxlJobHelper.getJobParam();
        System.out.println("参数: " + jobParam);

        //分片索引及分片数量,可以用于分片执行任务,同时运行
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        List<UserInfo> users = new ArrayList();
        for (UserInfo user : users) {
            Integer id = user.getId();
            //根据id取模,分片处理任务
            if (id % shardTotal == shardIndex) {
                //处理业务
            }
        }

        XxlJobHelper.log("XXL-JOB, Hello World.");
    }

    static class UserInfo {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

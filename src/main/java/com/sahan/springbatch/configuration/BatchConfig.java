package com.sahan.springbatch.configuration;

import com.sahan.springbatch.task.MyTaskFour;
import com.sahan.springbatch.task.MyTaskOne;
import com.sahan.springbatch.task.MyTaskThree;
import com.sahan.springbatch.task.MyTaskTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(new MyTaskOne())
                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();
    }

    @Bean
    public Step stepThree(){
        return steps.get("stepThree")
                .tasklet(new MyTaskThree())
                .build();
    }

    @Bean
    public Step stepFour(){
        return steps.get("stepFour")
                .tasklet(new MyTaskFour())
                .build();
    }


    @Bean
    @Scheduled(cron = "0 */1 * * * ?")
    public Job demoJob(){
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .next(stepFour())
                .next(stepThree())
                .build();
    }
}

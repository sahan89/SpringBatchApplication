package com.sahan.springbatch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MyTaskThree implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("############# TASK 03 #############");
        System.out.println("MyTaskThree Start..");
        System.out.println("MyTaskThree Done..");
        return RepeatStatus.FINISHED;
    }
}

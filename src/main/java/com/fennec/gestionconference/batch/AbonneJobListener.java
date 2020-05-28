package com.fennec.gestionconference.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.COMPLETED;
import static org.springframework.batch.core.BatchStatus.STARTED;

@Component
public class AbonneJobListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == STARTED) {
            //log.info("ORDER BATCH PROCESS STARTED...!");
            System.out.println("ABONNE BATCH PROCESS STARTED...!");
        }
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == COMPLETED) {
            //log.info("ORDER BATCH PROCESS COMPLETED...!");
            System.out.println("ABONNE BATCH PROCESS COMPLETED...!");
        }
    }
}

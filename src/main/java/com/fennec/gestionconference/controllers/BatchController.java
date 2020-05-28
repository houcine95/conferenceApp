package com.fennec.gestionconference.controllers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping(path = "/loadabonnefile")
    @ResponseBody
    public String loadAbonne() {

        try {
            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(maps);
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            System.out.println("JobExecution Status : "+ jobExecution.getStatus());


            System.out.println("JobExecution Status : "+ jobExecution.getStatus());

            System.out.println("Batch is running ..............");
            while (jobExecution.isRunning()){
                System.out.println(".....................");
            }

            String status = jobExecution.getStatus().toString();
            return status;

        }catch (Exception e){
            return e.toString();
        }



    }
}

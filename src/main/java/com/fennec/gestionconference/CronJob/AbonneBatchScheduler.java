package com.fennec.gestionconference.CronJob;

import com.fennec.gestionconference.Utils;
import com.fennec.gestionconference.services.sendMailService;
import com.fennec.gestionconference.services.smsSenderService;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AbonneBatchScheduler {

    @Autowired JobLauncher jobLauncher;

    @Autowired private Job job;

    @Autowired private sendMailService sendmailService;

    @Autowired private smsSenderService smssenderService;



    @Scheduled(cron = "0 0 * * * *") //  the top of every hour of every day. // 1ere methode
    //@Scheduled(cron = "${batch.cron}") // 2eme methode application properties
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters());

        if (jobExecution.getStatus() == BatchStatus.COMPLETED){
            String result = moveToPrecessed();
            sendmailService.sendEmail();
            smssenderService.sendSms();
            System.out.println(result);
        }
    }

    public String moveToPrecessed(){

        try{

            String srctFolder = "./src/main/resources/Batch/InComing/";
            String destFolder = "./src/main/resources/Batch/processedFiles/";



            File dir = new File("./src/main/resources/Batch/InComing/");
            File[] filesList = dir.listFiles();
            for (File file : filesList) {
                if (file.isFile()) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(Date.from(Instant.now()));
                    String result = String.format("-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.csv", cal);
                    String fileName = file.getName() + Utils.getSaltString() + result;
                    FileUtils.moveFile(
                            FileUtils.getFile(srctFolder+file.getName()),
                            FileUtils.getFile(destFolder+fileName));

                }

            }


            //Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            Path path = FileSystems.getDefault().getPath("");
            //return path.toString();


            //Path temp = Files.move(Paths.get("C:\\Users\\ELHOUCINE\\IdeaProjects\\gestionconference\\src\\main\\resources\\Batch\\InComing\\testFile.csv"),Paths.get("C:\\Users\\ELHOUCINE\\IdeaProjects\\gestionconference\\src\\main\\resources\\Batch.processedFiles\\testFile.csv"));


            return "done";

        }catch (Exception e){
            return e.toString();
        }

    }
}

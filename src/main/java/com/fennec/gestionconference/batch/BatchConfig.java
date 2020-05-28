package com.fennec.gestionconference.batch;

import com.fennec.gestionconference.dao.AbonneRepository;
import com.fennec.gestionconference.entities.Abonne;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    public static final String ABONNE_ITEM_READER = "abonne reder";
    public static final String BATCH_STEP = "abonne step";
    public static final String ABONNE_PROCESS_JOB = "abonne step";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private AbonneFieldMapper fieldMapper;

    @Value("${app.csv.fileHeaders}")
    private String[] headers;

    @Value("classpath:Batch/InComing/*.csv")
    private Resource[] inputResources;


    @Bean
    public MultiResourceItemReader<Abonne> multiResourceItemReader()
    {
        MultiResourceItemReader<Abonne> resourceItemReader = new MultiResourceItemReader<Abonne>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }



    @Bean
    public FlatFileItemReader<Abonne> reader(){
        return new FlatFileItemReaderBuilder<Abonne>()
                .name(ABONNE_ITEM_READER)
                //.resource(new ClassPathResource("Batch/InComing/testFile.csv"))
                .linesToSkip(1)
                .delimited()
                .names(headers)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Abonne>() {{
                    setTargetType(Abonne.class);
                }}).build();
    }

    @Bean
    public LineMapper<Abonne> lineMapper() {

        final DefaultLineMapper<Abonne> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(headers);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldMapper);

        return defaultLineMapper;
    }


    @Bean
    public AbonneItemProcessor processor(){
        return new AbonneItemProcessor();
    }

    @Bean
    public ItemWriter<Abonne> writer(){
        return new AbonneItemWriter();
    }

    @Bean
    public Step step(ItemWriter<Abonne> writer) {
        return stepBuilderFactory.get(BATCH_STEP)
                .<Abonne, Abonne> chunk(50)
                .reader(multiResourceItemReader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(AbonneJobListener listener, Step step) {
        return jobBuilderFactory.get(ABONNE_PROCESS_JOB)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }
}

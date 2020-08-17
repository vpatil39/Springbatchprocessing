package springbatchexample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import springbatchexample.beans.User;

@Configuration

public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<User> itemReader, ItemProcessor<User, User> itemProcesser, ItemWriter<User> itemWriter) {

		Step step = stepBuilderFactory.get("ETL-file-load").<User, User>chunk(100).reader(itemReader)
				.processor(itemProcesser).writer(itemWriter).build();

		return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();

	}
//	@Value("${inputfile}") Resource resource

	@Bean

	public FlatFileItemReader<User> fileItemReader() {
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<User>();
		flatFileItemReader.setResource(new ClassPathResource("user.csv"));
		flatFileItemReader.setName("csv-reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());

		return flatFileItemReader;
	}

	public LineMapper<User> lineMapper() {
		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<User>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		java.lang.String[] names = { "id","name","dept","salary"};
		lineTokenizer.setNames(names);
		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<User>();
		fieldSetMapper.setTargetType(User.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}

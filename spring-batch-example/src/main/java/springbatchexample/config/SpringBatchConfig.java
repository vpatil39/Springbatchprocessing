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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import springbatchexample.beans.User;
import springbatchexample.beans.UserSpecify;
import springbatchexample.itemWriter.DbWriter;
import springbatchexample.processor.Processor;

@Configuration

public class SpringBatchConfig {

	@Autowired
	DbWriter dbWriter;

	@Autowired
	Processor processor;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<User> itemReader, ItemProcessor<User, UserSpecify> itemProcesser, ItemWriter<UserSpecify> itemWriter) {

		
		return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step(stepBuilderFactory))
				.build();

	}

	@Bean
	Step step(StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("ETL-file-load").<User, UserSpecify>chunk(100).reader(fileItemReader())
				.processor(processor).writer(dbWriter).build();
	}

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
		java.lang.String[] names = { "id", "name", "dept", "salary" };
		lineTokenizer.setNames(names);
		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<User>();
		fieldSetMapper.setTargetType(User.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}

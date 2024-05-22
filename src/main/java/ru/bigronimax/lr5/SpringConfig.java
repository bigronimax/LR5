package ru.bigronimax.lr5;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.bigronimax.lr5.extractor.impl.ExtractorImpl;
import ru.bigronimax.lr5.question.Question;
import ru.bigronimax.lr5.test.impl.TestImpl;

import java.io.IOException;
import java.util.HashMap;


@Configuration
public class SpringConfig {

    @Value("5")
    int amount;

    @Bean
    public ExtractorImpl extractor() {
        HashMap<Question, Boolean> questions = new HashMap<>();
        for (int i = 0; i < amount; i++) {
            Question question = new Question();
            questions.put(question, false);
        }
        return new ExtractorImpl(questions);
    }

    @Bean
    public HashMap<Question, Boolean> questions() throws CsvValidationException, IOException {
        return extractor().fromCsvIntoHash();
    }

    @Bean
    public TestImpl test() throws CsvValidationException, IOException {
        return new TestImpl(questions());
    }

}

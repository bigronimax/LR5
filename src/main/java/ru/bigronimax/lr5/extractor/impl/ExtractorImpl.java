package ru.bigronimax.lr5.extractor.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.bigronimax.lr5.extractor.Extractor;
import ru.bigronimax.lr5.question.Question;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ExtractorImpl implements Extractor {

    @Value("test.csv")
    String csvFile;
    private final HashMap<Question, Boolean> questions;

    @Override
    public HashMap<Question, Boolean> fromCsvIntoHash() throws IOException, CsvValidationException {
        File resource = new ClassPathResource(csvFile).getFile();
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(resource)).build();

        String[] nextLine;
        Set<Question> questionsSet = questions.keySet();
        Iterator<Question> iterator = questionsSet.iterator();
        while ((nextLine = csvReader.readNext()) != null) {
            Question question = iterator.next();
            question.setQuestionText(nextLine[0]);
            question.setAnswer(nextLine[1]);
        }
        return questions;
    }
}

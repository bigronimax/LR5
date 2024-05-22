package ru.bigronimax.lr5.extractor;

import com.opencsv.exceptions.CsvValidationException;
import ru.bigronimax.lr5.question.Question;

import java.io.IOException;
import java.util.HashMap;

public interface Extractor {

    HashMap<Question, Boolean> fromCsvIntoHash() throws IOException, CsvValidationException;
}

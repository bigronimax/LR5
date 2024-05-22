package ru.bigronimax.lr5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bigronimax.lr5.test.impl.TestImpl;

public class SpringMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        TestImpl test = context.getBean("test", TestImpl.class);

        test.introduce();
        test.answer();
        test.rightAnswers();
        test.result();

        context.close();

    }
}

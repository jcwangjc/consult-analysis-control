package com.consult.analysis.service.test.spring;

import com.consult.analysis.calculate.service.VocabularyServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Autowired
    private VocabularyServer vocabularyServer;

    @Test
    public void test(){
    }
}

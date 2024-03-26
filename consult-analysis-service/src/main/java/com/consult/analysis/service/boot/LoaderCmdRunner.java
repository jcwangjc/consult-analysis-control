package com.consult.analysis.service.boot;

import com.consult.analysis.calculate.cache.VocabularyManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : laoa
 * @describe : 启动初始化
 * @email : laoa@markcoin.net
 */
@Configuration
public class LoaderCmdRunner implements CommandLineRunner, DisposableBean{

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

    @Autowired
    private VocabularyManager vocabularyManager;

    @Override
    public void run(String... args) throws Exception {
        initDictionary();
//        initTemplate();
    }

    private void initDictionary(){
        vocabularyManager.loadCustomDictionary();
        executor.scheduleAtFixedRate(() -> {
            vocabularyManager.loadCustomDictionary();
        }, 60, 600, TimeUnit.SECONDS);
    }

//    private void initTemplate(){
//        iSystemAnalysisLanguageService.initTemplate();
//        executor.scheduleAtFixedRate(() -> {
//            iSystemAnalysisLanguageService.initTemplate();
//        }, 60, 300, TimeUnit.SECONDS);
//    }

    @Override
    public void destroy() throws Exception {
        try {
            executor.shutdown();
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
    }
}

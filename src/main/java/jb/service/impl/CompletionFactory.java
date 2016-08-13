package jb.service.impl;

import farming.concurrent.CompletionService;
import farming.concurrent.ExecutorCompletionServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by john on 16/8/13.
 */
@Service
public class CompletionFactory implements InitializingBean {

    @Resource
    private Executor taskExecutor;

    private static Executor executor;

    public static CompletionService initCompletion(){
        return new ExecutorCompletionServiceImpl(executor);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executor = taskExecutor;
    }
}

package jb.service.impl;

import jb.service.FmMessageServiceI;
import jb.service.TaskServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by john on 16/10/16.
 */
@Service
public class TaskServiceImpl implements TaskServiceI {
    @Autowired
    private FmMessageServiceI fmMessageService;
    @Override
    public void sendMessage() {
        System.out.println("I coming in");
    }
}

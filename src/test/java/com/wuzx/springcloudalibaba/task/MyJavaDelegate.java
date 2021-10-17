package com.wuzx.springcloudalibaba.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class MyJavaDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("脚本自动执行");

        final String input = (String) delegateExecution.getVariable("input");
        System.out.println(input);

        input.toUpperCase();

        delegateExecution.setVariable("input", input);
    }
}

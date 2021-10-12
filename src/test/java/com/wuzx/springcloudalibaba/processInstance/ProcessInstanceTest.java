package com.wuzx.springcloudalibaba.processInstance;

import com.wuzx.springcloudalibaba.SpringcloudalibabaApplicationTests;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class ProcessInstanceTest extends SpringcloudalibabaApplicationTests {


    @Resource
    private RepositoryService repositoryService;


    private String key = "first";
    @Test
    public void deployment() {
        String name = "first";


        final Deployment deploy = repositoryService.createDeployment()
                .name("请假流程")
                .key(key)
                .addClasspathResource("processes/" + name + ".bpmn20.xml")
                .addClasspathResource("processes/" + name + ".png")
                .category("HR")
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署Key:" + deploy.getKey());
        System.out.println("流程部署name:" + deploy.getName());
        System.out.println("流程部署分类：" + deploy.getCategory());

    }


    @Resource
    private RuntimeService runtimeService;

    @Test
    public void start() {
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);

        System.out.println("流程实例Id：" + processInstance.getId());
        System.out.println("流程定义Id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程定义key：" + processInstance.getProcessDefinitionKey());
        System.out.println("流程部署对象Id：" + processInstance.getDeploymentId());

    }

    @Resource
    private TaskService taskService;

    @Test
    public void findTask() {
        String assigne = "李四";

        final List<Task> list = taskService.createTaskQuery().taskAssignee(assigne).list();

        list.forEach(task ->
                {
                    System.out.println("任务Id:" + task.getId());
                    System.out.println("任务name:" + task.getName());
                    System.out.println("任务执行人:" + task.getAssignee());
                }

        );
    }


    @Resource
    private HistoryService historyService;


    @Test
    public void findHistoryTask() {
        String name = "李四";

        final List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(name).list();
        list.forEach(task ->
                {
                    System.out.println("任务Id:" + task.getId());
                    System.out.println("任务name:" + task.getName());
                    System.out.println("任务执行人:" + task.getAssignee());
                }

        );
    }


    @Test
    public void completeTask() {
        taskService.complete("4d8b45f3-2b6f-11ec-9649-42bcc292444b");
        System.out.println("完成");
    }


}

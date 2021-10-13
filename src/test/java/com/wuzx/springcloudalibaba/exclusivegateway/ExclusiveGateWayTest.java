package com.wuzx.springcloudalibaba.exclusivegateway;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExclusiveGateWayTest extends SpringcloudalibabaApplicationTests {


    private String key = "exclusiveGateWay";

    @Resource
    private RepositoryService repositoryService;

    @Test
    public void deployment() {
        final Deployment deploy = repositoryService.createDeployment()
                .name("财务审批")
                .key(key)
                .addClasspathResource("processes/" + key + ".bpmn20.xml")
                .addClasspathResource("processes/" + key + ".png")
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
        String assigne = "张三";

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
        Map<String, Object> value = new HashMap<>();
        value.put("money", 11000);
        taskService.complete("8c2cd895-2c39-11ec-a037-0ad2f2a4681b", value);
        System.out.println("完成");
    }
}

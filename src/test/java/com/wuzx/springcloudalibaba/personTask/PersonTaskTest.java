package com.wuzx.springcloudalibaba.personTask;

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

public class PersonTaskTest extends SpringcloudalibabaApplicationTests {


    private String key = "personTask2";

    @Resource
    private RepositoryService repositoryService;

    @Test
    public void deployment() {
        final Deployment deploy = repositoryService.createDeployment()
                .name("请假流程")
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

        Map<String, Object> value = new HashMap<>();
        value.put("userId", "吴志旋");
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, value);

        System.out.println("流程实例Id：" + processInstance.getId());
        System.out.println("流程定义Id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程定义key：" + processInstance.getProcessDefinitionKey());
        System.out.println("流程部署对象Id：" + processInstance.getDeploymentId());

    }

    @Resource
    private TaskService taskService;

    @Test
    public void findTask() {
        String assigne = "吴志旋";

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
        taskService.complete("0cbb5f9e-2c43-11ec-8a27-a20c72d2422b");
        System.out.println("完成");
    }
}

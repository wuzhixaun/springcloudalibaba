package com.wuzx.springcloudalibaba.processVariables;

import com.wuzx.springcloudalibaba.SpringcloudalibabaApplicationTests;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProcessVariablesTest extends SpringcloudalibabaApplicationTests {


    private String bpmbNameKey = "processVariables";

    @Resource
    private RepositoryService repositoryService;


    @Test
    public void deployment() {

        final Deployment deploy = repositoryService.createDeployment()
                .name("请假流程")
                .key(bpmbNameKey)
                .addClasspathResource("processes/" + bpmbNameKey + ".bpmn20.xml")
                .addClasspathResource("processes/" + bpmbNameKey + ".png")
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
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(bpmbNameKey);

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


    @Test
    public void setVariables() {
        String taskId = "6512adf4-2b7a-11ec-b3dc-36301dfc234f";
        taskService.setVariableLocal(taskId, "请假天数", 1); // 和谁绑定就只能查询
        taskService.setVariable(taskId, "请假日期", new Date());

        taskService.setVariable(taskId, "请假原因", "不想上班");
        System.out.println("设置流程变量成功");
    }

    @Test
    public void getVariables() {
        String taskId = "2206e8e7-2b7c-11ec-91ba-fa185d8a3a7a";

        final Map<String, Object> variables = taskService.getVariables(taskId);

        System.out.println(variables);

    }


    @Test
    public void completeTask() {
        taskService.complete("6512adf4-2b7a-11ec-b3dc-36301dfc234f");
        System.out.println("完成");
    }

}

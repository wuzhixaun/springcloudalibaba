package com.wuzx.springcloudalibaba.first;

import com.wuzx.springcloudalibaba.SpringcloudalibabaApplicationTests;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class ActivitiQuick extends SpringcloudalibabaApplicationTests {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;


    private String nameAndKey = "first";
    @Test
    public void createTable() {

    }

    /**
     *  第1步： 画流程图
     */

    /**
     * 第2步： 部署流程图
     */
    @Test
    public void deploy() {
        final Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("processes/first.bpmn20.xml")
                .addClasspathResource("processes/first.png")
                .key(nameAndKey)
                .name(nameAndKey + "name")
                .category("HR") // 分类
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署Key:" + deploy.getKey());
        System.out.println("流程部署name:" + deploy.getName());
        System.out.println("流程部署分类：" + deploy.getCategory());
    }


    /**
     * 第3步: 启动流程实例，相当于有人请假一次
     */
    @Test
    public void start() {
        // 获取流程实例
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(nameAndKey);
        System.out.println("流程实例Id：" + processInstance.getId());
        System.out.println("流程定义Id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程定义key：" + processInstance.getProcessDefinitionKey());
        System.out.println("流程部署对象Id：" + processInstance.getDeploymentId());
    }


    /**
     * 第4步:查询xx待办任务列表
     */
    @Test
    public void findMyTask() {
        String assignee = "李四";
        final List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(task ->
                    {
                        System.out.println("任务Id:" + task.getId());
                        System.out.println("任务name:" + task.getName());
                        System.out.println("任务执行人:" + task.getAssignee());
                    }

            );
        }

    }

    /**
     * 第5步: 执行任务
     */
    @Test
    public void complte() {
        taskService.complete("e232b931-2920-11ec-964d-1e3ec27cf18d");

        System.out.println("任务执行完成");
    }

    /**
     * 第6步: 查看历史流程实例
     */
    @Test
    public void findHistoryProcessInstance() {
        final List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(nameAndKey)
                .list();
        if (!CollectionUtils.isEmpty(list)) {

            for (HistoricProcessInstance historicProcessInstance : list) {
                System.out.println("业务系统key：" + historicProcessInstance.getBusinessKey());
                System.out.println("部署对象id：" + historicProcessInstance.getDeploymentId());
                System.out.println("执行时长：" + historicProcessInstance.getDurationInMillis());
                System.out.println("流程定义id：" + historicProcessInstance.getProcessDefinitionId());
                System.out.println("流程定义key：" + historicProcessInstance.getProcessDefinitionKey());
                System.out.println("流程定义名称：" + historicProcessInstance.getProcessDefinitionName());

            }

        }
    }


    /**
     * 第8步：查看历史任务
     */
    @Test
    public void findHistory() {
        final List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();

        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("任务执行人：" + historicTaskInstance.getAssignee());
            System.out.println("任务名称：" + historicTaskInstance.getName());
            System.out.println("任务id：" + historicTaskInstance.getId());
            System.out.println("分类：" + historicTaskInstance.getCategory());
            System.out.println("流程实例id：" + historicTaskInstance.getProcessDefinitionId());


        }
    }

}

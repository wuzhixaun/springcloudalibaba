package com.wuzx.springcloudalibaba;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication( exclude = SecurityAutoConfiguration.class)
public class SpringcloudalibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudalibabaApplication.class, args);
    }


    /**
     * 限流规则,代码和dashboard不兼容
     */
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("get");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}

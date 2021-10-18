package com.wuzx.springcloudalibaba.auth;

import com.wuzx.springcloudalibaba.SpringcloudalibabaApplicationTests;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import org.junit.jupiter.api.Test;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName AuthTest.java
 * @Description TODO
 * @createTime 2021年10月13日 09:32:00
 */
public class AuthTest extends SpringcloudalibabaApplicationTests {


    @Test
    public void api() {
        // 创建授权request
        final AuthGithubRequest authGithubRequest = new AuthGithubRequest(AuthConfig.builder()
                .clientId("842b468e0c3431117fe3")
                .clientSecret("0b3ea280f682703643f01ab1c9e2571dcd7883da")
                .redirectUri("http://localhost:1888/api/blade-auth/oauth/callback/github")
                .build());

        // 生成授权页面
        final String authorizeUrl  = authGithubRequest.authorize("state");


        System.out.println(authorizeUrl);
    }
}

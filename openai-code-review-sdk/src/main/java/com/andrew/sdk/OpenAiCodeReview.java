package com.andrew.sdk;

import com.andrew.sdk.domain.service.impl.OpenAiCodeReviewService;
import com.andrew.sdk.infrastructure.git.GitCommand;
import com.andrew.sdk.infrastructure.openai.IOpenAI;
import com.andrew.sdk.infrastructure.openai.impl.ChatGLM;
import com.andrew.sdk.infrastructure.weixin.WeiXin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenAiCodeReview {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiCodeReview.class);

    // 微信配置
    private String weixin_appid = "wxd3d3694006c83b57";
    private String weixin_secret = "01ab919f048455e82d5dd0e08db933c7";
    private String weixin_touser = "o2JKt6Ecc_R2JM6zEkTYPy5t7MR4";
    private String weixin_template_id = "cmwWUFqr335DESwlfCWo3azXYUsEno3CAMxOLbeUyHk";
    // ChatGLM配置
    private String chatglm_apiHost = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
    private String chatglm_apiKeySecret = "";
    // GitHub配置
    private String github_review_log_uri;
    private String github_token;
    // 工程配置 - 自动获取
    private String github_project;
    private String github_branch;
    private String github_author;

    public static void main(String[] args) throws Exception {
        GitCommand gitCommand = new GitCommand(
                getEnv("GITHUB_REVIEW_LOG_URI"),
                getEnv("GITHUB_TOKEN"),
                getEnv("COMMIT_PROJECT"),
                getEnv("COMMIT_BRANCH"),
                getEnv("COMMIT_AUTHOR"),
                getEnv("COMMIT_MESSAGE")
        );

        WeiXin weiXin = new WeiXin(
                getEnv("WEIXIN_APPID"),
                getEnv("WEIXIN_SECRET"),
                getEnv("WEIXIN_TOUSER"),
                getEnv("WEIXIN_TEMPLATE_ID")
        );

        IOpenAI openAI = new ChatGLM(getEnv("CHATGLM_APIHOST"), getEnv("CHATGLM_APIKEYSECRET"));

        OpenAiCodeReviewService openAiCodeReviewService = new OpenAiCodeReviewService(gitCommand, openAI, weiXin);
        openAiCodeReviewService.exec();

        logger.info("openai-code-review done!");
    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if (null == value || value.isEmpty()) {
            throw new RuntimeException("value is null");
        }
        return value;
    }

}
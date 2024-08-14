package com.andrew.sdk.domain.service;

import com.andrew.sdk.infrastructure.git.GitCommand;
import com.andrew.sdk.infrastructure.openai.IOpenAI;
import com.andrew.sdk.infrastructure.weixin.WeiXin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractOpenaiCodeReviewService implements IOpenAiCodeReviewService {

    private final Logger logger = LoggerFactory.getLogger(AbstractOpenaiCodeReviewService.class);

    protected final GitCommand gitCommand;
    protected final IOpenAI openAI;
    protected final WeiXin weiXin;

    public AbstractOpenaiCodeReviewService(GitCommand gitCommand, IOpenAI iOpenAI, WeiXin weiXin) {
        this.gitCommand = gitCommand;
        this.openAI = iOpenAI;
        this.weiXin = weiXin;
    }

    @Override
    public void exec() {
        try {
            // 获取提交代码
            String diffCode = gitDiffCode();
            // 评审代码
            String recommend = codeReview(diffCode);
            // github记录评审结果：返回日志地址
            String logUrl = recordCodeReview(recommend);
            // 发送消息通知：日志地址、通知内容
            pushMessage(logUrl);
        } catch (Exception e) {
            logger.error("openai-code-review error", e);
        }
    }

    protected abstract String gitDiffCode() throws Exception;

    protected abstract String codeReview(String diffCode) throws Exception;

    protected abstract String recordCodeReview(String recommend) throws Exception;

    protected abstract void pushMessage(String logUrl) throws Exception;
}

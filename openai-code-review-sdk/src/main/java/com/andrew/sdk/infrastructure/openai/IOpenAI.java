package com.andrew.sdk.infrastructure.openai;

import com.andrew.sdk.infrastructure.openai.dto.ChatCompletionRequestDTO;
import com.andrew.sdk.infrastructure.openai.dto.ChatCompletionSyncResponseDTO;

public interface IOpenAI {

    ChatCompletionSyncResponseDTO completions(ChatCompletionRequestDTO requestDTO) throws Exception;
}

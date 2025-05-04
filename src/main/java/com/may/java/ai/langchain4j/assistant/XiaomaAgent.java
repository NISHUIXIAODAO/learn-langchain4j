package com.may.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/***
 * 配置Agent：
 *  流式输出ChatModel
 *  聊天记忆
 *  自定义工具
 *  向量存储Pinecone
 *
 *  系统提示词
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoma",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaomaPincone"
)
public interface XiaomaAgent {
    @SystemMessage(fromResource = "xiaoma-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}

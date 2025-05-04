package com.may.java.ai.langchain4j.controller;

import com.may.java.ai.langchain4j.assistant.XiaomaAgent;
import com.may.java.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Tag(name = "小马")
@RestController
@RequestMapping("/xiaoma")
public class XiaomaController {
    @Autowired
    private XiaomaAgent xiaomaAgent;

    @Operation(summary = "对话")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return xiaomaAgent.chat(chatForm.getMemoryId(),chatForm.getMessage());
    }
}

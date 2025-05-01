package com.may.java.ai.langchain4j.controller;

import com.may.java.ai.langchain4j.assistant.XiaomaAgent;
import com.may.java.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "小马")
@RestController
@RequestMapping("/xiaoma")
public class XiaomaController {
    @Autowired
    private XiaomaAgent xiaomaAgent;

    @Operation(summary = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm) {
        return xiaomaAgent.chat(chatForm.getMemoryId(),chatForm.getMessage());
    }
}

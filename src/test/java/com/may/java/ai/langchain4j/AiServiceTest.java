package com.may.java.ai.langchain4j;

import com.may.java.ai.langchain4j.assistant.Assistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = XiaoMiaoApp.class)
public class AiServiceTest {
    @Autowired
    private Assistant assistant;
    @Test
    public void testChat() {
        String answer = assistant.chat("你是谁？");
        System.out.println(answer);
    }
}

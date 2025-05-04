# AiService

* 组装assistant接口以及其他组件（chatModel， chatMemoryProvider， tools等）

* 利用反射机制创建一个实现Assistant接口的代理对象bean，通过这个bean处理所有输入输出的转换工作（输入字符串转换为UserMessage，调用聊天语言模型，输出AiMessage转换为字符串）

## ChatMemory实现聊天记忆

* 配置类生成`chatMemoryProvider`bean，添加到AiService中

```java
@Bean
public ChatMemoryProvider chatMemoryProviderXiaoma() {
    return memoryId ->
            MessageWindowChatMemory.builder()
                    .id(memoryId)
                    .maxMessages(20)
                    .chatMemoryStore(mongoChatMemoryStore)
                    .build();

}
```

## Prompt 提示词

* @SystemMessage 系统提示词 添加到chat方法上

  ```java
  //@SystemMessage("你是我的好朋友，请用东北话回答问题。")//系统消息提示词
  @SystemMessage(fromResource = "prompt-template.txt")//系统消息提示词
  String chat(@MemoryId int memoryId, @UserMessage String userMessage);
  ```

* @UserMessage 用户消息 添加到chat方法上

## Function Calling 函数调用 也叫 Tools工具

* 方法上添加Tools注解

* AiService配置tools

* 两个字段：name（工具名称，默认为方法名），value（工具描述）

* @P 注解 标记方法参数，两个字段：value，required

* @ToolMemoryId 注解 区分多个用户，一个用户的多个聊天记忆

```java
@Tool(name = "加法", value = "返回两个参数相加之和")
double sum(
        @ToolMemoryId int memoryId,
        @P(value="加数1", required = true) double a,
        @P(value="加数2", required = true) double b) {
    System.out.println("调用加法运算 " + memoryId);
    return a + b;
}
```

# RAG：检索增强生成

常用方法：全文搜索，向量搜索（语义搜索），混合搜索

## 向量搜索 vector search

向量

维度

相似度：方向和长度&#x20;

## 过程：

1. 索引阶段

   加载知识库文档 -> 文本分段 -> 利用向量大模型将文本段转换为向量 -> 将文本与向量存入向量数据库

2. 检索阶段

将用户查询转为向量 -> 在向量数据库中进行相似度匹配 -> 将用户查询和向量数据库中匹配到的相关内容一起交给LLM处理

### 为什么文本分段？

1. 向量模型一次处理有限；

2. 大语言模型的上下文窗口有限

3. 信息越多LLM处理时间越长，花费的资源越多

4. 提问中会有无关信息可能会干扰LLM，增加产生幻觉的几率

## 文档加载器 loader

```java
PathMatcher pathMatcher = FileSystems.getDefault()
        .getPathMatcher("glob:*.txt");
List<Document> documents = FileSystemDocumentLoader
        .loadDocuments("E:/AA", pathMatcher, new TextDocumentParser());// 文档解析器
```

## 文档解析器 parser

```java
Document document = FileSystemDocumentLoader
        .loadDocument("E:/AA/test.pdf", new ApachePdfBoxDocumentParser());
```

## 文档分割器 splitter

DocumentSplitter：段，行，句，单词，字符，正则，递归&#x20;

每个文本片段最多不能超过300个token

### 向量转换和向量存储

Embedding Stores 嵌入存储

## 基于向量数据库Pinecone的向量存储

1. 配置向量存储Bean`EmbeddingStore`

2. 配置基于Pinecone的向量数据库Bean`contentRetrieverPinecone` ，用于从嵌入存储中检索内容

   参数：

3. 加载到@AiService&#x20;







智能体：组合大模型，聊天记忆等 todo


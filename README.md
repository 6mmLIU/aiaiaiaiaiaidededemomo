# AI-Demo Project

这是一个演示项目，展示了如何利用 Spring AI 框架与不同的大型语言模型（LLM）进行集成。项目中包含了两个独立的模块，分别演示了如何与阿里巴巴的 **Dashscope (通义千问)** 和 **DeepSeek (深度求索)** 模型进行交互。

## 项目结构

```
AI-Demo/
├── dashscope-demo/      # 与 Dashscope 模型集成的 Spring Boot 项目
├── deepseek-demo/       # 与 DeepSeek 模型集成的 Spring Boot 项目
└── pom.xml              # 父项目 POM 文件
```

## 核心功能

  * **Dashscope 集成**: `dashscope-demo` 模块提供了一个 RESTful API 端点，可以将用户的输入消息发送给 Dashscope 模型，并返回模型的响应。
  * **DeepSeek 集成**: `deepseek-demo` 模块同样提供了一个 API 端点，用于和 DeepSeek 模型进行对话。
  * **Spring AI**: 项目基于 Spring AI 构建，简化了与大语言模型交互的流程，例如构造 Prompt、调用模型和解析返回结果。
  * **配置灵活**: 每个模块的 AI 模型 key 和其他参数都在 `application.yml` 文件中配置，方便切换和管理。

## 技术栈

  * **Java 17**
  * **Spring Boot 3.x**
  * **Maven**
  * **Spring AI**
  * **Spring Web & WebFlux**
  * **Lombok**

## 快速开始

### 运行环境要求

  * JDK 17 或更高版本
  * Apache Maven

### 步骤

1.  **克隆项目**

    ```bash
    git clone <your-repository-url>
    cd AI-Demo
    ```

2.  **配置 API Key**

    在运行项目之前，你需要配置模型的 API Key。

      * 对于 **Dashscope** (`dashscope-demo`):
        打开 `AI-Demo/dashscope-demo/src/main/resources/application.yml` 文件，将 `api-key` 替换为您自己的 Key。

        ```yaml
        spring:
          ai:
            dashscope:
              api-key: sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx # 替换成你的 Key
              # ...
        ```

      * 对于 **DeepSeek** (`deepseek-demo`):
        打开 `AI-Demo/deepseek-demo/src/main/resources/application.yml` 文件，将 `api-key` 替换为您自己的 Key，并可以根据需要修改 `model`。

        ```yaml
        spring:
          ai:
            dashscope:
              api-key: sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx # 替换成你的 Key
              chat:
                options:
                  model: deepseek-r1 # 模型名称
                  # ...
        ```

3.  **启动应用**

    你可以选择启动其中一个或两个演示应用。在对应的模块目录下运行以下命令：

      * 启动 **Dashscope Demo**:

        ```bash
        cd dashscope-demo
        ./mvnw spring-boot:run
        ```

        服务将默认在 `8080` 端口启动。

      * 启动 **DeepSeek Demo**:

        ```bash
        cd deepseek-demo
        ./mvnw spring-boot:run
        ```

        根据 `application.yml` 配置，服务将默认在 `8081` 端口启动。

## API 端点

  * **Dashscope Demo**:

      * **GET** `/helloAI`
      * **示例**:
        ```bash
        curl "http://localhost:8080/helloAI?msg=你好，请介绍一下你自己"
        ```

  * **DeepSeek Demo**:

      * **GET** `/hello`
      * **示例**:
        ```bash
        curl "http://localhost:8081/hello?msg=你好，请用java写一个快速排序算法"
        ```

希望这份 README 文件对您有帮助！您可以直接将以上内容复制并保存为项目根目录下的 `README.md` 文件。

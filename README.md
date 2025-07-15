# Face Detection API Java

Uma API REST em Java para detecção de rostos em imagens, projetada como uma camada intermediária (middleware) que se comunica com um microserviço Python para processamento de imagens e retorna as anotações visuais dos rostos detectados.

![Java](https://img.shields.io/badge/java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/spring_boot-3.3.5-green.svg)
![Spring Cloud](https://img.shields.io/badge/spring_cloud-2023.0.3-green.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## Descrição

Este projeto é uma API REST construída com Spring Boot que serve como um middleware entre aplicações cliente e um microserviço de detecção de faces em uma imagem. A API recebe imagens via requisições HTTP, encaminha-as para processamento no serviço de detecção facial e retorna as imagens processadas com retângulos ao redor dos rostos identificados.

## Funcionalidades

-  Encaminhamento de imagens para processamento de detecção facial
-  Tratamento de múltiplos formatos de imagem
-  Conversão automática de imagens para base64
-  Retorno de imagens processadas com metadados
-  Exposição de informações de detecção via cabeçalhos HTTP personalizados
-  Tratamento de erros robusto com respostas apropriadas
-  Arquitetura limpa usando padrão MVC e DTO

##  Tecnologias Utilizadas

- **[Java 17](https://www.oracle.com/java/)** - Linguagem principal
- **[Spring Boot 3.3.5](https://spring.io/projects/spring-boot)** - Framework web
- **[Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)** - Cliente HTTP declarativo
- **[Maven](https://maven.apache.org/)** - Gerenciamento de dependências e build
- **[DTO Pattern](https://martinfowler.com/eaaCatalog/dataTransferObject.html)** - Padrão para transferência de dados

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Serviço Python de detecção facial em execução (porta 5000)

## Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/face-detection-api.git
   cd face-detection-api
   ```

2. **Compile o projeto**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`

## Estrutura do Projeto

```
face-detection-api/
├── src/
│   ├── main/
│   │   ├── java/com/project/portfolio/facedetectionapi/
│   │   │   ├── client/
│   │   │   │   └── FaceRecognitionApiClient.java    # Cliente Feign para o serviço Python
│   │   │   ├── controller/
│   │   │   │   └── FaceDetectionController.java     # Controlador REST
│   │   │   ├── dto/
│   │   │   │   └── FaceDetectionResponse.java       # Objeto de transferência de dados
│   │   │   ├── service/
│   │   │   │   └── FaceDetectionService.java        # Lógica de negócio
│   │   │   └── FaceDetectionApiApplication.java     # Ponto de entrada da aplicação
│   │   └── resources/
│   │       └── application.properties               # Configurações da aplicação
│   └── test/                                        # Testes unitários e de integração
├── pom.xml                                          # Configuração do Maven
└── README.md                                        # Este arquivo
```

## Como Usar

### Endpoint Principal

**POST** `/api/faces/detect`

### Requisição

A requisição deve ser enviada como `multipart/form-data` com o seguinte campo:

- `image`: Arquivo de imagem a ser processado

### Resposta de Sucesso

A resposta é a imagem processada em formato JPEG com os seguintes cabeçalhos personalizados:

- `X-Faces-Count`: Número de rostos detectados
- `X-Success`: Indicador de sucesso do processamento (true/false)
- `X-Format`: Formato da imagem processada (geralmente "jpeg")

### Exemplo com cURL

```bash
curl -X POST http://localhost:8080/api/faces/detect \
  -H "Content-Type: multipart/form-data" \
  -F "image=@caminho/para/sua/imagem.jpg" \
  -o imagem_processada.jpg
```


##️ Configuração

A aplicação está configurada para conectar-se ao serviço Python de detecção facial na URL `http://localhost:5000`.

Para alterar essa configuração, modifique a anotação `@FeignClient` no arquivo `FaceRecognitionApiClient.java`.

## Tratamento de Erros

A API retorna os seguintes códigos de erro:

- **400 Bad Request**: Quando a imagem está ausente ou não é válida
- **500 Internal Server Error**: Quando ocorre um erro no processamento da imagem

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

- **Carlos Alfredo Oliveira de Lima** - [GitHub](https://github.com/CarlosAlfredoOliveiraDeLima)

## Integração

Este projeto foi projetado para trabalhar em conjunto com um microserviço Python de detecção facial. Certifique-se de que o serviço Python esteja em execução na porta 5000 antes de iniciar esta API. - 
[Aplicação Python/Flask](https://github.com/CarlosAlfredoOliveiraDeLima/face-detection-core-python)
---

 **Gostou do projeto? Deixe uma estrela!** 
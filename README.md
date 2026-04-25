# Chat LAN com Spring Boot

Aplicacao de chat em tempo real usando Spring Boot + WebSocket/STOMP.

Fluxo:

- Tela inicial para informar o nome
- Redirecionamento automatico para a sala do chat

## Como funciona

Um computador da rede executa o servidor Spring Boot.
Os outros computadores acessam o navegador apontando para o IP dessa maquina.

Exemplo:

- Maquina servidora: `192.168.0.15`
- URL para todos os clientes: `http://192.168.0.15:8080`

## Requisitos

- Java 21 ou superior
- Maven 3.9+ para rodar por linha de comando

## Executar

### IntelliJ

Rode a classe `com.example.lanchat.LanChatApplication`.

### Terminal

```bash
mvn spring-boot:run
```

## Testar entre dois computadores

1. Descubra o IP da maquina que vai hospedar o servidor.
2. Inicie a aplicacao nessa maquina.
3. Libere a porta `8080` no firewall, se necessario.
4. Nos dois computadores, abra `http://IP_DA_MAQUINA:8080`.
5. Informe nomes diferentes e envie mensagens.

## Endpoints

- Pagina web: `/`
- Sala do chat: `/chat.html`
- WebSocket/SockJS: `/chat`
- Destino de envio: `/app/send`
- Topico de recebimento: `/topic/messages`

# Chat com Spring Boot

Aplicação de chat em tempo real usando Spring Boot + WebSocket/STOMP.

Fluxo:

- Tela inicial para informar o nome
- Redirecionamento automático para a sala do chat
- Carregamento do histórico recente ao entrar na conversa

## Como funciona

Um computador da rede executa o servidor Spring Boot.
Os outros computadores acessam o navegador apontando para o IP desta máquina.

Exemplo:

- Máquina servidora: `192.168.0.15`
- URL para todos os clientes: `http://192.168.0.15:8080`

## Requisitos

- Java 21 ou superior
- Maven 3.9+ para rodar por linha de comando

## Executar

### IntelliJ

Rode a classe `com.example.lanchat.ChatApplication`.

### Terminal

```bash
mvn spring-boot:run
```

## Testar entre dois computadores

1. Descubra o IP da máquina que vai hospedar o servidor.
2. Inicie a aplicação nessa máquina.
3. Libere a porta `8080` no firewall, se necessário.
4. Nos dois computadores, abra `http://IP_DA_MAQUINA:8080`.
5. Informe nomes diferentes e envie mensagens.

## Histórico de conversas

O servidor mantém em memória as últimas 100 mensagens enviadas.
Quando um usuário entra na sala, a página carrega esse histórico antes de receber novas mensagens em tempo real.

Como o histórico fica em memória, ele é apagado quando a aplicação é reiniciada.

## Endpoints

- Página web: `/`
- Sala do chat: `/chat.html`
- Histórico de mensagens: `/api/messages`
- WebSocket/SockJS: `/chat`
- Destino de envio: `/app/send`
- Tópico de recebimento: `/topic/messages`

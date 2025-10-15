# Instruções para Executar a Aplicação Bancária

## Pré-requisitos

- Java 17 ou superior instalado
- Terminal (Linux, macOS ou Windows)

## Compilando o Projeto

1. Navegue até a pasta raiz do projeto:
   ```sh
   cd bank-app-java/bank-app
   ```
2. Compile os arquivos Java:
   ```sh
   javac -d bin src/**/*.java
   ```

## Executando a Aplicação

1. No terminal, execute o comando:
   ```sh
   java -cp bin App
   ```

2. O menu principal será exibido. Siga as instruções na tela para utilizar as funcionalidades:
   - Cadastrar conta
   - Depositar
   - Sacar
   - Transferir
   - Alterar limite
   - Consultar saldo
   - Ver histórico
   - Exportar histórico (CSV)
   - Listar contas

## Observações

- Os dados são mantidos apenas em memória durante a execução.
- O histórico pode ser exportado para um arquivo CSV.
- Para dúvidas ou problemas, consulte o código-fonte ou abra uma issue no repositório.

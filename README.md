# Teste End-to-End para a aplicação AppFit 🏋️

Este repositório contém testes end-to-end implementados usando Cucumber para validar o comportamento do sistema AppFit.

## Executando os Testes

1. Clone o repositório e entre na pasta gerada:
```
git clone https://github.com/seu-usuario/AppFit-tests.git
cd AppFit-tests
```
 2. Garanta que a [aplicação principal](https://github.com/julianaando/AppFit) esteja em execução antes de prosseguir com os testes.

 3. Inicie a execução dos testes através do comando:
```
mvn clean test
```

## Estrutura do Projeto

Explicação breve da estrutura do projeto:

- **src/test/resources/feature:** Contém os arquivos de feature escritos em Gherkin.
- **src/test/java:** Contém os arquivos de código-fonte Java para os passos de teste e configurações.

## Cenários de Teste

Os seguintes cenários de teste foram implementados:

### Cenário 1: Exercise linked to a User

```gherkin
Scenario: Exercise linked to a User
  Given Exercise and User are known
  When Activities are registered successfully
  Then User should have the Exercise registered
```
### Cenário 2: Failure to register activities for an unknown User 

```gherkin
Scenario: Failure to register activities for an unknown User
  Given the Exercise is known
  And the User is unknown
  When Activities are registered successfully
  Then the registration should fail
  And an error message should be displayed
```


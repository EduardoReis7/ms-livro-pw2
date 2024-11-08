# Plano de Teste

## 1. Ferramentas
As seguintes ferramentas serão utilizadas para o desenvolvimento e execução dos testes e verificação de qualidade:

- **JUnit**: Framework para criação de testes unitários em Java (https://junit.org/junit5/).
- **Mockito**: Biblioteca para criação de mocks e simulações em testes unitários (https://site.mockito.org/).
- **Rest Assured**: Biblioteca para testes de APIs RESTful em Java (https://rest-assured.io/).
- **SonarLint**: Ferramenta de análise estática para detectar problemas no código durante o desenvolvimento.
- **SonarCloud**: Serviço para integração contínua e análise de qualidade do código (https://sonarcloud.io/login).

## 2. Procedimentos

### 2.1 Commit
- Realize commits frequentes e bem documentados, descrevendo as alterações e testes adicionados.
- Exemplo de comando: `git commit -m "Implementação de testes unitários para [funcionalidade]"`

### 2.2 Pull Request
- Crie um Pull Request para cada conjunto de alterações.
- Inclua uma descrição detalhada do que foi alterado, testes adicionados e a verificação de qualidade feita com SonarLint.
- Todos os PRs devem ser revisados por pelo menos um membro da equipe antes de serem mesclados.

### 2.3 Revisão de Código
- Os PRs devem passar pela revisão de código, verificando especialmente a cobertura de testes e conformidade com os padrões de qualidade.
- Valide o uso do SonarLint e a análise do SonarCloud para manter a qualidade do código.

## 3. Requisitos, Restrições e Configurações para o Teste

### 3.1 Requisitos
- **Cobertura de Código**: A cobertura dos testes unitários e de integração deve ser de no mínimo 80%.
- **Mocks**: Utilize Mockito para mockar dependências internas durante os testes unitários.
- **APIs REST**: Utilize Rest Assured para validar as respostas e o comportamento das APIs RESTful da aplicação.

### 3.2 Restrições
- Todos os testes devem ser executados em um ambiente de desenvolvimento ou teste, separado do ambiente de produção.
- O SonarCloud deve ser configurado como requisito para integração contínua.

### 3.3 Configurações
- **SonarLint**: Configure o SonarLint para análise em tempo real no ambiente de desenvolvimento.
- **SonarCloud**: Configure o SonarCloud como parte do pipeline de CI/CD para análise de qualidade do código em cada PR e push.

## 4. Matriz de Funcionalidades versus Testes

| Funcionalidade            | Teste Unitário | Teste de Integração (API) | Mock com Mockito | 
|---------------------------|----------------|---------------------------|------------------|
| Cadastro de livros        |      Sim       |            Não            |       Não        | 
| Busca de livros           |      Sim       |            Não            |       Não        | 
| Exclusão de livros        |      Não       |            Não            |       Não        | 
| Edição de livros          |      Não       |            Não            |       Não        | 

## 5. Verificação Estática
Implemente a verificação estática do código utilizando:

- **SonarLint**: Ferramenta local para verificar problemas de qualidade no código durante o desenvolvimento.
- **SonarCloud**: Configure o SonarCloud no pipeline de CI/CD para verificar a qualidade e cobertura de código em todos os commits e PRs.

## 6. Testes de API com Rest Assured
Utilize o **Rest Assured** para implementar testes de API, verificando:

- **Respostas de Status**: Assegure-se de que os endpoints estão respondendo com os status HTTP corretos (ex: 200, 404, 500).
- **Validação de Conteúdo**: Verifique se o conteúdo das respostas está de acordo com os requisitos.
- **Cabeçalhos e Autenticação**: Valide cabeçalhos essenciais e autenticação das requisições.

## 7. Integração Contínua
- Configure um ambiente de integração contínua (por exemplo, GitHub Actions) para rodar os testes e verificar a análise do SonarCloud em cada push e PR.
- **SonarCloud**: Configure o SonarCloud para gerar relatórios de análise de qualidade e cobertura de código.

---


# Plano de Teste

## 1. Ferramentas
As seguintes ferramentas serão utilizadas para desenvolvimento e execução de testes e verificação de código:

- **JUnit**: Framework para criação de testes unitários em Java.
- **Mockito**: Biblioteca para mocks em testes unitários.
- **SonarLint**: Ferramenta de análise estática para detectar problemas no código durante o desenvolvimento.
- **SonarCloud**: Serviço para integração contínua e análise de qualidade do código.

## 2. Procedimentos

### 2.1 Commit
- Realize commits frequentes e bem documentados.
- Exemplo de comando: `git commit -m "Implementação de testes unitários para [funcionalidade]"`

### 2.2 Pull Request
- Crie um Pull Request para cada conjunto de alterações que inclui testes e verificação estática.
- Inclua uma descrição detalhada do que foi alterado e dos testes realizados.
- Todos os PRs devem ser revisados e aprovados por pelo menos um membro da equipe antes de serem mesclados.

### 2.3 Revisão de Código
- Todos os PRs serão revisados para garantir que os padrões de código estejam sendo seguidos, com atenção especial aos problemas apontados pelo SonarLint e SonarCloud.

## 3. Requisitos, Restrições e Configurações para o Teste

### 3.1 Requisitos
- **Cobertura de Código**: A cobertura de testes deve ser no mínimo de 80%.
- **Análise Estática**: Todo código deve passar por verificação com SonarLint.
- **Mocks**: Use Mockito para mockar serviços e dependências em testes unitários.

### 3.2 Restrições
- As ferramentas de verificação estática devem estar configuradas no ambiente local para garantir conformidade antes do commit.
- O SonarCloud será configurado como requisito para a integração contínua e análise de código.

### 3.3 Configurações
- **SonarLint**: Configure o SonarLint no editor de código para análise em tempo real.
- **SonarCloud e GitHub Actions**: Configure o SonarCloud para análise de qualidade e GitHub Actions para execução dos testes e análise em todos os PRs.

## 4. Matriz de Funcionalidades versus Testes

| Funcionalidade            | Teste Unitário | Teste de Integração | Mock com Mockito | Observações                         |
|---------------------------|----------------|----------------------|------------------|-------------------------------------|
| Autenticação               | Sim           | Sim                 | Sim              | Verificar login e autenticação      |
| Cadastro de Usuários       | Sim           | Sim                 | Sim              | Mock de validação externa           |
| Processamento de Dados     | Sim           | Não                 | Não              | Testes locais dos métodos           |
| API (Componentes)          | Sim           | Sim                 | Sim              | Testes de componentes com APIs      |

## 5. Verificação Estática
Implemente a verificação estática do código utilizando as seguintes ferramentas:

- **SonarLint**: Ferramenta local para verificar problemas de qualidade do código.
- **SonarCloud**: Configure o SonarCloud para verificar a qualidade do código durante a CI/CD e gerar relatórios de análise.

## 6. Testes de Componentes e de Sistema
- Para os testes de componentes, utilize o JUnit e Mockito para garantir o comportamento esperado dos módulos principais.
- Para testes de sistema ou testes de API, utilize **Selenium** (ou **Cypress**) para simular interações com o sistema de maneira automatizada.

## 7. Integração Contínua
- Configure o GitHub Actions para rodar os testes de unidade e verificação estática (Checkstyle, PMD e SonarCloud) em cada push e PR.
- **SonarCloud**: Configure para integração automática no pipeline do GitHub Actions para garantir que todos os critérios de qualidade sejam atendidos antes da mesclagem.

---


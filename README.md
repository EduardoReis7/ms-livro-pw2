# Plano de Teste

## 1. Ferramentas

As seguintes ferramentas serão utilizadas para o desenvolvimento e execução dos testes e verificação de qualidade:

- **JUnit**: Framework para criação de testes unitários em Java ([link](https://junit.org/junit5/)).
- **Mockito**: Biblioteca para criação de mocks e simulações em testes unitários ([link](https://site.mockito.org/)).
- **Rest Assured**: Biblioteca para testes de APIs RESTful em Java ([link](https://rest-assured.io/)).
- **SonarLint**: Ferramenta de análise estática para detectar problemas no código durante o desenvolvimento.
- **SonarCloud**: Serviço para integração contínua e análise de qualidade do código ([link](https://sonarcloud.io/login)).

## 2. Procedimentos

### 2.1 Commit

Realize commits frequentes e bem documentados, descrevendo as alterações e testes adicionados.

**Exemplo de comando**: `git commit -m "Implementação de testes unitários para [funcionalidade]"`

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

| Funcionalidade      | Teste Unitário | Teste de Integração (API)  | Mock com Mockito |
|---------------------|----------------|----------------------------|-------------------|
| Cadastro de livros  | Sim            | Sim                        | Sim               |
| Busca de livros     | Sim            | Sim                        | Sim               |
| Exclusão de livros  | Sim            | Sim                        | Sim               |
| Edição de livros    | Sim            | Sim                        | Sim               |

## 5. Verificação Estática

Implemente a verificação estática do código utilizando:

- **SonarLint**: Ferramenta local para verificar problemas de qualidade no código durante o desenvolvimento.
- **SonarCloud**: Configure o SonarCloud no pipeline de CI/CD para verificar a qualidade e cobertura de código em todos os commits e PRs.

## 6. Testes de API com Rest Assured

Utilize o Rest Assured para implementar testes de API, verificando:

- **Respostas de Status**: Assegure-se de que os endpoints estão respondendo com os status HTTP corretos (ex: 200, 404, 500).
- **Validação de Conteúdo**: Verifique se o conteúdo das respostas está de acordo com os requisitos.
- **Cabeçalhos e Autenticação**: Valide cabeçalhos essenciais e autenticação das requisições.

## 7. Integração Contínua

Configure um ambiente de integração contínua (por exemplo, GitHub Actions) para rodar os testes e verificar a análise do SonarCloud em cada push e PR.

- **SonarCloud**: Configure o SonarCloud para gerar relatórios de análise de qualidade e cobertura de código.

## 8. Casos de Teste 

### 8.1 Teste de Criação de Livro [UNITÁRIO]

- **Método**: `shouldReturnTrueWhenANewLivroIsCreated`
- **Descrição**: Verifica se um novo livro pode ser salvo corretamente no repositório.
- **Entradas**: Objeto Livro criado através do `TestUtils.createLivroEntity()`.
- **Resultado Esperado**: O livro deve ser salvo com sucesso, e o objeto retornado deve ser equivalente ao esperado.

### 8.2 Teste de Busca de Todos os Livros [UNITÁRIO]

- **Método**: `shouldReturnTrueWhenAListOfLivroIsSearched`
- **Descrição**: Verifica se todos os livros podem ser buscados corretamente.
- **Entradas**: Nenhuma.
- **Resultado Esperado**: A lista retornada deve conter todos os livros salvos, com os valores corretos.

### 8.3 Teste de Busca de Livro por ID [UNITÁRIO]

- **Método**: `shouldReturnTrueWhenALivroIsSearched`
- **Descrição**: Verifica se um livro específico pode ser buscado pelo ID.
- **Entradas**: ID do livro.
- **Resultado Esperado**: O livro correspondente ao ID deve ser retornado corretamente.

### 8.4 Teste de Edição de Livro [UNITÁRIO]

- **Método**: `shouldReturnTrueWhenALivroIsEdited`
- **Descrição**: Verifica se um livro existente pode ser editado corretamente.
- **Entradas**: Objeto Livro com alterações.
- **Resultado Esperado**: O livro deve ser editado com sucesso e refletir as alterações feitas.

### 8.5 Teste de Exclusão de Livro [UNITÁRIO]

- **Método**: `shouldReturnTrueWhenALivroIsDeleted`
- **Descrição**: Verifica se um livro pode ser excluído e não está mais disponível para busca.
- **Entradas**: ID do livro.
- **Resultado Esperado**: O livro deve ser excluído com sucesso, e uma tentativa de buscá-lo deve lançar a exceção `NaoEncontradoException`.

### 8.6 Teste de Salvamento de Livro [UNITÁRIO]

- **Método**: `shouldSaveBook`
- **Descrição**: Verifica se um livro pode ser salvo no repositório corretamente.
- **Entradas**: Objeto Livro criado através do `TestUtils.createLivroEntity()`.
- **Resultado Esperado**: O livro deve ser salvo com sucesso, e o ID e título devem corresponder ao esperado.

### 8.7 Teste de Busca de Todos os Livros com Títulos Diferentes [UNITÁRIO]

- **Método**: `shouldFindAllBooks`
- **Descrição**: Verifica se todos os livros com títulos diferentes são buscados corretamente.
- **Entradas**: Nenhuma.
- **Resultado Esperado**: A lista retornada deve conter todos os livros salvos, com os valores corretos de ID e título.

### 8.8 Teste de Busca de Livro Específico por ID [UNITÁRIO]

- **Método**: `shouldFindBookById`
- **Descrição**: Verifica se um livro específico pode ser buscado pelo ID corretamente.
- **Entradas**: ID do livro.
- **Resultado Esperado**: O livro correspondente ao ID deve ser retornado corretamente, com os valores esperados.

### 8.9 Teste de Edição de Livro Específico [UNITÁRIO]

- **Método**: `shouldEditBook`
- **Descrição**: Verifica se um livro existente pode ser editado com um novo título.
- **Entradas**: Objeto Livro com alterações no título.
- **Resultado Esperado**: O livro deve ser editado com sucesso, refletindo o novo título.

### 8.10 Teste de Exclusão de Livro [UNITÁRIO]

- **Método**: `shouldDeleteBook`
- **Descrição**: Verifica se um livro pode ser excluído do repositório corretamente.
- **Entradas**: ID do livro.
- **Resultado Esperado**: O livro deve ser excluído com sucesso sem lançar exceções.

- **Método**: `shouldReturn200WhenSavingLivro` [INTEGRAÇÃO]
- **Descrição**: Verifica se um novo livro pode ser salvo corretamente no sistema.
- **Entradas**: Objeto JSON representando o livro (título, autor, data de lançamento).
- **Resultado Esperado**: Status code 200 e o título do livro salvo deve ser "Livro Teste".

### 8.11 Teste de Busca de Todos os Livros [INTEGRAÇÃO]

- **Método**: `shouldReturn200WhenGettingAllBooks`
- **Descrição**: Verifica se todos os livros podem ser buscados corretamente.
- **Entradas**: Nenhuma.
- **Resultado Esperado**: Status code 200 e a lista de livros deve conter pelo menos um item.

### 8.12 Teste de Busca de Livro por ID [INTEGRAÇÃO]

- **Método**: `shouldReturn200WhenGettingBookById`
- **Descrição**: Verifica se um livro específico pode ser buscado pelo ID.
- **Entradas**: ID do livro.
- **Resultado Esperado**: Status code 200 e o ID do livro deve corresponder ao esperado.

### 8.13 Teste de Edição de Livro [INTEGRAÇÃO]

- **Método**: `shouldReturn200WhenUpdatingBook`
- **Descrição**: Verifica se um livro existente pode ser editado corretamente.
- **Entradas**: ID do livro e objeto JSON com os novos dados do livro (título, autor, data de lançamento).
- **Resultado Esperado**: Status code 200 e o título do livro deve ser atualizado para "Livro Atualizado".

### 8.14 Teste de Exclusão de Livro [INTEGRAÇÃO]

- **Método**: `shouldReturn204WhenDeletingBook`
- **Descrição**: Verifica se um livro pode ser excluído do sistema corretamente.
- **Entradas**: ID do livro.
- **Resultado Esperado**: Status code 204, indicando que o livro foi excluído com sucesso.

### 8.15 Teste de Autorização Inválida [INTEGRAÇÃO]

- **Método**: `shouldReturn401WhenUnauthorized`
- **Descrição**: Verifica se a tentativa de salvar um livro sem um token válido retorna erro de autorização.
- **Entradas**: Objeto JSON representando o livro (título, autor, data de lançamento) sem o cabeçalho de autorização.
- **Resultado Esperado**: Status code 401, indicando que a requisição não foi autorizada.
- 
## Você fez todos os testes???

![Descrição do GIF](https://media.giphy.com/media/ynRrAHj5SWAu8RA002/giphy.gif)

# 🛒 UMarket - Sistema de Gestão de Vendas e Estoque

O **UMarket** é uma aplicação Java Console desenvolvida para a disciplina de **Banco de Dados II**. O sistema utiliza a arquitetura **MVC** (Model-View-Controller) e **JDBC** para gerenciar uma operação de varejo, desde o cadastro básico até o fluxo complexo de vendas com baixa automática de estoque e relatórios gerenciais.

## 🛠️ Tecnologias Utilizadas
* **Java SDK 17+**
* **PostgreSQL** (Banco de Dados Relacional)
* **JDBC** (Java Database Connectivity)
* **StandardCharsets.UTF_8** (Suporte a caracteres especiais no console)

---

## 🚀 Como Executar

1.  **Configuração do Banco**:
    * Certifique-se de que o seu banco de dados está ativo.
    * Execute o script de criação de tabelas e o dump de dados (Seed) para popular o sistema.
2.  **Configuração da Conexão**:
    * Verifique a classe `Conexao.java` e insira sua `URL`, `User` e `Password` do banco.
3.  **Execução**:
    * Compile e execute a classe `Principal.java`. 
    * O console está configurado para UTF-8, garantindo a exibição correta de acentos.

---

## 📖 Guia de Navegação (Passo a Passo)

A interface é baseada em menus numéricos simples e intuitivos. Sempre que terminar uma ação, o sistema pedirá um **ENTER** para limpar a tela e retornar ao menu anterior.

### 1. Menu Principal
* **1 - Listar registros**: Visualiza tabelas (Usuários, Produtos, PDVs, Estoque, Vendas).
* **2 - Adicionar registros**: Onde ocorre a criação de novos cadastros e vendas.
* **3 - Remover registros**: Exclusão por ID com validação de integridade.
* **4 - Atualizar registros**: Edição de dados existentes.
* **5 - Exibir relatórios**: Métricas de desempenho do negócio.
* **6 - Encerrar Aplicação**: Fecha as conexões de forma segura.

### 2. Fluxo de Realização de Venda
Para testar o processo de venda multi-itens:
1.  Vá em `2 - Adicionar registros` -> `5 - Itens Venda`.
2.  Informe os dados do **Cabeçalho** (ID do Usuário e ID do PDV).
3.  No loop de itens, escolha o **Produto** e a **Quantidade**.
    * *Validação*: O sistema impede a venda se a quantidade desejada for maior que o estoque atual ou se o produto não existir.
4.  Escolha se deseja adicionar mais itens ou finalizar.
5.  Ao finalizar, o valor total da venda é atualizado automaticamente no banco.

### 3. Relatórios Gerenciais
Acesse a opção `5` no menu principal para visualizar:
* **Top 5 Produtos**: Ranking dos itens mais vendidos por quantidade acumulada.
* **Faturamento por Dia**: Soma de vendas dos últimos 10 dias de operação.
* **Vendas por PDV**: Comparativo de faturamento entre as diferentes unidades físicas.

---

## 🛡️ Funcionalidades de Segurança (DBA)

* **Rollback Manual**: Em caso de qualquer erro durante a inserção de itens (como falta de estoque), a aplicação remove automaticamente o cabeçalho da venda criado, evitando registros "sujos" ou zerados no banco de dados.
* **Integridade Referencial**: O script SQL utiliza `ON DELETE CASCADE` de forma estratégica para garantir que a remoção de vendas limpe também seus respectivos itens.
* **Ordenação**: Todas as listagens utilizam `ORDER BY` via SQL para garantir uma visualização organizada por ID ou nome.

---

**Desenvolvido por:** Nathan Carlos  
**Contexto:** Projeto Acadêmico - UDESC Joinville (CCT)
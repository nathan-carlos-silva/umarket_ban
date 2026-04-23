🛒 UMarket - Sistema de Gestão de Vendas e Estoque
O UMarket é uma aplicação Java Console desenvolvida para a disciplina de Banco de Dados II. O sistema utiliza arquitetura MVC (Model-View-Controller) e JDBC para gerenciar usuários, produtos, PDVs, estoque e um fluxo completo de vendas com múltiplos itens e relatórios gerenciais.

🛠️ Tecnologias Utilizadas
Java SDK 17+

PostgreSQL (ou SQL Server)

JDBC (Java Database Connectivity)

Maven/NetBeans

🚀 Como Executar
Configuração do Banco:

Execute o script dump.sql fornecido para criar as tabelas e popular os dados iniciais.

Certifique-se de que a classe Conexao.java tenha as credenciais corretas do seu banco local.

Compilação:

Abra o projeto no NetBeans ou sua IDE de preferência.

Verifique se o driver JDBC está no classpath do projeto.

Execução:

Execute a classe Principal.java.

📖 Guia de Navegação (Passo a Passo)
A aplicação utiliza um sistema de menus numéricos. Para selecionar uma opção, digite o número correspondente e pressione ENTER.

1. Menu Principal
Ao iniciar, você terá 6 opções principais:

1 - Listar: Visualiza dados brutos de qualquer tabela.

2 - Adicionar: Cria novos registros (incluindo o fluxo de venda).

3 - Remover: Exclui registros por ID.

4 - Atualizar: Modifica dados existentes.

5 - Relatórios: Exibe métricas de negócio e performance.

6 - Encerrar: Fecha a conexão com o banco e sai da aplicação.

2. Fluxo de Venda (A parte mais importante!)
Para realizar uma venda, navegue em: 2 - Adicionar registros -> 5 - Itens Venda.

Cabeçalho: Informe o ID do Usuário, ID do PDV e a Forma de Pagamento.

Carrinho: O sistema listará os produtos. Escolha o ID e a quantidade.

Validação: O sistema verifica automaticamente se há estoque disponível no PDV escolhido.

Multi-itens: Após cada item, o sistema perguntará se deseja adicionar mais produtos à mesma venda.

Finalização: O valor total é calculado e o estoque é baixado automaticamente.

3. Visualizando Resultados
Estoque: Use 1 - Listar -> 4 - Estoque para ver a quantidade atualizada após uma venda.

Relatórios: Use 5 - Exibir relatórios para ver o Top 5 produtos mais vendidos ou o faturamento dos últimos 10 dias.

🛡️ Tratamento de Erros
Integridade: Se ocorrer um erro durante uma venda (ex: falta de estoque ou ID inexistente), o sistema realiza um Rollback Manual, deletando o cabeçalho da venda para não deixar dados órfãos no banco.

Codificação: A aplicação está configurada para UTF-8, garantindo que caracteres como ç e ã apareçam corretamente no console.
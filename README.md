# Arquitetura_Software-FEI

Repositório utilizado nas aulas de Arquitetura de Software e Programação Orientada a Objeto no ensino superior na FEI

# Relatório do Projeto FEItv

## Introdução

O projeto FEItv foi desenvolvido com o objetivo de criar uma plataforma de compartilhamento e gerenciamento de informações sobre vídeos, incluindo filmes e séries. A proposta do sistema é simular funcionalidades presentes em plataformas de streaming, como busca de conteúdos, curtidas, gerenciamento de favoritos e listas de reprodução.

O desenvolvimento do projeto foi realizado utilizando a linguagem Java, com interface gráfica construída em Swing, persistência de dados utilizando PostgreSQL e comunicação com o banco por meio de JDBC. Além disso, o sistema foi organizado seguindo o padrão arquitetural MVC (Model, View e Controller), permitindo uma melhor separação das responsabilidades do sistema.

---

# Tecnologias Utilizadas

Durante o desenvolvimento do projeto foram utilizadas as seguintes tecnologias:

* Java
* Swing
* PostgreSQL
* JDBC
* Arquitetura MVC
* Programação Orientada a Objetos

O Java foi utilizado como linguagem principal do sistema. O Swing foi responsável pela construção da interface gráfica, permitindo interação do usuário com o sistema. O PostgreSQL foi utilizado como banco de dados relacional para armazenamento permanente das informações.

A comunicação entre o sistema e o banco foi feita utilizando JDBC, possibilitando operações como inserção, consulta, atualização e remoção de dados.

---

# Estrutura do Projeto

O sistema foi organizado utilizando o padrão MVC.

## Model

A camada Model foi responsável por representar as entidades do sistema, como usuários, vídeos e listas de reprodução.

## View

A camada View foi responsável pela interface gráfica desenvolvida em Swing.

## Controller

A camada Controller foi utilizada para controlar as ações do sistema, realizando a comunicação entre a interface gráfica e o banco de dados.

## DAO

Também foi utilizada uma camada DAO (Data Access Object), responsável pelas operações de acesso ao banco de dados.

---

# Banco de Dados

O banco de dados foi desenvolvido no PostgreSQL com o objetivo de armazenar permanentemente as informações do sistema.

As tabelas implementadas foram:

## Tabela usuario

Responsável por armazenar os dados dos usuários cadastrados.

Campos:

* id
* nome
* email
* senha

A senha foi armazenada utilizando hash SHA-256 para aumentar a segurança dos dados.

## Tabela video

Responsável por armazenar as informações dos vídeos disponíveis no sistema.

Campos:

* id
* titulo
* descricao
* anoLancamento
* tipo
* duracao
* temporadas

A tabela suporta tanto filmes quanto séries.

## Tabela curtida

Responsável por registrar quais vídeos foram curtidos pelos usuários.

## Tabela favorito

Responsável por armazenar os vídeos favoritados pelos usuários.

## Tabela lista_reproducao

Responsável por armazenar as listas de reprodução criadas pelos usuários.

## Tabela lista_video

Responsável pela relação entre listas de reprodução e vídeos.

---

# Funcionalidades Implementadas

## Cadastro de Usuários

O sistema permite o cadastro de novos usuários através da interface gráfica. Durante o cadastro, os dados são enviados ao banco de dados e armazenados permanentemente.

## Login de Usuários

Foi implementado um sistema de autenticação utilizando email e senha.

O sistema realiza a validação das credenciais cadastradas antes de permitir o acesso às funcionalidades principais.

## Busca de Vídeos

Os usuários podem realizar buscas de vídeos utilizando o título do conteúdo.

As informações dos vídeos são carregadas diretamente do banco de dados e exibidas na interface gráfica.

## Curtir Vídeos

O sistema permite que usuários curtam vídeos.

As curtidas são registradas na tabela curtida, permitindo controle individual das interações de cada usuário.

## Favoritos

Foi implementado um sistema de favoritos, permitindo que usuários salvem vídeos de interesse.

## Listas de Reprodução

Os usuários podem criar listas de reprodução personalizadas e adicionar ou remover vídeos dessas listas.

---

# Programação Orientada a Objetos

Durante o desenvolvimento foram utilizados conceitos de Programação Orientada a Objetos.

Entre os principais conceitos aplicados estão:

* Encapsulamento
* Herança
* Abstração
* Organização em classes

As entidades do sistema foram representadas em classes específicas, facilitando a manutenção e organização do código.

---

# Persistência de Dados

Todas as informações do sistema foram armazenadas no banco PostgreSQL.

Dessa forma, os dados não são perdidos ao fechar o programa.

As operações de persistência foram realizadas utilizando JDBC e classes DAO.

---

# Interface Gráfica

A interface gráfica foi construída utilizando Swing.

Foram desenvolvidas telas para:

* Login
* Cadastro de usuário
* Busca de vídeos
* Gerenciamento de favoritos
* Gerenciamento de listas de reprodução

A interface foi desenvolvida com foco na simplicidade e facilidade de uso.

---

# Conclusão

O projeto FEItv permitiu aplicar diversos conceitos importantes de desenvolvimento de software, incluindo Programação Orientada a Objetos, persistência de dados, arquitetura MVC e integração entre Java e PostgreSQL.

Além disso, o projeto possibilitou o desenvolvimento de habilidades relacionadas à construção de interfaces gráficas, modelagem de banco de dados e organização estrutural de sistemas.

O sistema desenvolvido atende aos requisitos propostos, permitindo o gerenciamento de usuários, vídeos, curtidas, favoritos e listas de reprodução de forma funcional e organizada.

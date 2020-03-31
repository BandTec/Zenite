## Do Livro "Spring MVC Domine o principal framework web Java" da Casa do Código

* **DTO**
-> Data Transfer Object é apenas uma classe que realiza o transporte de dados entre diferentes componentes de um sistema.

* **DAO**
-> Data Acess Object é apenas uma classe cujo objetivo é isolar o acesso de dados de uma determinada parte do sistema.

### Significado das annotations / anotações
-> as anotações dizem ao framework Spring o que deve fazer, e também possuem significado semântico.

* **@Autowired** é responsável por indicar pontos de injeção dentro da classe, o Spring Boot sabe que nesse ponto necessita injetar a conexão do banco de dados.

* **@Repository** diz ao Spring que ele deve carregar a classe e gerenciar seu ciclo de vida, e também indicar que a classe é responsável pelo acesso de dados.

* **@Service** indica que a classe representa um componente intimamente ligado a uma regra de negócio do sistema.


## Annotations / Anotações do JPA

* **@Entity** - indica que a classe irá virar uma tabela

* **@Id** - indica que o atributo em questão é a chave primária.

* **@Bean** - indica que os objetos criados por eles vão ser gerenciados pelo Spring e podem ser injetados em qualquer ponto do código.

---

## LINKS

**Explicação dos códigos de status do HTTP**
-> https://httpstatuses.com/
-> https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status

**Repositório do Curso de Spring Boot da Udemy**
-> https://github.com/cursodsousa/curso-spring-boot-especialista/blob/master/src/main/java/io/github/dougllasfps/config/SecurityConfig.java
-> (CURSO) https://www.udemy.com/course/spring-boot-expert/

**Relacionamento ManyToMany**
-> https://www.baeldung.com/jpa-many-to-many

**Documentação do JPA com palavras chaves para fazer consultas no banco**
-> https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

** **
->

** **
->



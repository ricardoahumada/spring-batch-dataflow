## Entities - DDL
For @Id @GeneratedValue(strategy = [STRATEGY])

- https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/orm/jpa/vendor/AbstractJpaVendorAdapter.html

- setGenerateDdl(boolean generateDdl): Set whether to generate DDL after the EntityManagerFactory has been initialized, creating/updating all relevant tables.

1. If STRATEGY = GenerationType.AUTO
	- Must set setGenerateDdl to true;
2. If STRATEGY = GenerationType.IDENTITY
	- Can set setGenerateDdl to true;
package com.t3.springdatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringDatabaseApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(SpringDatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDatabaseApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception{
		log.info("Inicio da execucao");

		jdbcTemplate.execute("DROP TABLE funcionarios IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE funcionarios(" + 
			"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		jdbcTemplate.update("INSERT INTO funcionarios(first_name, last_name) VALUES ('Marcelo', 'Lima')");
		jdbcTemplate.update("INSERT INTO funcionarios(first_name, last_name) VALUES ('Mariana', 'Souza')");

		log.info("Pesquisando Funcinarios que tenham o nome igual a Marcelo");

		jdbcTemplate.query(
			"SELECT * FROM funcionarios WHERE first_name = ?",
			(result, rowNum) -> new Funcionario(
				result.getLong("id"),
				result.getString("first_name"),
				result.getString("last_name")
			),
			new Object[] {"Marcelo"}
		).forEach(funcionario -> log.info(funcionario.toString()));

		log.info("Terminando a Execucao");

	}

}

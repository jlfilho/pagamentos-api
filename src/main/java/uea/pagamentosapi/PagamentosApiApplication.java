package uea.pagamentosapi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uea.pagamentosapi.models.Categoria;
import uea.pagamentosapi.models.Endereco;
import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.models.Permissao;
import uea.pagamentosapi.models.Pessoa;
import uea.pagamentosapi.models.Usuario;
import uea.pagamentosapi.models.enums.TipoLancamento;
import uea.pagamentosapi.repositories.CategoriaRepository;
import uea.pagamentosapi.repositories.LancamentoRepository;
import uea.pagamentosapi.repositories.PermissaoRepository;
import uea.pagamentosapi.repositories.PessoaRepository;
import uea.pagamentosapi.repositories.UsuarioRepository;

@SpringBootApplication
public class PagamentosApiApplication implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PagamentosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		runUsers();
		runCategoria();
		runPessoa();
		runLancamento();

	}

	private void runLancamento() {

		Lancamento l1 = new Lancamento(null, "Salário mensal", LocalDate.parse("2023-02-10"),
				LocalDate.parse("2023-02-10"), new BigDecimal(6500.00), "Distribuição de lucros",
				TipoLancamento.RECEITA, new Categoria(1L), new Pessoa(1L));

		Lancamento l2 = new Lancamento(null, "Supermercado", LocalDate.parse("2023-02-10"),
				LocalDate.parse("2023-02-10"), new BigDecimal(100.32), null, TipoLancamento.DESPESA, new Categoria(1L),
				new Pessoa(2L));

		lancamentoRepository.saveAll(Arrays.asList(l1, l2));

		/*
		 * INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor,
		 * observacao, tipo, codigo_categoria, codigo_pessoa) values ('Academia',
		 * '2017-06-10', null, 120, null, 'DESPESA', 3, 3); INSERT INTO lancamento
		 * (descricao, data_vencimento, data_pagamento, valor, observacao, tipo,
		 * codigo_categoria, codigo_pessoa) values ('Conta de luz', '2017-02-10',
		 * '2017-02-10', 110.44, null, 'DESPESA', 3, 4); INSERT INTO lancamento
		 * (descricao, data_vencimento, data_pagamento, valor, observacao, tipo,
		 * codigo_categoria, codigo_pessoa) values ('Conta de água', '2017-06-10', null,
		 * 200.30, null, 'DESPESA', 3, 5); INSERT INTO lancamento (descricao,
		 * data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria,
		 * codigo_pessoa) values ('Restaurante', '2017-03-10', '2017-03-10', 1010.32,
		 * null, 'DESPESA', 4, 6); INSERT INTO lancamento (descricao, data_vencimento,
		 * data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
		 * values ('Venda vídeo game', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
		 * INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor,
		 * observacao, tipo, codigo_categoria, codigo_pessoa) values ('Clube',
		 * '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8); INSERT INTO
		 * lancamento (descricao, data_vencimento, data_pagamento, valor, observacao,
		 * tipo, codigo_categoria, codigo_pessoa) values ('Impostos', '2017-06-10',
		 * null, 123.64, 'Multas', 'DESPESA', 3, 9); INSERT INTO lancamento (descricao,
		 * data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria,
		 * codigo_pessoa) values ('Multa', '2017-04-10', '2017-04-10', 665.33, null,
		 * 'DESPESA', 5, 10); INSERT INTO lancamento (descricao, data_vencimento,
		 * data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
		 * values ('Padaria', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5); INSERT
		 * INTO lancamento (descricao, data_vencimento, data_pagamento, valor,
		 * observacao, tipo, codigo_categoria, codigo_pessoa) values ('Papelaria',
		 * '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4); INSERT INTO
		 * lancamento (descricao, data_vencimento, data_pagamento, valor, observacao,
		 * tipo, codigo_categoria, codigo_pessoa) values ('Almoço', '2017-06-10', null,
		 * 1040.32, null, 'DESPESA', 4, 3); INSERT INTO lancamento (descricao,
		 * data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria,
		 * codigo_pessoa) values ('Café', '2017-04-10', '2017-04-10', 4.32, null,
		 * 'DESPESA', 4, 2); INSERT INTO lancamento (descricao, data_vencimento,
		 * data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
		 * values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);
		 */

	}

	private void runPessoa() {
		System.out.println("--------------------CRIANDO PESSOAS--------------------");
		Pessoa p1 = new Pessoa(null, "João Silva", true,
				new Endereco("Rua do Abacaxi", "10", null, "Brasil", "38.400-120", "Uberlândia", "MG"));
		Pessoa p2 = new Pessoa(null, "Maria Rita", true,
				new Endereco("Rua do Sabiá", "110", "Apto 101", "Colina", "11.400-125", "Ribeirão Preto", "SP"));
		Pessoa p3 = new Pessoa(null, "Pedro Santos", true,
				new Endereco("Rua da Bateria", "23", null, "Morumbi", "54.212-121", "Goiânia", "GO"));
		Pessoa p4 = new Pessoa(null, "Ricardo Pereira", true,
				new Endereco("Rua do Motorista", "65", "Apto 302", "Aparecida", "38.400-120", "Salvador", "BA"));
		Pessoa p5 = new Pessoa(null, "Josué Mariano", true,
				new Endereco("Av Rio Branco", "321", null, "Jardins", "56.400-126", "Natal", "RN"));
		Pessoa p6 = new Pessoa(null, "Pedro Barbosa", true,
				new Endereco("Av Brasil", "100", null, "Tubalina", "77.400-12", "Porto Alegre", "RS"));
		Pessoa p7 = new Pessoa(null, "Henrique Medeiros", true,
				new Endereco("Rua do Sapo", "1120", "Apto 201", "Centro", "12.400-127", "Rio de Janeiro", "RJ"));
		Pessoa p8 = new Pessoa(null, "Carlos Santana", true,
				new Endereco("Rua da Manga", "433", null, "Centro", "31.400-120", "Belo Horizonte", "MG"));
		Pessoa p9 = new Pessoa(null, "Leonardo Oliveira", true,
				new Endereco("Rua do Músico", "566", null, "Segismundo Pereira", "38.400-000", "Uberlândia", "MG"));
		Pessoa p10 = new Pessoa(null, "Isabela Martins", true,
				new Endereco("Rua da Terra", "1233", "Apto 10", "Vigilato", "99.400-12'", "Manaus", "AM"));

		pessoaRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

	}

	private void runCategoria() {
		System.out.println("--------------------CRIANDO CATEGORIAS--------------------");
		Categoria cat1 = new Categoria(null, "Lazer");
		Categoria cat2 = new Categoria(null, "Alimentação");
		Categoria cat3 = new Categoria(null, "Supermercado");
		Categoria cat4 = new Categoria(null, "Farmácia");
		Categoria cat5 = new Categoria(null, "Outros");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
	}

	private void runUsers() {
		System.out.println("--------------------CRIANDO USUÁRIOS E PERMISSÕES--------------------");

		Permissao p1 = new Permissao(null, "ROLE_CADASTRAR_CATEGORIA");
		Permissao p2 = new Permissao(null, "ROLE_PESQUISAR_CATEGORIA");
		Permissao p3 = new Permissao(null, "ROLE_ATUALIZAR_CATEGORIA");
		Permissao p4 = new Permissao(null, "ROLE_REMOVER_CATEGORIA");

		Permissao p5 = new Permissao(null, "ROLE_CADASTRAR_PESSOA");
		Permissao p6 = new Permissao(null, "ROLE_PESQUISAR_PESSOA");
		Permissao p7 = new Permissao(null, "ROLE_ATUALIZAR_PESSOA");
		Permissao p8 = new Permissao(null, "ROLE_REMOVER_PESSOA");

		Permissao p9 = new Permissao(null, "ROLE_CADASTRAR_LANCAMENTO");
		Permissao p10 = new Permissao(null, "ROLE_PESQUISAR_LANCAMENTO");
		Permissao p11 = new Permissao(null, "ROLE_ATUALIZAR_LANCAMENTO");
		Permissao p12 = new Permissao(null, "ROLE_REMOVER_LANCAMENTO");

		permissaoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

		Usuario u1 = new Usuario(null, "Administrador", "admin@uea.edu.br",
				"{bcrypt}$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.");
		u1.getPermissoes().addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

		Usuario u2 = new Usuario(null, "Maria Silva", "maria@uea.edu.br",
				"{bcrypt}$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq");
		u2.getPermissoes().addAll(Arrays.asList(p2, p6, p10));

		usuarioRepository.saveAll(Arrays.asList(u1, u2));
	}

}

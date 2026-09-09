-- create database atividadeweb;
use atividadeweb;

show tables;
insert into pessoa
	(nome, idade, email, telefone, endereco, cidade, uf)
values
('Carlos Eduardo Silva', 42, 'carlos.silva@email.com', '(15) 98888-1111', 'Rua das Flores, 120', 'Itapetininga', 'SP'),
('Ana Paula Mendes', 35, 'ana.mendes@email.com', '(15) 97777-2222', 'Rua São João, 85', 'Sorocaba', 'SP'),
('Rafael Oliveira Santos', 39, 'rafael.santos@email.com', '(15) 96666-3333', 'Avenida Brasil, 450', 'Tatuí', 'SP'),
('Juliana Ferreira Costa', 31, 'juliana.costa@email.com', '(15) 95555-4444', 'Rua das Palmeiras, 210', 'Itapetininga', 'SP'),
('Marcos Vinícius Almeida', 48, 'marcos.almeida@email.com', '(15) 94444-5555', 'Rua do Comércio, 75', 'São Paulo', 'SP'),
('Fernanda Rodrigues Lima', 37, 'fernanda.lima@email.com', '(15) 93333-6666', 'Rua XV de Novembro, 310', 'Sorocaba', 'SP'),
('Lucas Henrique Souza', 29, 'lucas.souza@email.com', '(15) 92222-7777', 'Rua Paraná, 155', 'Itapetininga', 'SP'),
('Patrícia Gomes Martins', 44, 'patricia.martins@email.com', '(15) 91111-8888', 'Rua Central, 90', 'Capão Bonito', 'SP'),
('Renato Alves Pereira', 41, 'renato.pereira@email.com', '(15) 90000-9999', 'Rua das Acácias, 180', 'Tatuí', 'SP'),
('Camila Rodrigues Souza', 33, 'camila.souza@email.com', '(15) 98888-0000', 'Avenida Paulista, 500', 'São Paulo', 'SP');

INSERT INTO professor (siape, formacao, area, id_pessoa)
VALUES
('1234567', 'Mestrado em Ciência da Computação', 'Informática', 1),
('2345678', 'Licenciatura em Matemática', 'Matemática', 2),
('3456789', 'Bacharelado em Sistemas de Informação', 'Informática', 3),
('4567890', 'Licenciatura em Letras', 'Português', 4),
('5678901', 'Mestrado em História', 'História', 5),
('6789012', 'Licenciatura em Química', 'Química', 6),
('7890123', 'Tecnólogo em Análise e Desenvolvimento de Sistemas', 'Informática', 7),
('8901234', 'Licenciatura em Geografia', 'Geografia', 8),
('9012345', 'Mestrado em Engenharia de Software', 'Informática', 9),
('0123456', 'Licenciatura em Física', 'Física', 10);

INSERT INTO pessoa
    (nome, idade, email, telefone, endereco, cidade, uf)
VALUES
('Gabriel Henrique Oliveira', 17, 'gabriel.oliveira@email.com', '(15) 98811-2233', 'Rua das Palmeiras, 45', 'Itapetininga', 'SP'),
('Beatriz Martins Souza', 16, 'beatriz.souza@email.com', '(15) 97722-3344', 'Rua São Paulo, 120', 'Sorocaba', 'SP'),
('João Pedro Ferreira', 18, 'joao.ferreira@email.com', '(15) 96633-4455', 'Rua Paraná, 78', 'Tatuí', 'SP'),
('Larissa Gomes Santos', 17, 'larissa.santos@email.com', '(15) 95544-5566', 'Rua das Flores, 230', 'Itapetininga', 'SP'),
('Matheus Almeida Costa', 19, 'matheus.costa@email.com', '(15) 94455-6677', 'Avenida Brasil, 310', 'Capão Bonito', 'SP'),
('Isabela Rodrigues Lima', 16, 'isabela.lima@email.com', '(15) 93366-7788', 'Rua Central, 90', 'Sorocaba', 'SP'),
('Pedro Henrique Martins', 18, 'pedro.martins@email.com', '(15) 92277-8899', 'Rua XV de Novembro, 155', 'Itapetininga', 'SP'),
('Mariana Oliveira Alves', 17, 'mariana.alves@email.com', '(15) 91188-9900', 'Rua das Acácias, 200', 'Tatuí', 'SP'),
('Gustavo Ribeiro Souza', 19, 'gustavo.souza@email.com', '(15) 90099-0011', 'Rua do Comércio, 65', 'São Paulo', 'SP'),
('Sofia Fernandes Costa', 16, 'sofia.costa@email.com', '(15) 98800-1122', 'Avenida Paulista, 420', 'São Paulo', 'SP');

INSERT INTO curso
    (ano_inicio, nome)
VALUES
(2026, 'Técnico em Informática Integrado ao Ensino Médio'),
(2026, 'Técnico em Administração'),
(2026, 'Técnico em Eletromecânica Integrado ao Ensino Médio'),
(2025, 'Técnico em Mecatrônica'),
(2025, 'Técnico em Redes de Computadores'),
(2026, 'Técnico em Desenvolvimento de Sistemas');

INSERT INTO aluno
    (contato_responsavel, nome_responsavel, prontuario, id_pessoa, id_curso)
VALUES
('(15) 98811-2233', 'Carla Oliveira', 'ITP2026001', 11, 1),
('(15) 97722-3344', 'Fernanda Souza', 'ITP2026002', 12, 1),
('(15) 96633-4455', 'Patrícia Ferreira', 'ITP2026003', 13, 1),
('(15) 95544-5566', 'Luciana Santos', 'ITP2026004', 14, 1),
('(15) 94455-6677', 'Sandra Costa', 'ITP2026005', 15, 1),
('(15) 93366-7788', 'Renata Lima', 'ITP2026006', 16, 1),
('(15) 92277-8899', 'Márcia Martins', 'ITP2026007', 17, 1),
('(15) 91188-9900', 'Juliana Alves', 'ITP2026008', 18, 1),
('(15) 90099-0011', 'Cristiane Souza', 'ITP2026009', 19, 1),
('(15) 98800-1122', 'Patrícia Costa', 'ITP2026010', 20, 1);

INSERT INTO disciplina
    (area, carga_horaria, descricao, nome, id_curso)
VALUES
('Informática', '40h', 'Disciplina sobre lógica de programação e desenvolvimento de algoritmos.', 'Algoritmos', 1),
('Informática', '40h', 'Disciplina sobre programação orientada a objetos e seus principais conceitos.', 'Programação Orientada a Objetos', 1),
('Informática', '40h', 'Disciplina sobre bancos de dados relacionais, SQL e modelagem de dados.', 'Banco de Dados', 1),
('Informática', '40h', 'Disciplina sobre desenvolvimento de aplicações utilizando HTML, CSS, JavaScript e frameworks.', 'Programação Web', 1),
('Informática', '36h', 'Disciplina sobre conceitos, componentes e funcionamento de redes de computadores.', 'Redes de Computadores', 1),
('Informática', '36h', 'Disciplina sobre conceitos fundamentais de sistemas operacionais.', 'Sistemas Operacionais', 1),
('Informática', '40h', 'Disciplina sobre estruturas de dados e técnicas de organização e manipulação de informações.', 'Estrutura de Dados', 6),
('Informática', '40h', 'Disciplina sobre análise, projeto e desenvolvimento de sistemas de software.', 'Engenharia de Software', 6),
('Informática', '36h', 'Disciplina sobre princípios de segurança, proteção de dados e segurança de sistemas.', 'Segurança da Informação', 6),
('Informática', '40h', 'Disciplina sobre conceitos fundamentais e aplicações de inteligência artificial.', 'Inteligência Artificial', 6);

INSERT INTO usuario (login, senha, tipo_usuario, id_pessoa)
VALUES
('professor1', '1234', 'PROFESSOR', 1),
('professor2', '1234', 'PROFESSOR', 2),
('aluno1', '1234', 'ALUNO', 11),
('aluno2', '1234', 'ALUNO', 12),
('admin', '1234', 'ADMINISTRADOR', NULL);
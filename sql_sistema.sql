CREATE SCHEMA rastreioencomendas;

CREATE TABLE rastreioencomendas.frete(
	id serial primary key,
	tipo varchar,
	qtd_dias integer
);

CREATE TABLE rastreioencomendas.endereco(
	id serial primary key,
	cep varchar,
	logradouro varchar,
	cidade varchar,
	bairro varchar,
	numero integer,
	estado varchar,
	complemento text
);

CREATE TABLE rastreioencomendas.usuario(
	id serial primary key,
	email varchar unique,
	senha varchar,
	nome varchar,
	admin boolean,
	id_endereco integer references rastreioencomendas.endereco(id) not null
); 

CREATE TABLE rastreioencomendas.empresa(
	Id serial primary key,
	cnpj varchar not null,
	Id_endereco integer references rastreioencomendas.endereco(id) not null,
	nome_fantasma varchar,
	razao_social varchar
);

CREATE TABLE rastreioencomendas.status(
	Id serial primary key,
	descricao varchar
);

CREATE TABLE rastreioencomendas.pacote(
	id serial primary key,
	id_endereco_destino integer references rastreioencomendas.endereco(id) not null,
	id_empresa_remetente integer references rastreioencomendas.empresa(id) not null,
	codigo_rastreio varchar,
	descricao text,
	peso float,
	cpf_cnpj_destinatario varchar,
	id_frete integer references rastreioencomendas.frete(id) not null,
	data_postado timestamp
);

CREATE TABLE rastreioencomendas.historico_pacote(
	id serial primary key,
	id_pacote integer references rastreioencomendas.pacote(id) not null,
	Id_status integer references rastreioencomendas.status(id) not null,
	datahora_atualizacao timestamp,
	observacao text,
localizacao varchar
);

INSERT INTO rastreioencomendas.endereco(logradouro) VALUES('TESTE');
INSERT INTO rastreioencomendas.usuario(email, senha, nome, admin, id_endereco) VALUES ('teste@gmail.com', '123456', 'Administrador', true, 1);
INSERT INTO rastreioencomendas.frete(tipo, qtd_dias) VALUES('EX', 5);
INSERT INTO rastreioencomendas.frete(tipo, qtd_dias) VALUES('ST', 12);
INSERT INTO rastreioencomendas.status(descricao) VALUES('RECEBIDO NO CENTRO DE DISTRIBUIÇÃO');
INSERT INTO rastreioencomendas.status(descricao) VALUES('TRANSFERÊNCIA PARA');
INSERT INTO rastreioencomendas.status(descricao) VALUES('SEPARADO PARA O ROTEIRO DE ENTREGA');
INSERT INTO rastreioencomendas.status(descricao) VALUES('PROCESSO DE ENTREGA');
INSERT INTO rastreioencomendas.status(descricao) VALUES('ENTREGA REALIZADA');
INSERT INTO rastreioencomendas.status(descricao) VALUES('ENDEREÇO DESTINATÁRIO NÃO LOCALIZADO');
INSERT INTO rastreioencomendas.status(descricao) VALUES('RETIRADO DO MANIFESTO ANTERIOR');
INSERT INTO rastreioencomendas.status(descricao) VALUES('CLIENTE AUSENTE/ESTABELECIMENTO FECHADO');
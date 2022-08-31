CREATE TABLE IF NOT EXISTS endereco (
    id int auto_increment primary key,
    logradouro varchar(255) not null,
    numero varchar(10) not null,
    complemento varchar(255),
    bairro varchar(255) not null,
    municipio varchar(255) not null,
    estado varchar(255) not null,
    cep varchar(255) not null,
    pais varchar(255) not null,
);

--CREATE TABLE IF NOT EXISTS dentista (
--    id int auto_increment primary key,
--    nome varchar(255) not null,
--    sobrenome varchar(255) not null,
--    matriculaDeCadastro varchar(10) not null,
--);
--
--CREATE TABLE IF NOT EXISTS paciente (
--    id int auto_increment primary key,
--    nome varchar(255) not null,
--    sobrenome varchar(255) not null,
--    endereco_id int not null,
--    rg varchar(20) not null,
--    dataDeCadastro date not null,
--    constraint fk_paciente_endereco foreign key (endereco_id) references endereco(id),
--);
--
--CREATE TABLE IF NOT EXISTS consulta (
--    id int auto_increment primary key,
--    paciente_id int not null,
--    dentista_id int not null,
--    dataHora timestamp not null,
--    constraint fk_consulta_paciente foreign key (paciente_id) references paciente(id),
--    constraint fk_consulta_dentista foreign key (dentista_id) references dentista(id),
--);
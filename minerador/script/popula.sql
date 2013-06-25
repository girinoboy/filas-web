/*Script de Carga das Tabelas - Inserts */
INSERT INTO `Base`.`Fornecedor`
(`Nome`,`Telefone`,`Endereco`,`CNPJ`,`Codigo_Fornecedor`)
VALUES
('Água Mineral Nativa','(62)4008-8400','Bairro Setor Bueno - GOIANIA - GO',2147483647,1);
INSERT INTO `Base`.`Fornecedor`
(`Nome`,`Telefone`,`Endereco`,`CNPJ`,`Codigo_Fornecedor`)
VALUES
('Cereais Bramil LTDA','(24)2251-8000','Bairro Cantagalo - TRES RIOS - RJ',71268349818765,2);
INSERT INTO `Base`.`Fornecedor`
(`Nome`,`Telefone`,`Endereco`,`CNPJ`,`Codigo_Fornecedor`)
VALUES
('Cafe do Sitio IND e COM LTDA','(62)3202-3020','Bairro Vila Jardim Dom Bosco - GOIANIA - GO',
71832961559924,3);
INSERT INTO `Base`.`Fornecedor`
(`Nome`,`Telefone`,`Endereco`,`CNPJ`,`Codigo_Fornecedor`)
VALUES
('Icasa Massas e Biscoitos LTDA','(67)787-9600','Bairro Universitario - CAMPO GRANDE - MS',
01726547234876,4);
INSERT INTO `Base`.`Fornecedor`
(`Nome`,`Telefone`,`Endereco`,`CNPJ`,`Codigo_Fornecedor`)
VALUES
('CANAA IND. LATICINIOS LTDA','(69)3441-3259','Bairro Saida para Cuiaba - JI-PARANA - RO',
238755661014014,5);
INSERT INTO `Base`.`Funcionario`
(`Matricula`,`Nome`,`Cargo`,`Salario`,`Telefone`,`CPF`)
VALUES
(111,'Fausto Rodrigues Silva','Caixa',820.10,'(61)3305-1234','109278365044');
INSERT INTO `Base`.`Funcionario`
(`Matricula`,`Nome`,`Cargo`,`Salario`,`Telefone`,`CPF`)
VALUES
(112,'Silas Oliveira Martins','Caixa',820.10,'(61)3305-4321','87612332101');
INSERT INTO `Base`.`Funcionario`
(`Matricula`,`Nome`,`Cargo`,`Salario`,`Telefone`,`CPF`)
VALUES
(113,'Vanda Pereira Rocha','Gerente',3200.72,'(61)3305-1010','09121300988');
INSERT INTO `Base`.`Funcionario`
(`Matricula`,`Nome`,`Cargo`,`Salario`,`Telefone`,`CPF`)
VALUES
(114,'Vitor Hugo Lima','Controle de Estoque',1100.30,'(61)3305-3030','10527829650');
INSERT INTO `Base`.`Funcionario`
(`Matricula`,`Nome`,`Cargo`,`Salario`,`Telefone`,`CPF`)
VALUES
(115,'Marcos Cesar Mendes','Seguranca',1500.88,'(61)3305-5050','09876543210');
INSERT INTO `Base`.`Cliente`
(`Codigo_Cliente`,`CPF`,`Nome`,`Telefone`)
VALUES(11,'12983746501','Sebastao Ramos Peres','(61)3363-7865');
INSERT INTO `Base`.`Cliente`
(`Codigo_Cliente`,`CPF`,`Nome`,`Telefone`)
VALUES
(12,'71628614511','Maria do Carmo Lemes','(61)3389-7421');
INSERT INTO `Base`.`Cliente`
(`Codigo_Cliente`,`CPF`,`Nome`,`Telefone`)
VALUES
(13,'91289100012','Fabio Pereira de Moura','(61)3340-6510');
INSERT INTO `Base`.`Cliente`
(`Codigo_Cliente`,`CPF`,`Nome`,`Telefone`)
VALUES
(14,'01090187611','Luis Humberto Pereira','(61)3389-0110');
INSERT INTO `Base`.`Cliente`
(`Codigo_Cliente`,`CPF`,`Nome`,`Telefone`)
VALUES
(15,'09127187628','Silvania Martins Filho','(61)3337-0987');

INSERT INTO `base`.`produto`
(`Codigo_Produto`,`Decricao`,`Valor_unitario`,`Quantidade`)
VALUES
(1111,'Agua Mineral - Sem Gas',1.50,2500);
INSERT INTO `base`.`produto`
(`Codigo_Produto`,`Decricao`,`Valor_unitario`,`Quantidade`)
VALUES
(2222,'Refrigerante de Cola',3.89,2000);
INSERT INTO `base`.`produto`
(`Codigo_Produto`,`Decricao`,`Valor_unitario`,`Quantidade`)
VALUES
(3333,'Cafe Torrado e Moido',1.19,300);
INSERT INTO `base`.`produto`
(`Codigo_Produto`,`Decricao`,`Valor_unitario`,`Quantidade`)
VALUES
(4444,'Macarrao Instantaneo',0.59,700);
INSERT INTO `base`.`produto`
(`Codigo_Produto`,`Decricao`,`Valor_unitario`,`Quantidade`)
VALUES
(5555,'Leite Desnatado',1.99,400);

INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(1111,4);
INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(1111,3);
INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(2222,5);
INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(4444,2);
INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(2222,1);
INSERT INTO `base`.`fornecimento`
(`Codigo_Produto`,`Codigo_Fornecedor`)
VALUES
(5555,3);


INSERT INTO `Base`.`Venda`
(`Codigo_NF`,`Data`,`Codigo_Cliente`,`Matricula`)
VALUES
(1,'2013-05-28',15,111);
INSERT INTO `Base`.`Venda`
(`Codigo_NF`,`Data`,`Codigo_Cliente`,`Matricula`)
VALUES
(2,'2013-05-29',14,112);
INSERT INTO `Base`.`Venda`
(`Codigo_NF`,`Data`,`Codigo_Cliente`,`Matricula`)
VALUES
(3,'2013-05-30',13,113);
INSERT INTO `Base`.`Venda`
(`Codigo_NF`,`Data`,`Codigo_Cliente`,`Matricula`)
VALUES
(4,'2013-05-31',12,114);
INSERT INTO `Base`.`Venda`
(`Codigo_NF`,`Data`,`Codigo_Cliente`,`Matricula`)
VALUES
(5,'2013-06-01',11,115);

INSERT INTO `base`.`retirada`
(`Codigo_Produto`,`Codigo_NF`,`Codigo_Cliente`,`Matricula`,`Quantidade`)
VALUES
(1111,5,11,115,4);
INSERT INTO `base`.`retirada`
(`Codigo_Produto`,`Codigo_NF`,`Codigo_Cliente`,`Matricula`,`Quantidade`)
VALUES
(1111,4,12,114,5);
INSERT INTO `base`.`retirada`
(`Codigo_Produto`,`Codigo_NF`,`Codigo_Cliente`,`Matricula`,`Quantidade`)
VALUES
(1111,3,13,113,1);
INSERT INTO `base`.`retirada`
(`Codigo_Produto`,`Codigo_NF`,`Codigo_Cliente`,`Matricula`,`Quantidade`)
VALUES
(1111,2,14,112,2);
INSERT INTO `base`.`retirada`
(`Codigo_Produto`,`Codigo_NF`,`Codigo_Cliente`,`Matricula`,`Quantidade`)
VALUES
(1111,1,15,111,12);
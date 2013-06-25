/*****Funcionário*****/
CREATE TABLE Funcionario (
Matricula Int PRIMARY KEY,
Nome Varchar(50),
Cargo Varchar(50),
Salario Decimal(50,2),
Telefone Varchar(50),
CPF Varchar(16)
);
/*****Cliente*****/
CREATE TABLE Cliente (
Codigo_Cliente Int PRIMARY KEY,
Nome Varchar(50),
Telefone Varchar(50),
CPF Varchar(16)
);
/*****Fornecedor*****/
CREATE TABLE Fornecedor (
Codigo_Fornecedor Int PRIMARY KEY,
Nome Varchar(50),
Telefone Varchar(50),
Endereco Varchar(50),
CNPJ Varchar(16)
);

/*****Produto*****/
CREATE TABLE Produto (
Codigo_Produto Int PRIMARY KEY,
Decricao Varchar(50),
Valor_unitario Decimal(15,2),
Quantidade Int
);
/*****Fornecimento*****/
CREATE TABLE Fornecimento (
Codigo_Produto Int,
Codigo_Fornecedor Int,
PRIMARY KEY(Codigo_Produto, Codigo_Fornecedor),
FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo_Produto),
FOREIGN KEY(Codigo_Fornecedor) REFERENCES Fornecedor(Codigo_Fornecedor)
);
/*****Venda*****/
CREATE TABLE Venda (
Codigo_NF Int,
Data Date,
Codigo_Cliente Int,
Matricula Int,
PRIMARY KEY(Codigo_NF,Codigo_Cliente,Matricula),
FOREIGN KEY(Matricula) REFERENCES Funcionario (Matricula),
FOREIGN KEY(Codigo_Cliente) REFERENCES Cliente (Codigo_Cliente)
);

/*****Retirada*****/
CREATE TABLE Retirada (
Codigo_Produto Int,
Codigo_NF Int,
Codigo_Cliente Int,
Matricula Int,
Quantidade Varchar(50) not null,
PRIMARY KEY(Codigo_Produto, Codigo_NF, Codigo_Cliente, Matricula), 
FOREIGN KEY(Codigo_Produto) REFERENCES Produto (Codigo_Produto),
FOREIGN KEY(Codigo_NF) REFERENCES Venda(Codigo_NF),
FOREIGN KEY(Codigo_Cliente) REFERENCES Venda(Codigo_Cliente),
FOREIGN KEY(Matricula) REFERENCES Venda(Matricula)
);
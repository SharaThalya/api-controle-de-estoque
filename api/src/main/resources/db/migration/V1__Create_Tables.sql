CREATE TABLE produto (
  id SERIAL PRIMARY KEY,
  estoqueMinimo int NOT NULL,
  descricao varchar NOT NULL,
  valor float NOT NULL,
  saldo int NOT NULL,
  status varchar NOT NULL
);
CREATE TABLE entrada (
  id SERIAL PRIMARY KEY,
  id_produto int NOT NULL,
  dataEntrada DATE NOT NULL,
  quantidade int NOT NULL,
  isEstornado BOOLEAN NOT NULL DEFAULT false,
  FOREIGN KEY (id_produto) REFERENCES produto(id)
);

CREATE TABLE saida (
  id SERIAL PRIMARY KEY,
  id_produto int NOT NULL,
  dataSaida DATE NOT NULL,
  quantidade int NOT NULL,
  isEstornado BOOLEAN NOT NULL DEFAULT false,
  FOREIGN KEY (id_produto) REFERENCES produto(id)
  );

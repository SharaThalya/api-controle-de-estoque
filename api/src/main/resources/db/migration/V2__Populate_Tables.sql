INSERT INTO produto (
    id, estoqueMinimo, descricao, valor, saldo, status
    )
VALUES (11, 30,'Casaco', '120.00' ,30, 'Estoque perigoso'),
(12, 30,'Blusa',  '50.00' ,30, 'Estoque perigoso'),
(13, 30,'Calça',  '70.00',0, 'Sem estoque'),
(14, 30,'Saia',  '60.00',30, 'Estoque perigoso'),
(15, 30,'Bermuda',  '60.00',50, 'Estoque confortável'),
(16, 30,'Camisa',  '80.00',60, 'Estoque confortável'),
(17, 30,'Meia',  '25.00',60, 'Estoque confortável'),
(18, 30,'Sapato',  '90.00',0, 'Sem estoque'),
(19, 30,'Bolsa',  '75.00',60, 'Estoque confortável'),
(20, 30,'Pijama',  '60.00' ,0, 'Sem estoque');

INSERT INTO entrada (
    id, id_produto, dataEntrada, quantidade, isEstornado
)
VALUES (1, 11, '2021-05-05', 100, FALSE),
(2, 12, '2021-05-05', 150, FALSE),
(3, 13, '2021-05-05', 120, FALSE),
(4, 14, '2021-05-05', 80, FALSE),
(5, 15, '2021-05-05', 120, FALSE),
(6, 16, '2021-05-05', 100, FALSE),
(7, 17, '2021-05-05', 200, FALSE),
(8, 18, '2021-05-05', 80, FALSE),
(9, 19, '2021-05-05', 50, FALSE),
(10, 20, '2021-05-05', 50, FALSE);

INSERT INTO saida (
    id, id_produto, dataSaida, quantidade, isEstornado
)
VALUES (1, 11, '2021-05-10', 70, FALSE),
(2, 12, '2021-05-13', 80, FALSE),
(3, 13, '2021-05-13', 80, FALSE),
(4, 14, '2021-05-15', 20, FALSE),
(5, 15, '2021-05-16', 45, FALSE),
(6, 13, '2021-05-20', 40, FALSE);
CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    url_imagem VARCHAR(255),
    data_nascimento DATE,
    status VARCHAR(50),
    categoria_id INT,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

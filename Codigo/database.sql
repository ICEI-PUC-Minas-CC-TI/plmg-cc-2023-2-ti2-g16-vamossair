CREATE TABLE usuarios (
  id serial PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
);

CREATE TABLE lugar (
  id serial PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  descricao TEXT,
  categoria VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  rua VARCHAR(255) NOT NULL,
  complemento VARCHAR(255)
);

CREATE TABLE favoritos (
    id SERIAL PRIMARY KEY,
    usuario_id INT,
    lugar_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (lugar_id) REFERENCES lugar(id)
);

CREATE TABLE avaliacoes (
    id SERIAL PRIMARY KEY,
    usuario_id INT,
    lugar_id INT,
    nota INT,
    comentario TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (lugar_id) REFERENCES lugar(id)
);
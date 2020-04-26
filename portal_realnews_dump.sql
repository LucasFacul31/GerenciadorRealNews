INSERT INTO noticia (
  titulo,
  descricao,
  texto
) VALUES (
  'Notícia 1',
  'Descrição da notícia 1',
  'Texto teste para a notícia 1.'
);

INSERT INTO noticia (
  titulo,
  descricao,
  texto
) VALUES (
  'Notícia 2',
  'Descrição da notícia 2',
  'Texto teste para a notícia 2.'
);

INSERT INTO noticia (
  titulo,
  descricao,
  texto
) VALUES (
  'Notícia 3',
  'Descrição da notícia 3',
  'Texto teste para a notícia 3.'
);

INSERT INTO comentario (
  nome,
  texto,
  fk_noticia_id
) VALUES (
  'Lucas',
  'Comentario teste',
  3
)
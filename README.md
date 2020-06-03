# TP03DR4 - Criptografia utilizando SQLite e Firebase Auth
Nesse Teste de performace feito ao longo da aula de Segurança, Monetização e Publicação de Aplicativos Android
fizemos um programa simples para salvar 6 respostas de perguntas utilizando a biblioteca Room para usarmos o SQLite.
O nome do bairro e "restaurante" avaliados ficam salvos criptografados e podemos ver as avaliações feitas pelo usuario logado e uma média 
de todos os usuários do telefone dividida em bairros.
### Problema(s) encontrado(s)
Ao seguir a Criptografia aprendida em sala, uma mesma string pode gerar mais de um tipo de ByteArray, levando isso em concideração vemos que ao agrupar por bairros o agrupamento fica errado, pois o mesmo bairro pode ter diversas ByteArrays e gerando uma dificuldade extra na organização

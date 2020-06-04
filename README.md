# TP03DR4 - Criptografia utilizando SQLite e Firebase Auth
Nesse Teste de performace feito ao longo da aula de Segurança, Monetização e Publicação de Aplicativos Android
fizemos um programa simples para salvar 6 respostas de perguntas utilizando a biblioteca Room para usarmos o SQLite.
O nome do bairro e "restaurante" avaliados ficam salvos criptografados e podemos ver as avaliações feitas pelo usuario logado e uma média 
de todos os usuários do telefone dividida em bairros.
### Problema(s) encontrado(s)
[x] ~~Ao seguir a Criptografia aprendida em sala, uma mesma string pode gerar mais de um tipo de ByteArray, levando isso em concideração vemos que ao agrupar por bairros o agrupamento fica errado, pois o mesmo bairro pode ter diversas ByteArrays e gerando uma dificuldade extra na organização~~
*O problema foi resolvido apos mudar de Group By para Order By* explicação do por que sera inserida depois.
> ~~Aparentemente é possivel que o Order By agrupe caso os nomes sejam iguais, isto deve estar ocorrendo. Mesmo assim o fato de o Group By não funcionar sozinho deve se dar em relação aos bytes arrays não serem iguais.~~

Problema resolvido apos Criação da classe CriptoString onde o dado armazenado no BD é transformado de string para isto e de isto para string quando se for pegar.

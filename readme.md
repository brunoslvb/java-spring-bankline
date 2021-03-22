**API-BankLine**

​	O projeto tem como escopo um serviço de cadastro e manipulação de lançamentos de um banco.

**Rotas**

![image-20210321213645433](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210321213645433.png)

​	Como pode-se observar, temos 6 rotas disponíveis para uso na nossa API. Vamos mergulhar fundo agora para ver de perto a funcionalidade de cada uma.

**/user (createUser)**

​	Essa rota é o começo de tudo. com ela iremos criar o usuário e iremos inicializar suas contas, sendo elas a conta padrão ou debito que chamamos aqui em cada operação de AccountD, e a segunda conta que é a de credito, que chamamos de AccountC.



Segue agora uma ilustração do seu uso no insomnia:

![image-20210321214436325](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210321214436325.png)

​	Aqui podemos ver que o usuário deve informar seu cpf, login, nome e senha. Se digitado tudo corretamente e o cpf e login estiverem disponíveis para uso, o cadastro será efetuado com sucesso.



**/login-user (login)**

​	Após o cadastro, o usuário deve se autenticar. Essa parte é essencial porque aqui ele receberá seu token de acesso para as próximas operações e poderá ter acesso as suas contas.

vamos para mais uma ilustração:

![image-20210321215113560](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210321215113560.png)

Agora sim! usuário verificado, com token em mãos e acesso aos dados das suas contas.



**/lancamento/plan-account (criarPlan)**

Nesta rotas será criada os planos de conta do usuário. Vamos dar uma olhada na pratica:

![image-20210321215522267](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210321215522267.png)

​	No plano de conta temos as informações essências para se fazer um lançamento. A descrição vai nos dizer do que se trata, o login informa qual é o usuário e o tipo de movimento é que definira se é uma despesa, receita e transferência. Aqui estamos trabalhando com termos chave em inglês então se for uma receita chamamos de revenue, se for uma despesa se chamará charge e transferência será transfer. pra facilitar o uso definimos que o preenchimento desse capo deve ser feito com a inicial do seu nome, no exemplo acima está a letra R, portanto é um revenue!



**/lancamento/plan-account (getPlans)**

​	Nessa rota pegaremos todos os planAccounts criados no pelo usuário. O acesso é feito pelo login como parâmetro.

![image-20210322002724111](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210322002724111.png)

Podemos ver que aqui temos um plano de conta para cada exemplo, agora só falta lançar!

**/lancamento (lancamento)**

Agora é que de fato vamos fazer os lançamentos.

![image-20210322003054453](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210322003054453.png)

​	Esta primeira imagem é de um lançamento de transferência. O Id da conta tem que ser colocado, a conta de destino que irá receber a transferência, a data, descrição, Id do plano de conta respectivo e o valor que será debitado.

![image-20210322003439366](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210322003439366.png)

​	Já este exemplo acima, tem a breve mudança de que não tem conta destino e o Id do plano de conta muda.



**/dashboard (dashLancamentos)**

​	É no dashboard que vamos ter acesso a todos os lancamentos, ver o saldo das contas e tudo isso listado e bem separado para facilitar a manipulação de dados.

![image-20210322022926977](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210322022926977.png)

​	Podemos observar que as primeiras informações mostradas são as contas do usuário, com o saldo devidamente atualizado. nesses exemplos utilizamos apenas a conta de debito para demonstrar os resultados.

![image-20210322022956488](C:\Users\kiboz\AppData\Roaming\Typora\typora-user-images\image-20210322022956488.png)

​	No restante da resposta temos os lançamentos separados por conta e uma lista exclusiva para transferências para facilitar na visualização dos lançamentos. Temos todas as informações passadas no plano de conta e todos os valores e tipos de movimento nas informações de lançamentos.

​	Vale lembrar que nos parâmetros passados, pode se filtrar os lançamentos por data, auxiliando na organização e na busca que o usuário pode vir a fazer em seus usos diários.



**E isso é tudo 🚀🚀🚀**

Temos uma API segura e pronta para gerir os lançamentos de cada usuário cadastrado!

​																**Equipe RESTaNODE**
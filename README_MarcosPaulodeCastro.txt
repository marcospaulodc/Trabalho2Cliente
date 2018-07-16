Marcos Paulo de Castro

1- Execute o trabalho pela classe Main.
2- As classes e métodos mais complexos estão devidamente comentados, a fim de obter um melhor entendimento do funcionamento do programa.


Descrição sucinta do programa

Na classe Main são criados os canais de comunicação de cada cliente, é instanciado o objeto compartilhado chamado fluxoDeControle no qual será responsável por identificar o momento certo de realizar as operações. Cria-se as 3 threads de Cliente, identificadas com um id estático, na aqual cada thread será responsável por realizar um tipo de operação.Cria-se também, as 3 threads de servidores, cada servidor é identificado também com um id estático, e recebe as 3 entradas de dados referente a cada tipo de operação (Depósito, Retirada e Correção). As threads dos servidores recebem também em seu construtor o objeto compartilhado.

Cada cliente possui um Pipe distinto não compartilhado, referentes a cada tipo de operação. Entra-se no loop de quantidades de requisições e há uma verificação, sendo que o cliente com id 1, escreve o valor de 10.00 sendo este o Depósito, o cliente de id 2 escreve o valor de 3.00 sendo este a Retirada e por fim, escreve 1.00 quando for Correção.

Neste momento as threads Rececptoras entram em ação, lendo as requisições difundidas pelos clientes e setando um respectivo valor para cada operação que chega.

As Threads Receptoras são inicializadas no constutor da classe Servidor, juntamente com o objeto compartilhado.

No método run() do Servidor contém um loop com a quantidade total de requisições difundidas pelas threads Receptoras e chama o método mRealizarOp(). Este método contém a seção crítica, possuindo condições de controle para a realização da operações e manipulação dos objetos: ArrayList<Integer> vetorDeControle e operacoes.

Por fim, realizei o uso do sleep a fim de garantir o funcionamento correto e esperado do programa e informando os valores dos 3 servidores garantindo a consistência de estado e replicação do Saldo Final.







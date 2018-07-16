Marcos Paulo de Castro

1- Execute o trabalho pela classe Main.
2- As classes e m�todos mais complexos est�o devidamente comentados, a fim de obter um melhor entendimento do funcionamento do programa.


Descri��o sucinta do programa

Na classe Main s�o criados os canais de comunica��o de cada cliente, � instanciado o objeto compartilhado chamado fluxoDeControle no qual ser� respons�vel por identificar o momento certo de realizar as opera��es. Cria-se as 3 threads de Cliente, identificadas com um id est�tico, na aqual cada thread ser� respons�vel por realizar um tipo de opera��o.Cria-se tamb�m, as 3 threads de servidores, cada servidor � identificado tamb�m com um id est�tico, e recebe as 3 entradas de dados referente a cada tipo de opera��o (Dep�sito, Retirada e Corre��o). As threads dos servidores recebem tamb�m em seu construtor o objeto compartilhado.

Cada cliente possui um Pipe distinto n�o compartilhado, referentes a cada tipo de opera��o. Entra-se no loop de quantidades de requisi��es e h� uma verifica��o, sendo que o cliente com id 1, escreve o valor de 10.00 sendo este o Dep�sito, o cliente de id 2 escreve o valor de 3.00 sendo este a Retirada e por fim, escreve 1.00 quando for Corre��o.

Neste momento as threads Rececptoras entram em a��o, lendo as requisi��es difundidas pelos clientes e setando um respectivo valor para cada opera��o que chega.

As Threads Receptoras s�o inicializadas no constutor da classe Servidor, juntamente com o objeto compartilhado.

No m�todo run() do Servidor cont�m um loop com a quantidade total de requisi��es difundidas pelas threads Receptoras e chama o m�todo mRealizarOp(). Este m�todo cont�m a se��o cr�tica, possuindo condi��es de controle para a realiza��o da opera��es e manipula��o dos objetos: ArrayList<Integer> vetorDeControle e operacoes.

Por fim, realizei o uso do sleep a fim de garantir o funcionamento correto e esperado do programa e informando os valores dos 3 servidores garantindo a consist�ncia de estado e replica��o do Saldo Final.







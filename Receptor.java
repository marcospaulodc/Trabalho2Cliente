package Trabalho2;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Receptor extends Thread
{
	private DataInputStream in;
	private Servidor servidor;

	public Receptor(PipedInputStream in, Servidor servidor)
	{
		this.in = new DataInputStream(in);
		this.servidor = servidor;
	}
	
	public void run()
	{
		for (int i = 0; i < 40; i++)//Atenção alterar para 40 repetições, quando finalizar...
		{
			try 
			{
				double valorOperacao = this.in.readDouble();
				//Se, a Thread de cliente escreveu 10.00 no out, seta minha variável Operacoes para 1(Retirada)
				if(valorOperacao == 10.00)
				{
					this.servidor.setOperacoes(1);
				}
				//Senão se, a Thread de cliente escreveu 3.00 no out, seta minha variável Operacoes para 2(Depósito)
				else if(valorOperacao == 3.00)
				{
					this.servidor.setOperacoes(2);
				}
				//Senão se, a Thread de cliente escreveu 1.00 no out, seta minha variável Operacoes para 3(Correção)
				else if(valorOperacao == 1.00)
				{
					this.servidor.setOperacoes(3);
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

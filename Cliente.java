package Trabalho2;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread
{
	private int idOperacao;
	private DataOutputStream out1;
	private DataOutputStream out2;
	private DataOutputStream out3;
	
	//Cada cliente possui um Pipe Distinto não compartilhado referente a cada tipo de operação
	public Cliente(int idOperacao, PipedOutputStream out1, PipedOutputStream out2, PipedOutputStream out3)
	{
		this.idOperacao = idOperacao;
		this.out1 = new DataOutputStream(out1);
		this.out2 = new DataOutputStream(out2);
		this.out3 = new DataOutputStream(out3);
	}
	
	public void run()
	{
		for (int i = 0; i < 40; i++)//Atenção alterar para 40 repetições, quando finalizar...
		{
			try 
			{
				//Se, o idOperacao chega 1, realiza a operação de Depósito
				if(this.idOperacao == 1)
				{
					this.out1.writeDouble(10.00);
					this.out2.writeDouble(10.00);
					this.out3.writeDouble(10.00);
				}
				//Senão se, o idOperacao chega 2, realiza a operação de Retirada
				else if(idOperacao == 2)
				{
					this.out1.writeDouble(3.00);
					this.out2.writeDouble(3.00);
					this.out3.writeDouble(3.00);
				}
				//Senão, o idOperacao chega 3, realiza a operação de Correção
				else
				{
					this.out1.writeDouble(1.00);
					this.out2.writeDouble(1.00);
					this.out3.writeDouble(1.00);
				}

				//out1.flush();
				//out2.flush();
				//out3.flush();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package Trabalho2;
/**
 * @author Marcos Paulo de Castro
 */

import java.io.PipedInputStream;
import java.util.ArrayList;

public class Servidor extends Thread
{
	private int idServidor;
	private double saldo = 10_000.00;
	private ArrayList<Integer> operacoes = new ArrayList<Integer>();
	private FluxoDeControle fluxoDeControle = null;
	int cont = 0;
	
	public Servidor(int idServidor, PipedInputStream inDep, PipedInputStream inRet, PipedInputStream inApl, FluxoDeControle fluxoDeControle)
	{
		//Em tal construtor (Servidor), são instancias as receptoras contendo a entrada de dados
		Receptor receptorDep = new Receptor(inDep, this);
		Receptor receptorRet = new Receptor(inRet, this);
		Receptor receptorApl = new Receptor(inApl, this);
		this.fluxoDeControle = fluxoDeControle;
		this.idServidor = idServidor;
		
		//Startando as threads receptoras
		receptorDep.start();
		receptorRet.start();
		receptorApl.start();
	}
	
	public void mRealizarOp()
	{
		//Seção crítica fluxoDeControle
		synchronized (this.fluxoDeControle) 
		{
			if(this.fluxoDeControle.vetorDeControle.size() == 0 || this.fluxoDeControle.vetorDeControle.size() == 3)
			{
				//vetor cheio ou vazio realiza a limpeza
				fluxoDeControle.vetorDeControle.clear();
				fluxoDeControle.setOperacoes(this.getOperacoes().get(0));
				fluxoDeControle.setVetorDeControle(idServidor);
				
				if(fluxoDeControle.getOperacoes() == 1)
				{
					this.mDepositar(10.00);
					System.out.println("Cliente 1 realizou depósito!");
					cont++;
				}
				else if(fluxoDeControle.getOperacoes() == 2)
				{
					this.mRetirar(3.00);
					System.out.println("Cliente 2 realizou saque!");
					cont++;
				}
				else if(fluxoDeControle.getOperacoes() == 3)
				{
					this.mAplicarCorrecao();
					System.out.println("Cliente 3 realizou correção!");
					cont++;
				}
				for (int i = 0; i < this.getOperacoes().size(); i++)
				{
					if(this.operacoes.get(i) == this.fluxoDeControle.getOperacoes())
					{
						//Removendo operações já realizadas da lista
						this.operacoes.remove(i);
						return;
					}
				}
			}
			else if(fluxoDeControle.vetorDeControle.size() == 2 || fluxoDeControle.vetorDeControle.size() == 1)
			{
				//se no meu vetor já tenho um servidor vinculado, apenas retorno
				if(fluxoDeControle.vetorDeControle.contains(idServidor))
				{
					return;
				}
				else
				{
					//senão, seto o idServidor no meu array
					fluxoDeControle.setVetorDeControle(idServidor);
					if(fluxoDeControle.getOperacoes() == 1)
					{
						this.mDepositar(10.00);
						System.out.println("Cliente 1 realizou depósito!");
						cont++;
					}
					else if(fluxoDeControle.getOperacoes() == 2)
					{
						this.mRetirar(3.00);
						System.out.println("Cliente 2 realizou saque!");
						cont++;
					}
					else if(fluxoDeControle.getOperacoes() == 3)
					{
						this.mAplicarCorrecao();
						System.out.println("Cliente 3 realizou correção!");
						cont++;
					}
					for (int i = 0; i < this.getOperacoes().size(); i++)
					{
						if(this.operacoes.get(i) == this.fluxoDeControle.getOperacoes())
						{
							//Removendo operações já realizadas da lista
							this.operacoes.remove(i);
							return;
						}
					}
				}
			}
		}
	}
	
	public int getIdServidor() 
	{
		return idServidor;
	}

	public void setIdServidor(int idServidor) 
	{
		this.idServidor = idServidor;
	}

	public ArrayList<Integer> getOperacoes() 
	{
		return operacoes;
	}

	public void setOperacoes(int operacoes) 
	{
		this.operacoes.add(operacoes);
	}

	public double getSaldo() 
	{
		return saldo;
	}

	public void setSaldo(double saldo) 
	{
		this.saldo = saldo;
	}

	public void mDepositar(double valor)
	{
		this.saldo += valor;
	}
	
	public void mRetirar(double valor)
	{
		this.saldo -= valor;
	}
	
	public void mAplicarCorrecao()
	{
		this.saldo = (saldo * (1.1));
	}
	
	public void run()
	{
		try 
		{
			System.out.println("*** Atualizando o servidor do id: " + idServidor + ", aguarde um pouco ***");
			sleep(8000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(cont < 120)
		{
			mRealizarOp();
		}
		try 
		{
			sleep(3000);
			System.out.printf("Saldo Servidor %d - R$%.2f\n", idServidor , saldo);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
}
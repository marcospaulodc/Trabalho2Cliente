package Trabalho2;

import java.util.ArrayList;

public class FluxoDeControle 
{
	ArrayList<Integer> vetorDeControle = new ArrayList<>();
	private int operacoes;
	
	public ArrayList<Integer> getVetorDeControle() 
	{
		return vetorDeControle;
	}
	
	public void setVetorDeControle(int idServidor) 
	{
		this.vetorDeControle.add(idServidor);
	}
	
	public int getOperacoes() 
	{
		return operacoes;
	}
	
	public void setOperacoes(int valorOperacao) 
	{
		this.operacoes = valorOperacao;
	}
}

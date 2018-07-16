package Trabalho2;
/**
 * Trabalho 2 de Computação Paralela e Sistemas Distribuídos;
 * Criação de um serviço replicado de um Banco, utilizando threads com comunicação 
 * via Pipe Distinto não compartilhado;
 * 
 * @author Marcos Paulo de Castro
 */

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main 
{

	public static void main(String[] args) 
	{
		try 
		{
			//Criando meus canais de comunicação
			PipedOutputStream outDep1 = new PipedOutputStream();
			PipedOutputStream outRet1 = new PipedOutputStream();
			PipedOutputStream outApl1 = new PipedOutputStream();
			
			PipedOutputStream outDep2 = new PipedOutputStream();
			PipedOutputStream outRet2 = new PipedOutputStream();
			PipedOutputStream outApl2 = new PipedOutputStream();
			
			PipedOutputStream outDep3 = new PipedOutputStream();
			PipedOutputStream outRet3 = new PipedOutputStream();
			PipedOutputStream outApl3 = new PipedOutputStream();
			
			PipedInputStream inDep1 = new PipedInputStream(outDep1);
			PipedInputStream inRet1 = new PipedInputStream(outRet1);
			PipedInputStream inApl1 = new PipedInputStream(outApl1);
			
			PipedInputStream inDep2 = new PipedInputStream(outDep2);
			PipedInputStream inRet2 = new PipedInputStream(outRet2);
			PipedInputStream inApl2 = new PipedInputStream(outApl2);
			
			PipedInputStream inDep3 = new PipedInputStream(outDep3);
			PipedInputStream inRet3 = new PipedInputStream(outRet3);
			PipedInputStream inApl3 = new PipedInputStream(outApl3);
			
			//Objeto compartilhado contendo o controle dos servidores e das operações
			FluxoDeControle fluxoControle = new FluxoDeControle();
						
			//Intanciando os 3 clientes, identificados com o id da operação e cada um recebe
			//um tipo de operação, sendo as saídas (out)
			Cliente clienteDep = new Cliente(1, outDep1, outDep2, outDep3);
			Cliente clienteRet = new Cliente(2, outRet1, outRet2, outRet3);
			Cliente clienteApl = new Cliente(3, outApl1, outApl2, outApl3);
			
			//Instanciando os 3 servidores,  identificados com seu id estático e recebendo as as 3 entrada de dados (in)
			//referentes aos 3 tipos de operacao e o objeto compartilhado chamado fluxoControle
			Servidor servidor1 = new Servidor(1, inDep1, inRet1, inApl1, fluxoControle);
			Servidor servidor2 = new Servidor(2, inDep2, inRet2, inApl2, fluxoControle);
			Servidor servidor3 = new Servidor(3, inDep3, inRet3, inApl3, fluxoControle);
			
			//Startando as threads dos clientes
			clienteDep.start();
			clienteRet.start();
			clienteApl.start();
			
			//Startando as threads dos servidores
			servidor1.start();
			servidor2.start();
			servidor3.start();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

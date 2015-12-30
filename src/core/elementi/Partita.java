package core.elementi;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import core.sconti.Sconto;
import core.utente.Cliente;

public class Partita 
{
	public Partita(GregorianCalendar data, String squadraA , String squadraB, String idStadio,double ora,double prezzoBase)
	{
		this.data = data;
		this.squadraA = squadraA;
		this.squadraB = squadraB;
		this.idStadio = idStadio;
		this.ora = ora;
		this.prezzoBase = prezzoBase;
		this.sconti= new ArrayList<>();
	}


	public String getGame()
	{
		return squadraA + " vs " + squadraB;
	}


	public GregorianCalendar getData()
	{
		return data;
	}

	public String getSquadraA() 
	{
		return squadraA;
	}

	public String getSquadraB() 
	{
		return squadraB;
	}

	public String getIdStadio() 
	{
		return idStadio;
	}
	
	public double getOra()
	{
		return ora;
	}
	

	
	public void aggiungiSconti(ArrayList<Sconto> scontiGlobali)
	{
		for(Sconto s : scontiGlobali)
		{
			
			aggiungiSconto(s);
		}
	}
	
	public void aggiungiSconto(Sconto s)
	{
			if(s.verificaApplicabilita(this))
			{
				this.sconti.add(s);
			}	
	}
	
	
	public double generaPrezzoBiglietto(Cliente c)
	{
		double prezzo = prezzoBase;
		
		for(int i = 0 ; i < sconti.size() ; i++)
		{
			Sconto s = sconti.get(i);
			prezzo -= prezzo*(s.getPercentualeSconto(this, c)/100.0); 
			
		}
		return prezzo;
	
	}
	


	//sconti che si applicano alla singola partita
	private ArrayList<Sconto> sconti; 
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private String idStadio;
	private double ora;
	private double prezzoBase;
	
}

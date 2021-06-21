package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO dao;
	
	public Model() {
		dao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriod(Integer pd) {
		return dao.getCorsiByPeriod(pd);
	}
	
	public Map<Corso,Integer> getIscrittiByPeriod(Integer pd){
		return dao.getIscrittiByPeriod(pd);
	}
	
	public List<Studente> getStudentiByCorso(Corso corso) {
		return dao.getStudentiByCorso(corso);
	}
	
	public boolean esisteCorso(String codins) {
		return dao.esisteCorso(codins);
	}
	
	public Map<String, Integer> getDivisioneCDS(Corso corso) {
		List<Studente> studenti = dao.getStudentiByCorso(corso);
		Map<String, Integer> statistiche = new HashMap<String, Integer>();
		
		for(Studente s: studenti) {
			if (s.getCorsoDiStudio() != null && !s.getCorsoDiStudio().equals("")) 
			{
				if(statistiche.containsKey(s.getCorsoDiStudio()))
					statistiche.put(s.getCorsoDiStudio(), statistiche.get(s.getCorsoDiStudio()) + 1);
				else
					statistiche.put(s.getCorsoDiStudio(), 1);
			}
		}
		return statistiche;
	}
	
}

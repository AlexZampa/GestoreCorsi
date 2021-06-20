package it.polito.tdp.corsi.model;

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
	
}

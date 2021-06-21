package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;


public class CorsoDAO {
	
	public boolean esisteCorso(String codins) {
		String sql = "SELECT * FROM corso WHERE codins = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);		
			ResultSet res = st.executeQuery();
			
			if(res.next()) {
				conn.close();
				return true;
			}
			else {
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public List<Corso> getCorsiByPeriod(Integer pd) {
		String sql = "SELECT * FROM corso WHERE pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Corso c = new Corso(
						res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd"));
				result.add(c);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
	public Map<Corso,Integer> getIscrittiByPeriod(Integer pd) {
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot " +
				"FROM corso c, iscrizione i " +
				"WHERE c.codins = i.codins AND c.pd = ? " +
				"GROUP BY c.codins, c.nome, c.crediti, c.pd";
		Map<Corso,Integer> result = new HashMap<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Corso c = new Corso(
						res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd"));
				Integer n = res.getInt("tot");
				
				result.put(c, n);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
	public List<Studente> getStudentiByCorso(Corso corso) {
		String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS " +
				"FROM studente AS s, iscrizione AS i " +
				"WHERE s.matricola = i.matricola AND codins = ?";
		List<Studente> result = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, corso.getCodins());
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Studente s = new Studente(res.getInt("matricola"),
						res.getString("nome"),	
						res.getString("cognome"),
						res.getString("CDS"));
				result.add(s);
			}
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
	
	public Map<String, Integer> getDivisioneCDS(Corso corso) {
		String sql = "SELECT s.CDS, COUNT(*) AS tot "
				+ "FROM studente AS s, iscrizione AS i "
				+ "WHERE s.matricola = i.matricola AND s.CDS <> \"\" AND codins = ? "
				+ "GROUP BY s.CDS";
		
		Map<String, Integer> statistiche = new HashMap<String, Integer>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, corso.getCodins());
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				statistiche.put(res.getString("CDS"), res.getInt("tot"));
			}
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
			
		return statistiche;
	}
	
	
	
	
	
	
	
	
	
}

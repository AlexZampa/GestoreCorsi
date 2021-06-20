package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;


public class CorsoDAO {

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
}

package com.example.Bavl.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class StatService {
    private final JdbcTemplate jdbcTemplate;

    public StatService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getFacture(Integer idCommande) {
        String sql = "SELECT * FROM VueFacture WHERE idCommande = ?";
        return jdbcTemplate.queryForMap(sql, idCommande);
    }

    public List<Map<String, Object>> getInventaireProduits() {
        String sql = "SELECT * FROM VueInventaireProduits";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getVentesParProduit() {
        String sql = "SELECT * FROM VueVentesParProduit";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getDetailsCommandesClients() {
        String sql = "SELECT * FROM VueDetailsCommandesClients";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAnalyseCoutsProduction() {
        String sql = "SELECT * FROM VueAnalyseCoutsProduction";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getStatistiquesVentes(String periode) {
        String sql = "SELECT * FROM VueStatistiquesVentes";
        if (periode != null) {
            sql += switch (periode) {
                case "jour" -> " WHERE Date = CURRENT_DATE";
                case "semaine" -> " WHERE Date >= DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY)";
                case "mois" -> " WHERE Date >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)";
                default -> "";
            };
        }
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getPerformanceProduits() {
        String sql = "SELECT * FROM VuePerformanceProduits";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getTendancesCommandes() {
        String sql = "SELECT * FROM VueTendancesCommandes";
        return jdbcTemplate.queryForList(sql);
    }
} 
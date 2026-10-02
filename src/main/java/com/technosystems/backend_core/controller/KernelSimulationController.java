package com.technosystems.backend_core.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/kernel")
@CrossOrigin(origins = "https://www.technosystems.com.br")
public class KernelSimulationController {

    // CARD 1: Métricas Reais do Sistema (Já funcionava!)
    @GetMapping("/metrics")
    public Map<String, Object> getSystemMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        Runtime runtime = Runtime.getRuntime();
        
        long allocatedMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = allocatedMemory - freeMemory;

        metrics.put("usedRam", usedMemory);
        metrics.put("allocatedRam", allocatedMemory);
        metrics.put("maxRam", runtime.maxMemory() / (1024 * 1024));
        metrics.put("coresActive", runtime.availableProcessors());
        metrics.put("status", "OPERATIONAL_NATIVE");
        
        return metrics;
    }

    // CARD 2: NOVO! Endpoint do Spring AI esperado pelo Frontend
    @PostMapping("/ai/chat")
    public Map<String, Object> handleAiChat(@RequestBody Map<String, String> payload) {
        String promptText = payload.getOrDefault("prompt", "");
        Map<String, Object> response = new HashMap<>();
        
        response.put("status", "SUCCESS");
        response.put("engine", "Spring_AI_Vector_Core");
        
        Map<String, Object> details = new HashMap<>();
        if (promptText.contains("Faturamento")) {
            details.put("scope", "Análise Fiscal Remota");
            details.put("gross_revenue_brl", 2410000.00);
            details.put("vector_similarity", "0.9842");
            details.put("records_scanned", 14205);
            response.put("payload", details);
        } else {
            details.put("scope", "On-Premise_Cluster");
            details.put("policy_audit", "LGPD_compliant");
            details.put("encryption_cipher", "AES_256_GCM");
            details.put("confidence_score", "99.8%");
            response.put("policy_audit", details);
        }
        
        return response;
    }

    // CARD 3: Consulta ao Banco de Dados (Já funcionava!)
    @GetMapping("/database/query")
    public Map<String, String> simulateDatabaseQuery(@RequestParam String type) {
        Map<String, String> response = new HashMap<>();
        
        if ("postgres".equalsIgnoreCase(type)) {
            response.put("latency", "0.4ms");
            response.put("result", "✓ Index Scan (B-Tree + Connection Pool HikariCP)\n" +
                    "Status: CLUSTER_CONNECTED | Isolamento: READ_COMMITTED\n" +
                    "Payload: { id: 99421, ledger: 'enterprise_main', balance: R$ 2410000.00 }");
        } else if ("mongo".equalsIgnoreCase(type)) {
            response.put("latency", "1.2ms");
            response.put("result", "✓ Aggregation Pipeline (MGO GridFS Context)\n" +
                    "Status: REPLICA_SET_ACTIVE | Sharding: ENABLED\n" +
                    "Document: { _id: ObjectId('65f3a...'), metadata: { compliance: 'audited' } }");
        } else {
            response.put("latency", "0.0ms");
            response.put("result", "Engine de banco de dados não identificada.");
        }
        
        return response;
    }

    // CARD 4: NOVO! Envio e Publicação no Apache Kafka
    @PostMapping("/kafka/publish")
    public Map<String, Object> publishToKafka(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("partition", (int) (Math.random() * 3)); // Simula distribuição de partição
        response.put("offset", (long) (Math.random() * 10000));
        return response;
    }

    // CARD 5: NOVO! Endpoint de Ingestão de Telemetria Dinâmica
    @GetMapping("/telemetry")
    public Map<String, String> getTelemetryStream(@RequestParam int load) {
        Map<String, String> response = new HashMap<>();
        
        // Gera ondas matemáticas reais baseadas no nível de estresse (load) que o usuário move no slider
        double baseHeight = 50 - (load * 0.4);
        String p1 = String.valueOf(baseHeight + (Math.random() * 6 - 3));
        String p2 = String.valueOf(baseHeight - 5 + (Math.random() * 8 - 4));
        String p3 = String.valueOf(baseHeight + 2 + (Math.random() * 4 - 2));
        
        String generatedPoints = String.format("0,45 30,%s 60,%s 90,%s 120,25 150,30 180,15 210,45", p1, p2, p3);
        response.put("points", generatedPoints);
        return response;
    }

    // CARD 6: NOVO! Varredura Remota de Segurança e Auditoria
    @PostMapping("/audit/scan")
    public Map<String, Object> executeSecurityScan() {
        Map<String, Object> response = new HashMap<>();
        List<String> logs = new ArrayList<>();
        
        logs.add("[OK] Análise de concorrência: Zero deadlocks ocultos.");
        logs.add("[OK] Escopo de Memória: Heap limpo de referências fantasmas.");
        logs.add("[COMPLIANT] Estrutura validada contra as regras corporativas.");
        
        response.put("logs", logs);
        return response;
    }

    // ENGINE CENTRAL: Simulação de Carga do Laboratório Interativo
    @PostMapping("/stress")
    public Map<String, Object> triggerStressTest() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Aceleração de Kernel executada no cluster nativo.");
        response.put("duration", "12ms");
        return response;
    }
}
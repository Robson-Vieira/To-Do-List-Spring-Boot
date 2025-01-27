
DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `criacao` datetime(6) DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `prazo` datetime(6) NOT NULL,
  `prioridade` enum('ALTA','BAIXA','MEDIA') DEFAULT NULL,
  `status` enum('CONCLUIDA','EMANDAMENTO','PENDENTE') DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



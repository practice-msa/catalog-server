package msa.catalogserver.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.catalogserver.domain.CatalogEntity;
import msa.catalogserver.repository.CatalogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogRepository repository;

    @KafkaListener(topics = "example-order-topic")
    public void updateQty(String kafkaMessage){
        log.info("kafka message: ->"+kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        try {
            map = new ObjectMapper().readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        CatalogEntity catalogEntity = repository.findByProductId((String) map.get("productId"));
        catalogEntity.setStock(catalogEntity.getStock() - (Integer) map.get("qty"));

        repository.save(catalogEntity);
    }
}

package team.moebius.disposer.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenInfoMapper {

    private final ObjectMapper objectMapper;

    public TokenInfoMapper() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public String toJson(TokenInfo tokenInfo) throws JsonProcessingException {
        return objectMapper.writeValueAsString(tokenInfo);
    }

    public TokenInfo toTokenInfo(String jsonString) throws RuntimeException {
        try {
            return objectMapper.readValue(jsonString, TokenInfo.class);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
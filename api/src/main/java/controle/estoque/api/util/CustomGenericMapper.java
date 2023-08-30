package controle.estoque.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomGenericMapper {
    private final ObjectMapper objectMapper;
    public <T> T map(Object source, Class<T> destinationType) {
        return objectMapper.convertValue(source, destinationType);
    }

    public String stringifyObject(Object object ) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Não foi possível transformar o objeto em String";
    }
}
package TigerBank.ImportExport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class YamlFormat implements DataFormat {

  private final ObjectMapper objectMapper;

  public YamlFormat() {
    this.objectMapper = new ObjectMapper(new YAMLFactory())
        .registerModule(new JavaTimeModule())
        .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Override
  public void export(BankDataDTO data, Path filePath) throws IOException {
    objectMapper.writeValue(filePath.toFile(), data);
  }

  @Override
  public BankDataDTO importData(Path filePath) throws IOException {
    return objectMapper.readValue(filePath.toFile(), BankDataDTO.class);
  }

  @Override
  public String getFileExtension() {
    return ".yaml";
  }
}
package belaquaa.project.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "car")
public class CarConfig {
    private List<String> nonSortableFields;
}

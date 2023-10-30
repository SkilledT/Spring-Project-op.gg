package leagueoflegendsproject.Configurations;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.scheduling")
@EnableScheduling
@ConditionalOnProperty(prefix = "app.scheduling", name="enabled", havingValue="true", matchIfMissing = true)
public class SchedulingConfig {
    private boolean enabled;
}

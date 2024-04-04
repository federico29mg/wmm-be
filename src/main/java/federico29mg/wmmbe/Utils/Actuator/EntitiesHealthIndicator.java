package federico29mg.wmmbe.Utils.Actuator;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EntitiesHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        Reflections reflections = new Reflections("federico29mg.wmmbe.Entities", new SubTypesScanner(false));
        Set<Class> classes = new HashSet<>(reflections.getSubTypesOf(Object.class));
        Map<String, String> details = classes.stream().collect(Collectors.toMap(Class::getSimpleName, Class::descriptorString));

        Health.Builder status = Health.up();
        if(details.isEmpty()) {
            status = Health.down();
        }

        return status.withDetails(details).build();
    }
}

package ph.edu.cspb.ticketing.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.InetSocketAddress;

@Configuration
@Profile("dev")
public class EmbeddedMongoConfig implements DisposableBean {

    private MongoServer server;
    private InetSocketAddress address;

    @Bean
    public MongoClient mongoClient() {
        server = new MongoServer(new MemoryBackend());
        address = server.bind();
        return MongoClients.create("mongodb://" + address.getHostName() + ":" + address.getPort());
    }

    @Override
    public void destroy() {
        if (server != null) {
            server.shutdownNow();
        }
    }
}

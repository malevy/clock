package net.malevy.clock.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.hazelcast.leader.LeaderInitiator;

@Profile("!test")
@Configuration
public class ClusterConfig {

    @Bean
    public HazelcastInstance cacheInstance() {
        return Hazelcast.newHazelcastInstance(new Config());
    }

    /**
     * the default behavior of the LeaderInitiator will be to publish
     * an onGrantedEvent and onRevokedEvent as leadership is assigned
     * and removed from the nodes that make up the cluster
     * @return
     */
    @Bean
    public LeaderInitiator leaderInitiator() {
        return new LeaderInitiator(cacheInstance());
    }

}

package com.example;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
@Getter
public class EmbededMongoDb {

    public final String localhost = "localhost";
    // TODO some alternative ? https://github.com/spring-projects/spring-framework/issues/28052
    public final int port = SocketUtils.findAvailableTcpPort();

    private MongodExecutable mongodExecutable;

    @PostConstruct
    private void postConstruct() throws IOException {
        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(localhost, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

    }

    @PreDestroy
    public void preDestroy() {
        mongodExecutable.stop();
    }


}

package play.mongo.embedded;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.ITempNaming;
import de.flapdoodle.embed.process.extract.UUIDTempNaming;
import de.flapdoodle.embed.process.io.directories.FixedPath;
import de.flapdoodle.embed.process.io.directories.IDirectory;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class})
public class AppTest {

    @Bean
    public MongoClient mongoClient() throws Exception {

        int port = 12345;

        Command command = Command.MongoD;

        Storage storage = new Storage("C:\\embeddedmongo", null, 0);

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.V4_0)
                .replication(storage)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        IDirectory iDirectory = new FixedPath("C:\\embeddedmongo");
        ITempNaming iTempNaming = new UUIDTempNaming();

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(new ExtractedArtifactStoreBuilder()
                        .defaults(command)
                        .download(new DownloadConfigBuilder()
                                .defaultsForCommand(command)
                                .downloadPrefix("")
                                .downloadPath("file:\\C:\\embeddedmongo\\") //this where it will be downloaded from
                                //if you don't have mongo here FileNotFoundException will be thrown
                                .artifactStorePath(iDirectory)
                                .build())
                        .executableNaming(iTempNaming))
                .build();

        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);

        MongodExecutable mongodExecutable;

        mongodExecutable = runtime.prepare(mongodConfig);
        mongodExecutable.start();

        return new MongoClient("localhost", port);
    }
}

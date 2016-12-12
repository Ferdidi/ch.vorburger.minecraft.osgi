package ch.vorburger.minecraft.osgi.testplugin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandsSetUp {

    void register() {
        Sponge.getCommandManager()
            .register(null, helloCommandSpec(), "helloworld", "hello", "test");
    }

    CommandSpec helloCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Hello World Command"))
                .executor(new HelloWorldCommand())
                .build();
    }
}

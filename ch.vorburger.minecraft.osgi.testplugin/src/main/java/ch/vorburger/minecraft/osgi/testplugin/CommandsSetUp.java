/**
 * ch.vorburger.minecraft.osgi
 *
 * Copyright (C) 2016 - 2017 Michael Vorburger.ch <mike@vorburger.ch>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.vorburger.minecraft.osgi.testplugin;

import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandsSetUp {

/*
    TODO Build a general adapter from HelloWorldCommandRegistration to do something like this,
    so that this Plugin COULD also be used as a non-OSGi standard Sponge plugin?

    Optional<CommandMapping> register() {
        return Sponge.getCommandManager()
            .register(null, helloCommandSpec(), "helloworld", "hello", "test");
    }
*/
    CommandSpec helloCommandSpec() {
        return CommandSpec.builder()
                .description(Text.of("Hello World Command"))
                .executor(new HelloWorldCommand())
                .build();
    }
}

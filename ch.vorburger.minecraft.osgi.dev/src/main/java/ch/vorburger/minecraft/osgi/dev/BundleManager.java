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
package ch.vorburger.minecraft.osgi.dev;

import ch.vorburger.minecraft.osgi.dev.internal.infratomove.CommandExceptions;
import ch.vorburger.minecraft.osgi.dev.internal.infratomove.MessageReceivers;
import ch.vorburger.osgi.gradle.SourceInstallService;
import java.io.File;
import java.net.URI;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.format.TextColors;

/**
 * Service to manage OSGi bundles.
 *
 * This layer is Minecraft specific; i.e. it includes Player UI feedback etc.
 *
 * This layer is NOT Minecraft command/event specific.
 *
 * @author Michael Vorburger.ch
 */
public class BundleManager {

    private final SourceInstallService sourceInstallService;

    public BundleManager(SourceInstallService sourceInstallService) {
        this.sourceInstallService = sourceInstallService;
    }

    // TODO something strongly typed instead of String, like choice-of local-fs VS git VS github VS userProject, and then a parser for it

    public void installBundle(CommandSource commandSource, String bundleUriAsString) throws CommandException {
        URI bundleURI = CommandExceptions.doAndWrap("interpret as URI: " + bundleUriAsString, () -> new URI(bundleUriAsString));
        File bundleFile = new File(bundleURI);
        installBundle(commandSource, bundleFile);
    }
    // public void installBundle(User user, String bundleURI) {

    // TODO keep bundle running when user logs out, or stop?
    public void installBundle(User user, File bundleFileOrDirectory) {
        MessageReceiver messageReceiver = MessageReceivers.from(user);
        installBundle(messageReceiver, bundleFileOrDirectory);
    }

    private void installBundle(MessageReceiver commandSource, File bundleFileOrDirectory) {
        // TODO create and pass-through some sort of abstraction with a getDescription() so for projects it doesn't show absolute file URI
        // or (!) make project and URI type?  Look at PAX
        commandSource.sendMessage(Text.builder("OK; will install " + bundleFileOrDirectory).build());
        MessageReceivers.addCallback(sourceInstallService.installSourceBundle(bundleFileOrDirectory),
                commandSource, bundle -> {
                    bundle.start();
                    commandSource.sendMessage(Text.builder("Successful (built and) installed " + bundleFileOrDirectory).color(TextColors.GREEN).append().build());
                });
    }
}

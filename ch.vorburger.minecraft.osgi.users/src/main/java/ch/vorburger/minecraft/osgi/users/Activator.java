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
package ch.vorburger.minecraft.osgi.users;

import ch.vorburger.minecraft.osgi.dev.BundleManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.spongepowered.api.event.EventListener;

// TODO remove once @Component on UsersPlugin (UsersComponent?) works
public class Activator implements BundleActivator {

    private BundleManager bundleManager;

    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<BundleManager> ref = context.getServiceReference(BundleManager.class);
        if (ref != null) {
            bundleManager = context.getService(ref);
            PlayerJoinListener playerJoinListener = new PlayerJoinListener(bundleManager);
            context.registerService(EventListener.class, playerJoinListener, null);
        } else {
            throw new IllegalStateException("SourceInstallService not found (yet, change start order; DS SCR later)");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}

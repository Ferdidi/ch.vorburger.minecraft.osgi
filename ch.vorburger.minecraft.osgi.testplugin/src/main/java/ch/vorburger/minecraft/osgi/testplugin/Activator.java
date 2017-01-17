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

import ch.vorburger.minecraft.osgi.api.CommandRegistration;
import ch.vorburger.minecraft.osgi.api.Listeners;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO remove this entire class when switching to annotation-based declarative services..
public class Activator implements BundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    @Override
    public void start(BundleContext context) throws Exception {
        // System.out.println("STDOUT started!");
        LOG.info("starting and registering command..");
        try {
            // new CommandsSetUp().register();
            // TODO remove this when switching to annotation-based declarative services..
            context.registerService(CommandRegistration.class, new HelloWorldCommandRegistration(), null);
            context.registerService(Listeners.class, new ExampleListeners(), null);
        } catch (Throwable t) {
            // we MUST catch and log, because Felix itself does not, and this gets lost..
            LOG.error("boum", t);
            // we rethrow, to fail the Bundle start
            throw t;
        }
        LOG.info("started!");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        try {
            // System.out.println("STDOUT stopped!");
            // NB: It's not required to release, this is automatic:
            // context.ungetService(helloWorldCommandRegistrationServiceReference.getReference());
            // helloWorldCommandRegistrationServiceReference.unregister();
            LOG.info("stopped!");
        } catch (Throwable t) {
            LOG.error("boum", t);
            throw t;
        }
    }

/*
    private LogService getLogService() {
        ServiceReference ref = context.getServiceReference(LogService.class.getName());
        if (ref != null) {
            LogService log = (LogService) context.getService(ref);
            return log;

        }
        return null;
    }
*/
}

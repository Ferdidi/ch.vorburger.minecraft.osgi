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
package ch.vorburger.minecraft.osgi.api.impl;

import ch.vorburger.minecraft.osgi.api.Listeners;
import java.util.Optional;
import org.osgi.framework.BundleContext;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

// TODO replace this with OSGi DS equivalent
public class ListenersTrackerCustomizer extends AbstractServiceTrackerCustomizer<Listeners, Object> {

    public ListenersTrackerCustomizer(BundleContext context, PluginContainer pluginContainer) {
        super(context, pluginContainer);
    }

    @Override
    protected Optional<Object> getRegistration(Listeners service) {
        Sponge.getEventManager().registerListeners(pluginContainer, service);
        return Optional.of(service);
    }

    @Override
    protected void removeRegistration(Object r) {
        Sponge.getEventManager().unregisterListeners(r);
    }

}

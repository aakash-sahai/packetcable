package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.packetcable.packetcable.policy.server.impl.rev140131;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.packetcable.provider.PacketcableProvider;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketcableProviderModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.packetcable.packetcable.policy.server.impl.rev140131.AbstractPacketcableProviderModule {
    private static final Logger logger = LoggerFactory.getLogger(PacketcableProviderModule.class);

    public PacketcableProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public PacketcableProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.packetcable.packetcable.policy.server.impl.rev140131.PacketcableProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        logger.info("Creating PacketcableProvider instance");

        final DataBroker dataBrokerService = getDataBrokerDependency();
        final PacketcableProvider provider = new PacketcableProvider(dataBrokerService);

        final ListenerRegistration<DataChangeListener> ccapDataChangeListenerRegistration =
                dataBrokerService.registerDataChangeListener(LogicalDatastoreType.CONFIGURATION,
                        PacketcableProvider.ccapIID, provider, DataBroker.DataChangeScope.SUBTREE );

        final ListenerRegistration<DataChangeListener> qosDataChangeListenerRegistration =
                dataBrokerService.registerDataChangeListener(LogicalDatastoreType.CONFIGURATION,
                        PacketcableProvider.qosIID, provider, DataBroker.DataChangeScope.SUBTREE );


        logger.info("PacketCableProvider Registered with DataBroker");
        return provider;
    }

}
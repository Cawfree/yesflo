package io.github.cawfree.yesflo.util;

/**
 * Created by cawfree on 26/03/17.
 */

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.github.cawfree.yesflo.data.Catalogue;
import io.github.cawfree.yesflo.elements.Component;
import io.github.cawfree.yesflo.elements.Connection;
import io.github.cawfree.yesflo.elements.Param;
import io.github.cawfree.yesflo.elements.Process;
import io.github.cawfree.yesflo.elements.Port;
import io.github.cawfree.yesflo.elements.Terminal;

/** A collection of algorithms for parsing FlowHub diagrams in their JSON form. */
public final class FlowHubGlobal { /** TODO: Rename. */

    /** TODO: How to infer route information? */
    /* Static Declarations. */
    public static final int VALUE_ROUTE_UNKNOWN = 0; // Assert the default.

    /** TODO: Use the associated Library to build the corresponding Processes, so we can use actual references and not mappings. */

    /** Returns the equivalent Catalogue of the Diagram. */
    public static final Catalogue getCatalogue(final JsonNode pJsonNode) {
        // Allocate the HashMap.
        final Map<String, Component> lHashMap  = new HashMap<>();
        // Iterate the Nodes.
        final Iterator<String>       lIterator = pJsonNode.fieldNames();
        // Iterate the Fields.
        while(lIterator.hasNext()) {
            // Fetch the next Component Reference.
            final String   lComponent     = lIterator.next();
            // Fetch the ComponentNode.
            final JsonNode lComponentNode = pJsonNode.at("/"+lComponent);
            // Fetch the Name.
            final String   lName          = lComponentNode.at("/name").asText();
            final String   lDescription   = lComponentNode.at("/description").asText();
            final String   lIcon          = lComponentNode.at("/icon").asText();
            // Fetch the Inports and Outports.
            final JsonNode lInportNodes   = lComponentNode.at("/inports");
            final JsonNode lOutportNodes  = lComponentNode.at("/outports");
            // Allocate the Inports and Outports.
            final List<Param> lInports    = new ArrayList<>(lInportNodes.size());
            final List<Param> lOutports   = new ArrayList<>(lOutportNodes.size());
            // Iterate the Inports.
            for(int i = 0; i < lInportNodes.size(); i++) {
                // Fetch the next entry.
                final JsonNode       lInportNode = lInportNodes.get(i);
                // Allocate a new Inport.
                final String         lInportName = lInportNode.at("/name").asText();
                final String         lType       = lInportNode.at("/type").asText();
                // Allocate a new Inport.
                final Param          lInport     = new Param(lInportName, lType);
                // Buffer the Inports.
                lInports.add(lInport);
            }
            // Iterate the Outports.
            for(int i = 0; i < lOutportNodes.size(); i++) {
                // Fetch the next entry.
                final JsonNode lOutportNode = lOutportNodes.get(i);
                // Allocate a new Outport.
                final String         lOutportName = lOutportNode.at("/name").asText();
                final String         lType        = lOutportNode.at("/type").asText();
                // Allocate a new Outport.
                final Param          lOutport     = new Param(lOutportName, lType);
                // Buffer the Outport.
                lOutports.add(lOutport);
            }
            // Buffer the Component.
            lHashMap.put(lComponent, new Component(lName, lDescription, lIcon, lInports, lOutports));
        }
        // Return the Catalogue. (Since The Graph buffers all the components in a single Catalogue, we'll assert the equivalent regex.)
        return new Catalogue(lHashMap, "*");
    }

    /** Fetches the Ports of the Graph. */
    public static final List<Port> onFetchPorts(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Allocate a List to buffer the Parameters.
        final List<Port> lPorts = new ArrayList<>();
        // Iterate the Nodes.
        final Iterator<String> lIterator    = pJsonNode.fieldNames();
        // Iterate the Fields.
        while(lIterator.hasNext()) {
            // Fetch the next Field.
            final String   lPortDeclaration = lIterator.next(); // i.e. "prev"
            // Process the Port's dependencies.
            final JsonNode lPortNode        = pJsonNode.at("/" + lPortDeclaration);
            // Allocate the Port and buffer it.
            lPorts.add(FlowHubGlobal.getPort(lPortNode, pProcessMap));
        }
        // Return the Parameters.
        return lPorts;
    }

    /** Converts a JsonNode to a corresponding Port. */
    public static final Port getPort(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Fetch the associated Process.
        final Process  lProcess         = pProcessMap.get(pJsonNode.at("/process").asText()); // i.e. "routers\/KickRouter_bzaiw"
        // Fetch the Port. TODO: Although the names seem shared, they might be the ports that we're connecting to! (likely the diagram use name)
        final String   lPort            = pJsonNode.at("/port").asText(); // i.e. "prev"
        // Allocate the Terminal for the Process and Port defining the Connection.
        final Terminal lTerminal        = new Terminal(lProcess, lPort);
        // Fetch the MetaData.
        final JsonNode lMetaData        = pJsonNode.at("/metadata");
        // Fetch the boundaries of the Port. /** TODO: To bounds? */
        final int      lX               = lMetaData.at("/x").asInt();
        final int      lY               = lMetaData.at("/y").asInt();
        final int      lWidth           = lMetaData.at("/width").asInt();
        final int      lHeight          = lMetaData.at("/height").asInt();
        // Buffer a new Parameter.
        return new Port(lTerminal, lX, lY, lWidth, lHeight);
    }

    /** Fetches the Processes defined on the Graph. */
    public static final List<Process> onFetchProcesses(final JsonNode pJsonNode, final Catalogue<Component> pCatalogue, final Map<String, Process> pProcessMap) {
        // Allocate a List of Processes for easier use than iterating the ProcessMap.
        final List<Process> lProcesses = new ArrayList<>();
        // Iterate the Nodes.
        final Iterator<String> lIterator = pJsonNode.fieldNames();
        // Iterate the Fields.
        while (lIterator.hasNext()) {
            // Fetch the ProcessName.
            final String   lProcessName = lIterator.next(); // i.e. dom\/GetElement_f4nkd
            // Fetch the corresponding JsonNode.
            final JsonNode lJsonNode    = pJsonNode.at("/" + lProcessName);
            // Fetch the Component. /** TODO: I think this is the algorithm reference */
            final String   lComponent   = lJsonNode.at("/component").asText();
            // Fetch the MetaData.
            final JsonNode lMetaData    = lJsonNode.at("/metadata");
            // Fetch the boundaries and label assigned to this called Process.
            final int    lX        = lMetaData.at("/x").asInt();
            final int    lY        = lMetaData.at("/y").asInt();
            final int    lWidth    = lMetaData.at("/width").asInt();
            final int    lHeight   = lMetaData.at("/height").asInt();
            final String lLabel    = lMetaData.at("/label").asText(); // i.e. "startButton"
            // Allocate a Process.
            final Process lProcess = new Process<>(pCatalogue.getLibrary().get(lComponent), lX, lY, lWidth, lHeight, lLabel);
            // Buffer a new Process into the Map.
            pProcessMap.put(lProcessName, lProcess);
            // Add the buffered Process.
            lProcesses.add(lProcess);
        }
        // Return the Processes.
        return lProcesses;
    }

    /** Fetches the Connection on the Diagram. */
    public static final List<Connection> onFetchConnections(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Allocate a new List to hold the Connection.
        final List<Connection> lConnections = new ArrayList<>(pJsonNode.size());
        // Iterate the Connection.
        for(int i = 0; i < pJsonNode.size(); i++) {
            // Fetch the next Connection.
            final JsonNode   lConnectionNode = pJsonNode.get(i);
            // Render the corresponding Connection.
            final Connection lConnection     = FlowHubGlobal.getConnection(lConnectionNode, pProcessMap);
            // Buffer the Connection.
            lConnections.add(lConnection);
        }
        // Return the Connection.
        return lConnections;
    }

    /** Defines whether a JsonNode contains useful information. */
    public static final boolean isValidNode(final JsonNode pJsonNode) { /** TODO: There must be an equivalent Jackson. */
        // Ensure it's not null and it has some content.
        return pJsonNode != null && !pJsonNode.toString().isEmpty();
    }

    /** Deserializes a Connection from a JsonNode. */
    private static final Connection getConnection(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Fetch the Target. (Where we're carrying data to. (This is why we differentiate between Source/Data connections.))
        final JsonNode lTarget         = pJsonNode.at("/tgt");
        // Fetch the Target Information.
        final Process  lProcess        = pProcessMap.get(lTarget.at("/process").asText()); // i.e. "dom\/GetElement_f4nkd", a Process on the diagram.
        // Fetch the Port on the Target we'll pass the data into.
        final String   lPort           = lTarget.at("/port").asText(); // i.e. "element", the port on the target Process we're connecting. (*Not* the Component of that Process.)
        // Allocate the Target Terminal.
        final Terminal lTargetTerminal = new Terminal(lProcess, lPort);
        // Fetch the SourceNode.
        final JsonNode lSourceNode     = pJsonNode.at("/src");
        // Fetch the Route.
        final int      lRoute          = pJsonNode.at("/metadata").at("/route").asInt();
        // Is the SourceNode valid?
        if(FlowHubGlobal.isValidNode(lSourceNode)) { /** TODO: Cleaner implementation. */
            // Fetch the Process and the Port of the Source.
            final Process  lSourceProcess  = pProcessMap.get(lSourceNode.at("/process").asText()); // i.e. "dom\/GetElement_f4nkd" The diagram Process that's sourcing the data.
            final String   lSourcePort     = lSourceNode.at("/port").asText(); // i.e. "element"  The Port on the diagram Process we're connected to.
            // Allocate the SourceTerminal.
            final Terminal lSourceTerminal = new Terminal(lSourceProcess, lSourcePort);
            // Return a SourceConnection.
            return new Connection.Source(lSourceTerminal, lTargetTerminal, lRoute);
        }
        else {
            // Fetch the Value of the Data being transmitted.
            final String lDataValue     = pJsonNode.at("/data").asText();
            // Return the DataConnection.
            return new Connection.Data(lTargetTerminal, lDataValue, lRoute);
        }
    }

    /** Returns an equivalent Connection from a low-level internal definition, i.e. {"from":{"node":1,"port":"out0"},"to":{"node":2,"port":"in0"},"metadata":{}} */
    public static final Connection getConnectionRaw(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Parse the Target.
        final JsonNode lTarget  = pJsonNode.at("/to");
        // Fetch the Node and Port of the Target.
        final Process  lTargetProcess = pProcessMap.get(lTarget.at("/node").asText());
        final String   lTargetPort    = lTarget.at("/port").asText();
        // If we couldn't find the matching process, throw an error.
        if(lTargetProcess == null) {
            // We must be able to provide a sound catalogue of the contained Processes.
            throw new NullPointerException("Invalid Process reference.");
        }
        // Allocate a corresponding Terminal.
        final Terminal lTerminal = new Terminal(lTargetProcess, lTargetPort);
        // Fetch the Source.
        final JsonNode lSource   = pJsonNode.at("/from");
        // Ensure it exists.
        if(lSource != null) {
            // Fetch the SourceProcess and SourcePort.
            final Process lSourceProcess = pProcessMap.get(lSource.at("/node").asText());
            final String  lSourcePort    = lSource.at("/port").asText();
            // Allocate the SourceTerminal.
            final Terminal lSourceTerminal = new Terminal(lSourceProcess, lSourcePort);
            // Allocate the corresponding Connection.
            return new Connection.Source(lSourceTerminal, lTerminal, FlowHubGlobal.VALUE_ROUTE_UNKNOWN); /** TODO: Route is *not* a safe parameter to check equality with. */
        }
        else {
            /** TODO: Fix handling for DataConnections! */
            // We don't know how to catalogue DataConnections as of yet.
            throw new UnsupportedOperationException("DataConnection instantiation isn't yet supported.");
        }
    }

    /** Defines whether a Source Connection is cyclic. */
    public static final boolean isCyclic(final Connection.Source pSourceConnection, final List<Connection> pConnections) {
        // Use the core implementation.
        return FlowHubGlobal.isCyclic(pSourceConnection.getSource().getProcess(), pSourceConnection.getTarget().getProcess(), pConnections);
    }

    /** Defines whether two processes are Cyclic. */
    public static final boolean isCyclic(final Process pSourcing, final Process pSinking, final List<Connection> pConnections) {
        // Fetch the SourceConnections from the SinkingProcess.
        final List<Connection.Source> lSourceConnections = FlowHubGlobal.getSourceConnections(pSinking, pConnections);
        // Iterate the SourceConnections.
        for(final Connection.Source lSourceConnection : lSourceConnections) {
            // Fetch the Target Process.
            final Process lTargetProcess = lSourceConnection.getTarget().getProcess();
            // Is the TargetProcess the Source?
            if(lTargetProcess.equals(pSourcing) || FlowHubGlobal.isCyclic(pSourcing, lTargetProcess, pConnections)) {
                // The connection is cyclic!
                return true;
            }
        }
        // The process isn't cyclic.
        return false;
    }

    /** Fetches the Source Connections for a Process. */
    public static final List<Connection.Source> getSourceConnections(final Process pProcess, final List<Connection> pConnections) {
        // Allocate a List to hold the Source Connections.
        final List<Connection.Source> lConnections = new ArrayList<>();
        // Iterate the Connections.
        for(final Connection lConnection : pConnections) {  if(lConnection instanceof Connection.Source) {
            // Fetch the SourceConnection.
            final Connection.Source lSourceConnection = (Connection.Source)lConnection;
            // Is the Connection Sourced from the Process?
            if(lSourceConnection.getSource().getProcess().equals(pProcess)) {
                // Buffer the SourceConnection.
                lConnections.add(lSourceConnection);
            }
        } }
        // Return the SourceConnections.
        return lConnections;
    }

    /** Returns the Target Connections (Sinking) for a process. */
    public static final List<Connection> getTargetConnections(final Process pProcess, final List<Connection> pConnections) {
        // Allocate a List of the Target Connections.
        final List<Connection> lConnections = new ArrayList<>();
        // Iterate the Connections.
        for(final Connection lConnection : pConnections) {
            // Does the Connection target the Process?
            if(lConnection.getTarget().getProcess().equals(pProcess)) {
                // Add the Connection.
                lConnections.add(lConnection);
            }
        }
        // Return the Connections.
        return lConnections;
    }

    /** Defines whether a the drivers to a Port indicate it's unwired. */
    public static final <T extends Port> boolean isUnwired(final List<Connection> pConnections, final List<T> pPorts) {
        // Are they both empty?
        return pConnections.isEmpty() && pPorts.isEmpty();
    }

    /** Fetches a reference to the Ports that need to be wired for an existing Process. (Dataflow states that all data dependencies must be supplied.) */
    public static final List<Param> getUnwired(final Process pProcess, final List<Connection> pConnections, final List<Port> pPorts) {
        // Allocate a List to hold the Portd which are starved.
        final List<Param> lStarved     = new ArrayList<>();
        // Iterate the Process' Inports.
        for(int i = 0; i < pProcess.getComponent().getInports().size(); i++) {
            // Fetch the Param.
            final Param lInport = (Param)pProcess.getComponent().getInports().get(i); /** TODO: Clean up the generic implementation. */
            // Fetch the driving Ports and Connections to this Inport.
            final List<Connection> lConnections = FlowHubGlobal.getDrivingConnections(pProcess, lInport, pConnections);
            final List<Port>       lPorts       = FlowHubGlobal.getDrivingPorts(pProcess, lInport, pPorts);
            // Are there no Connections?
            if(FlowHubGlobal.isUnwired(lConnections, lPorts)) {
                // The Port is starved.
                lStarved.add(lInport);
            }
        }
        // Return the Starved Ports.
        return lStarved;
    }

    /** Fetches the drivers for a given Inport belonging to a Process. (Multiple drivers are unsupported in Dataflow!) */
    public static final List<Connection> getDrivingConnections(final Process pProcess, final Param pInport, final List<Connection> pConnections) {
        // Allocate a List for the Connections which drive. Ideally, there should be no more than one.
        final List<Connection> lConnections = new ArrayList<>(1);
        // Iterate the Connections.
        for(final Connection lConnection : pConnections) {
            // Does the Connection drive the Process?
            if(lConnection.getTarget().getProcess().equals(pProcess)) {
                // Okay; does it connect to the specified Inport?
                if(lConnection.getTarget().getPort().equals(pInport.getName())) {
                    // Buffer the Connection.
                    lConnections.add(lConnection);
                }
            }
        }
        // Return the Connections.
        return lConnections;
    }

    /** Fetches the driving diagram-level Ports for an Inport. (Multiple drivers are unsupported in Dataflow!) */
    public static final <V extends Port> List<V> getDrivingPorts(final Process pProcess, final Param pInport, final List<V> pPorts) {
        // Allocate a List for the Ports which drive. Ideally, there should be no more than one.
        final List<V> lPorts = new ArrayList<>(1);
        // Iterate the Ports.
        for(final V lPort : pPorts) {
            // Does the current Port drive the Process?
            if(lPort.getTerminal().getProcess().equals(pProcess)) {
                // Does it connect to the specified Port?
                if(lPort.getTerminal().getPort().equals(pInport.getName())) {
                    // Buffer the Port.
                    lPorts.add(lPort);
                }
            }
        }
        // Return the Ports.
        return lPorts;
    }

    /** Returns the data that's driving a given Process Inport. The expectatation is that at this stage we've ratified that there's only a single driver. */
    public static final <V extends Port, T> T getDrivingData(final Process pProcess, final Param pInport, final List<V> pPorts, final List<Connection> pDrivingConnections, final Map<V, T> pPortDataMap, final Map<Connection, T> pConnectionDataMap) {
        // Declare the Data we're going to be returning.
        T       lT       = null;
        // Define a boolean to determine if the data was found.
        boolean lIsFound = false;
        // Find the connection driving the Inport.
        for(final Connection lConnection : pDrivingConnections) {
            // Ensure we're handling appropriate drivers.
            if(!lConnection.getTarget().getProcess().equals(pProcess)) {
                // The developer supplied the wrong Connections. (They need to drive this process only.)
                throw new UnsupportedOperationException("Invalid driving connections.");
            }
            // Does the Connection target the Inport?
            if(lConnection.getTarget().getPort().equals(pInport.getName()) && lConnection.getTarget().getProcess().equals(pProcess)) {
                // Fetch the data.
                lT       = pConnectionDataMap.get(lConnection);
                // Assert that we found the data. (We can't use an 'is null' check because some drivers might want to return null.)
                lIsFound = true;
                // Stop iterating.
                break;
            }
        }
        // Could we not find the driving data?
        if(!lIsFound) {
            // Fetch the driving Inport.
            for(final V lPort : pPorts) {
                // Ensure we're handling the appropriate drivers.
                if(!lPort.getTerminal().getProcess().equals(pProcess)) {
                    // The developer supplied the wrong Ports. (They need to drive this process only.)
                    throw new UnsupportedOperationException("Invalid driving ports.");
                }
                // Does the Port target the Inport?
                if(lPort.getTerminal().getPort().equals(pInport.getName())) {
                    // Fetch the data.
                    lT       = pPortDataMap.get(lPort);
                    // Assert that we found the data.
                    lIsFound = true;
                    // Stop iterating.
                    break;
                }
            }
        }
        // Return the driving data.
        return lT;
    }

    /** TODO: Detect duplicate transmissions. */

    /** Linearly iterates through a FlowHub diagram. */
    public static final <U extends Component, T, V extends Port> void iterate(final Map<String, Process> pProcessMap, final List<V> pInports, final List<V> pOutports, final List<Connection> pConnections, final IFloListener<U, T, V> pFloListener) {
        // Allocate the Processes.
        final List<Process> lProcesses = new ArrayList<>();
        // Iterate the ProcessMap.
        for(final Map.Entry<String, Process> lEntry : pProcessMap.entrySet()) {
            // Buffer the Process.
            lProcesses.add(lEntry.getValue());
        }
        // Call the core implementation.
        FlowHubGlobal.iterate(lProcesses, pInports, pOutports, pConnections, pFloListener);
    }

    /** Linearly iterates through a FlowHub diagram. */ /** TODO: <T></T> */
    public static final <U extends Component, T, V extends Port> void iterate(final List<Process> pProcesses, final List<V> pInports, final List<V> pOutports, final List<Connection> pConnections, final IFloListener<U, T, V> pFloListener) {
        // Iterate the Connections.
        for(final Connection lConnection : pConnections) { if(lConnection instanceof Connection.Source) {
            // Fetch the SourceConnection.
            final Connection.Source lSourceConnection = (Connection.Source)lConnection;
            // Determine if it's cyclic.
            if(FlowHubGlobal.isCyclic(lSourceConnection, pConnections)) {
                // Don't permit execution of the graph.
                throw new IllegalStateException("Cyclic configurations are unsupported.");
            }
        } }
        // Iterate the Processes.
        for(final Process lProcess : pProcesses) {
            // Iterate the Process' ports.
            for(int i = 0; i < lProcess.getComponent().getInports().size(); i++) {
                // Fetch the Inport.
                final Param            lInport = (Param)lProcess.getComponent().getInports().get(i); /** TODO: Clean up generic implementation. */
                // Fetch the Driving Sources.
                final List<Connection> lDrivers = FlowHubGlobal.getDrivingConnections(lProcess, lInport, pConnections);
                final List<V>          lPorts   = FlowHubGlobal.getDrivingPorts(lProcess, lInport, pInports);
                // Are there no drivers?
                if(FlowHubGlobal.isUnwired(lDrivers, lPorts)) { /** TODO: Externalize this check. */
                    // We don't support unwired Ports; *all* connections are required until we find a good architecture for handling default types.
                    throw new UnsupportedOperationException("Diagram cannot be executed. Not all Inports have been wired.");
                }
                // Do we have instances of multiple drivers?
                if(lDrivers.size() > 1) {
                    // We don't support multiple drivers; dataflow doesn't work this way.
                    throw new UnsupportedOperationException("Diagram cannot be executed. Detected multiple writes to a single Inport.");
                }
            }
        }
        // Make a copy of the Processes that are left to execute.
        final List<Process>           lProcesses   = new ArrayList<>(pProcesses);
        // Make a local copy of the Connection; we'll be using this to determine which wires must be propagated next.
        final List<Connection>        lConnections = new ArrayList<>(pConnections);
        // The Outports which have yet to be driven to.
        final List<V>                 lOutports    = new ArrayList<>(pOutports);
        // Declare the DataMap; defines which data propagates across which Connection.
        final Map<Connection, T>      lDataMap     = new HashMap<>(); /** TODO:ensure connections match datamap size */
        final Map<V, T>               lPortDataMap = new HashMap<>();
        // Iterate across the Inports. (Inputs to the Diagram.)
        for(final V lPort : pInports) {
            // Fetch the Port's Data.
            final T lData = pFloListener.getInportValue(lPort);
            // Buffer this into the PortDataMap.
            lPortDataMap.put(lPort, lData);


            // Iterate the Connection connected to this Port.
            /*for(final Connection lConnection : lConnections) {
                // Is it a SourceConnection?
                if(lConnection instanceof Connection.Source) {
                    // Cast accordingly.
                    final Connection.Source lSourceConnection = (Connection.Source)lConnection;
                    // Determine if the SourceConnection's Target matches the Parameter Definition. (Target represents the Parameter's passage for data.)
                    if(lPort.getTerminal().getProcess().equals(lSourceConnection.getTarget().getProcess()) && lPort.getTerminal().getPort().equals(lSourceConnection.getTarget().getPort())) {
                        // Prepare the Data carried for this connection. (Don't remove it; we have yet to process the propagation of this connection.)
                        lDataMap.put(lConnection, pFloListener.getInportValue(lPort));
                    }
                }
            }*/


        }
        // Iterate the DataConnections.
        for(final Connection lConnection : lConnections) {
            // Determine if it's a DataConnection.
            if(lConnection instanceof Connection.Data) { /** TODO: Courier */
                // Cast Accordingly.
                final Connection.Data lDataConnection = (Connection.Data)lConnection;
                // Buffer the Data Constant into the DataMap.
                lDataMap.put(lDataConnection, pFloListener.valueOf(lDataConnection));
            }
        }
        // Whilst there are Processes left to execute...
        while(lProcesses.size() > 0) {
            // Iterate the Processes that we have left to execute.
            for(int j = lProcesses.size() - 1; j >= 0; j--) { /** TODO: Only need to generate these lists once... */
                // Fetch the Process.
                final Process<U>       lProcess  = lProcesses.get(j);
                // Allocate a List of the Sinking and Sourcing Connection to this Process.
                final List<V>          lPorts    = new ArrayList<>();
                final List<Connection> lSourcing = new ArrayList<>(); // Inputs to a Process. (The *target* to supply data into.)
                final List<Connection> lSinking  = new ArrayList<>(); // Outputs from Processes. (The *source* of Process data.)
                // Iterate the Connections.
                for(final Connection lConnection : lConnections) {
                    // Determine if the Connection is providing data to this process.
                    if(lConnection.getTarget().getProcess().equals(lProcess)) {
                        // Buffer the Connection as Sourcing.
                        lSourcing.add(lConnection);
                    }
                    // Determine if we're handling a SourceConnection.
                    if(lConnection instanceof Connection.Source) {
                        // Cast accordingly.
                        final Connection.Source lSourceConnection = (Connection.Source)lConnection;
                        // Check if this SourceConnection carries data propagated from this Terminal.
                        if(lSourceConnection.getSource().getProcess().equals(lProcess)) {
                            // Buffer the Connection as Sinking. (Output.)
                            lSinking.add(lConnection);
                        }
                    }
                }
                // Iterate the Ports.
                for(final V lPort : pInports) {
                    // Does it source to the Process?
                    if(lPort.getTerminal().getProcess().equals(lProcess)) {
                        // Add the Ports.
                        lPorts.add(lPort);
                    }
                }
                // Determine if the Process is permitted to execute.
                boolean lIsExecutable = true;
                // Iterate the Sourcing. (We don't need to do this with Ports, because the FloListener guarantees data will be supplied by them.)
                for(int i = 0; i < lSourcing.size() && lIsExecutable; i++) {
                    // Ensure there's a matching entry for this Connection in the DataMap.
                    lIsExecutable &= lDataMap.containsKey(lSourcing.get(i));
                }
                // Is the Process Executable?
                if(lIsExecutable) {
                    // Execute the Process. (The expectation is that the results for each Sinking connection are now within the DataMap.)
                    pFloListener.onExecutionOf(lProcess, lPorts, lSourcing, lSinking, lPortDataMap, lDataMap);
                    // Iterate the Sourcing.
                    for(int i = lSourcing.size() - 1; i >= 0; i--) {
                        // Fetch the Sourcing Connection.
                        final Connection lConnection = lSourcing.get(i);
                        // Remove this Connection from the Connection and the DataMap. /** TODO: If we choose not to clear the DataMap, we'll get a interesting representation of all data carried by the map. May be useful. */
                        lConnections.remove(lConnection);
                        lDataMap.remove(lConnection);
                    }
                    // Iterate the Sinking.
                    for(final Connection lConnection : lSinking) {
                        // Here we *know* all Connection are SourceConnections, but better safe than sorry.
                        if(lConnection instanceof Connection.Source) {
                            // Cast accordingly.
                            final Connection.Source lSourceConnection = (Connection.Source)lConnection;
                            // Fetch the Connection's data.
                            final T                lResult           = lDataMap.get(lConnection);
                            // Iterate the Outports.
                            for(int i = lOutports.size() - 1; i >= 0; i--) {
                                // Fetch the Parameter.
                                final V lPort = lOutports.get(i);
                                // Determine if the Output is wired to the same Process.
                                if(lPort.getTerminal().getProcess().equals(lSourceConnection.getSource().getProcess()) && lPort.getTerminal().getPort().equals(lSourceConnection.getSource().getPort())) {
                                    // Have the Outport handle the Result.
                                    pFloListener.onOutportResponse(lPort, lResult);
                                    // Remove the Connection.
                                    lConnections.remove(lConnection);
                                    // Remove the Outport.
                                    lOutports.remove(lPort);
                                    // Finish iterating the Outports.
                                    break;
                                }
                            }
                        }
                        else {
                            // This is an impossible state.
                            throw new UnsupportedOperationException("Diagram is corrupt!");
                        }
                    }
                    // Remove the Process.
                    lProcesses.remove(lProcess);
                }
            }
        }
    }

    /** Prevent external instantiation. */
    private FlowHubGlobal() { }
}

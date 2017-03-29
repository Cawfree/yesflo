package io.github.cawfree.yesflo;

/**
 * Created by cawfree on 26/03/17.
 */

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.github.cawfree.yesflo.components.Connection;
import io.github.cawfree.yesflo.components.Process;
import io.github.cawfree.yesflo.components.Parameter;
import io.github.cawfree.yesflo.components.Terminal;
import io.github.cawfree.yesflo.util.IFloListener;

/** A collection of algorithms for parsing FlowHub diagrams in their JSON form. */
public final class FlowHubTools {

    /** Fetches the Ports of the Graph. */
    public static final List<Parameter> onFetchPorts(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
        // Allocate a List to buffer the Parameters.
        final List<Parameter>  lParameters = new ArrayList<>();
        // Iterate the Nodes.
        final Iterator<String> lIterator   = pJsonNode.fieldNames();
        // Iterate the Fields.
        while(lIterator.hasNext()) {
            // Fetch the next Field.
            final String   lPortDeclaration = lIterator.next(); // i.e. "prev"
            // Process the Port's dependencies.
            final JsonNode lPortNode        = pJsonNode.at("/" + lPortDeclaration);
            // Fetch the associated Process.
            final Process  lProcess         = pProcessMap.get(lPortNode.at("/process").asText()); // i.e. "routers\/KickRouter_bzaiw"
            // Fetch the Port. TODO: Although the names seem shared, they might be the ports that we're connecting to! (likely the diagram use name)
            final String   lPort            = lPortNode.at("/port").asText(); // i.e. "prev"
            // Allocate the Terminal for the Process and Port defining the Connection.
            final Terminal lTerminal        = new Terminal(lProcess, lPort);
            // Fetch the MetaData.
            final JsonNode lMetaData        = lPortNode.at("/metadata");
            // Fetch the boundaries of the Port. /** TODO: To bounds? */
            final int      lX               = lMetaData.at("/x").asInt();
            final int      lY               = lMetaData.at("/y").asInt();
            final int      lWidth           = lMetaData.at("/width").asInt();
            final int      lHeight          = lMetaData.at("/height").asInt();
            // Buffer a new Parameter.
            lParameters.add(new Parameter(lTerminal, lX, lY, lWidth, lHeight));
        }
        // Return the Parameters.
        return lParameters;
    }

    /** Fetches the Processes defined on the Graph. */
    public static final List<Process> onFetchProcesses(final JsonNode pJsonNode, final Map<String, Process> pProcessMap) {
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
            final Process lProcess = new Process(lComponent, lX, lY, lWidth, lHeight, lLabel);
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
            final JsonNode lConnectionNode = pJsonNode.get(i);
            // Fetch the Target. (Where we're carrying data to. (This is why we differentiate between Source/Data connections.))
            final JsonNode lTarget         = lConnectionNode.at("/tgt");
            // Fetch the Target Information.
            final Process  lProcess        = pProcessMap.get(lTarget.at("/process").asText()); // i.e. "dom\/GetElement_f4nkd", a Process on the diagram.
            // Fetch the Port on the Target we'll pass the data into.
            final String   lPort           = lTarget.at("/port").asText(); // i.e. "element", the port on the target Process we're connecting. (*Not* the Component of that Process.)
            // Allocate the Target Terminal.
            final Terminal lTargetTerminal = new Terminal(lProcess, lPort);
            // Fetch the SourceNode.
            final JsonNode lSourceNode     = lConnectionNode.at("/src");
            // Is the SourceNode valid?
            if(lSourceNode != null && !lSourceNode.toString().isEmpty()) { /** TODO: Cleaner implementation. */
                // Fetch the Process and the Port of the Source.
                final Process  lSourceProcess  = pProcessMap.get(lSourceNode.at("/process").asText()); // i.e. "dom\/GetElement_f4nkd" The diagram Process that's sourcing the data.
                final String   lSourcePort     = lSourceNode.at("/port").asText(); // i.e. "element"  The Port on the diagram Process we're connected to.
                // Allocate the SourceTerminal.
                final Terminal lSourceTerminal = new Terminal(lSourceProcess, lSourcePort);
                // Allocate the SourceConnection.
                lConnections.add(new Connection.Source(lSourceTerminal, lTargetTerminal));
            }
            else {
                // Fetch the Value of the Data being transmitted.
                final String lDataValue     = lConnectionNode.at("/data").asText();
                // Allocate the DataConnection.
                lConnections.add(new Connection.Data(lTargetTerminal, lDataValue));
            }
        }
        // Return the Connection.
        return lConnections;
    }

    /** Defines whether a Source Connection is cyclic. */
    public static final boolean isCyclic(final Connection.Source pSourceConnection, final List<Connection> pConnections) {
        // Use the core implementation.
        return FlowHubTools.isCyclic(pSourceConnection.getSource().getProcess(), pSourceConnection.getTarget().getProcess(), pConnections);
    }

    /** Defines whether two processes are Cyclic. */
    public static final boolean isCyclic(final Process pSourcing, final Process pSinking, final List<Connection> pConnections) {
        // Fetch the SourceConnections from the SinkingProcess.
        final List<Connection.Source> lSourceConnections = FlowHubTools.getSourceConnections(pSinking, pConnections);
        // Iterate the SourceConnections.
        for(final Connection.Source lSourceConnection : lSourceConnections) {
            // Fetch the Target Process.
            final Process lTargetProcess = lSourceConnection.getTarget().getProcess();
            // Is the TargetProcess the Source?
            if(lTargetProcess.equals(pSourcing) || FlowHubTools.isCyclic(pSourcing, lTargetProcess, pConnections)) {
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

    /** Linearly iterates through a FlowHub diagram. */ /** TODO: <T></T> */
    public static final <T> void iterate(final List<Process> pProcesses, final List<Parameter> pInports, final List<Parameter> pOutports, final List<Connection> pConnections, final IFloListener<T> pFloListener) {
        // Iterate the Connections.
        for(final Connection lConnection : pConnections) { if(lConnection instanceof Connection.Source) {
            // Fetch the SourceConnection.
            final Connection.Source lSourceConnection = (Connection.Source)lConnection;
            // Determine if it's cyclic.
            if(FlowHubTools.isCyclic(lSourceConnection, pConnections)) {
                // Don't permit execution of the graph.
                throw new IllegalStateException("Cyclic configurations are unsupported.");
            }
        } }
        // Make a copy of the Processes that are left to execute.
        final List<Process>           lProcesses   = new ArrayList<>(pProcesses);
        // Make a local copy of the Connection; we'll be using this to determine which wires must be propagated next.
        final List<Connection>        lConnections = new ArrayList<>(pConnections);
        // The Outports which have yet to be driven to.
        final List<Parameter>         lOutports    = new ArrayList<>(pOutports);
        // Declare the DataMap; defines which data propagates across which Connection.
        final Map<Connection, T>      lDataMap     = new HashMap<>(); /** TODO:ensure connections match datamap size */
        // Iterate across the Inports.
        for(final Parameter lParameter : pInports) {
            // Iterate the Connection connected to this Parameter.
            for(final Connection lConnection : lConnections) {
                // Is it a SourceConnection? /** TODO: To Courier! */
                if(lConnection instanceof Connection.Source) {
                    // Cast accordingly.
                    final Connection.Source lSourceConnection = (Connection.Source)lConnection;
                    // Determine if the SourceConnection's Target matches the Parameter Definition. (Target represents the Parameter's passage for data.)
                    if(lParameter.getTerminal().getProcess().equals(lSourceConnection.getTarget().getProcess()) && lParameter.getTerminal().getPort().equals(lSourceConnection.getTarget().getPort())) {
                        // Prepare the Data carried for this connection. (Don't remove it; we have yet to process the propagation of this connection.)
                        lDataMap.put(lConnection, pFloListener.getInportValue(lParameter));
                    }
                }
            }
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
        // Whilst there are Connection left to process..
        while(lConnections.size() > 0) {
            // Iterate the Processes that we have left to execute.
            for(int j = lProcesses.size() - 1; j >= 0; j--) { /** TODO: Only need to generate these lists once... */
                // Fetch the Process.
                final Process lProcess = lProcesses.get(j);
                // Allocate a List of the Sinking and Sourcing Connection to this Process.
                final List<Connection> lSourcing = new ArrayList<>(); // Inputs to a Process. (The *target* to supply data into.)
                final List<Connection> lSinking  = new ArrayList<>(); // Outputs from Processes. (The *source* of Process data.)
                // Iterate the Connection.
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
                // Determine if the Process is permitted to execute.
                boolean lIsExecutable = true;
                // Iterate the Sourcing.
                for(int i = 0; i < lSourcing.size() && lIsExecutable; i++) {
                    // Ensure there's a matching entry for this Connection in the DataMap.
                    lIsExecutable &= lDataMap.containsKey(lSourcing.get(i));
                }
                // Is the Process Executable?
                if(lIsExecutable) {
                    // Execute the Process. (The expectation is that the results for each Sinking connection are now within the DataMap.)
                    pFloListener.onExecutionOf(lProcess, lSourcing, lSinking, lDataMap);
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
                                final Parameter lParameter = lOutports.get(i);
                                // Determine if the Output is wired to the same Process.
                                if(lParameter.getTerminal().getProcess().equals(lSourceConnection.getSource().getProcess()) && lParameter.getTerminal().getPort().equals(lSourceConnection.getSource().getPort())) {
                                    // Have the Outport handle the Result.
                                    pFloListener.onOutportResponse(lParameter, lResult);
                                    // Remove the Connection.
                                    lConnections.remove(lConnection);
                                    // Remove the Outport.
                                    lOutports.remove(lParameter);
                                    // Finish iterating the Outports.
                                    break;
                                }
                            }
                        }
                    }
                    // Remove the Process.
                    lProcesses.remove(lProcess);
                }
            }
        }
    }

    /** Prevent external instantiation. */
    private FlowHubTools() { }
}

package io.github.cawfree.yesflo.util;

/**
 * Created by cawfree on 26/03/17.
 */

import java.util.List;
import java.util.Map;

import io.github.cawfree.yesflo.elements.Component;
import io.github.cawfree.yesflo.elements.Process;
import io.github.cawfree.yesflo.elements.Connection;
import io.github.cawfree.yesflo.elements.Port;

/** Defines a Listener for implementing execution states along a FlowHub graphs. */
public interface IFloListener<U extends Component, T, V extends Port> {
    /** Defines the data sourced by a DataConnection. (i.e. this is a diagram constant.) */
    T              valueOf(final Connection.Data pDataConnection);
    /** Defines the result of the execution of a Process, and provides the Input and Output channels of data. (Use Connection as the interface to the DataMap.) */
    void     onExecutionOf(final Process<U> pProcess, final List<V> pSourceInports, final List<Connection> pSourceConnections, final List<Connection> pSinkConnections, final Map<V, T> pPortDataMap, final Map<Connection, T> pDataMap);
    /** Defines the data value of an Inport. (This is w.r.t the diagram.) */
    T       getInportValue(final V pPort);
    /** Defines the response of an Outport. (This is w.r.t the diagram.) */
    void onOutportResponse(final V pPort, final T pData);
}
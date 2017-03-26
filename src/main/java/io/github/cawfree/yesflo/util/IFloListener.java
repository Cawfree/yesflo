package io.github.cawfree.yesflo.util;

/**
 * Created by cawfree on 26/03/17.
 */

import java.util.List;
import java.util.Map;

import io.github.cawfree.yesflo.components.Process;
import io.github.cawfree.yesflo.components.Connection;
import io.github.cawfree.yesflo.components.Parameter;

/** Defines a Listener for implementing execution states along a FlowHub graphs. */
public interface IFloListener<T> {
    /** Defines the data value of an Inport. */
    T getInportValue(final Parameter pParameter);
    /** Defines the data sourced by a DataConnection. */
    T valueOf(final Connection.Data pDataConnection);
    /** Defines the result of the execution of a Process, and provides the Input and Output channels of data. (Use Connection as the interface to the DataMap.) */
    void   onExecutionOf(final Process pProcess, final List<Connection> pSourceConnections, final List<Connection> pSinkConnections, final Map<Connection, T> pDataMap);
    /** Defines the response of an Output. */
    void   onOutportResponse(final Parameter pParameter, final T pData);
}
# yesFlo
A Java linear iterator for FlowHub's snazzy [noFlo diagrams](https://github.com/flowhub/the-graph).

This library presents tools for interpreting FlowHub's diagram JSON as equivalent Java Objects. It also provides a simple iterator that allows noFlo Processes to be iterated linearly, using conventional dataflow, contrary to the asynchronous "message-driven" style used in Flow-Based Programming, or FBP.

You can visit the existing examples using the URLs below:

* Simple demo. [code](./examples/demo-simple.html) |
[Run](https://flowhub.github.io/the-graph/examples/demo-simple.html)
* Stresstest. [code](./examples/demo-full.html) |
[Run](https://flowhub.github.io/the-graph/examples/demo-full.html)

And you can make a copy of the equivalent diagram JSON using the following script:
`javascript:_graph = window.open("data:text/json," + encodeURIComponent(JSON.stringify(document.getElementById('editor').fbpGraph)),"_blank"); _graph.focus();`

The returned String may then be procesed by the yesFlo utilities. A simple `iterate` method has been supplied, which will allow you to provide application-specific handling of a FlowHub diagram. Please note that this implementation is simplistic, and cyclic data dependencies will throw these utilities into an infinite loop!

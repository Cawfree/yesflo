package io.github.cawfree.yesflo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.cawfree.yesflo.data.Catalogue;
import io.github.cawfree.yesflo.elements.Component;
import io.github.cawfree.yesflo.elements.Connection;
import io.github.cawfree.yesflo.elements.Port;
import io.github.cawfree.yesflo.util.FlowHubGlobal;
import io.github.cawfree.yesflo.util.IFloListener;
import io.github.cawfree.yesflo.elements.Process;

public final class Main {

    /** Execute the command below in your browser's URL whilst viewing a FlowHub diagram to visualize the equivalent JSON String in a new tab. */
    // javascript:_graph = window.open("data:text/json," + encodeURIComponent(JSON.stringify(document.getElementById('editor').fbpGraph)),"_blank"); _graph.focus();
    /** Execute this command to fetch the Library's equivalent JSON. */
    // javascript:alert(JSON.stringify(document.getElementById('editor').$.graph.library));

    /* Static Declarations. */
    private static final String JSON_GRAPH_STRESS = "\n" +
            "\n" +
            "{\n" +
            "  \"caseSensitive\": false,\n" +
            "  \"properties\": {\n" +
            "    \"name\": \"photobooth\",\n" +
            "    \"environment\": {\n" +
            "      \"runtime\": \"html\",\n" +
            "      \"src\": \"preview\\/iframe.html\",\n" +
            "      \"width\": 300,\n" +
            "      \"height\": 300,\n" +
            "      \"content\": \"    <video id=\\\"vid\\\" autoplay loop width=\\\"640\\\" height=\\\"480\\\" style=\\\"display:none;\\\"><\\/video>\\n    <canvas id=\\\"out\\\" width=\\\"640\\\" height=\\\"480\\\" style=\\\"max-width:100%;\\\"><\\/canvas>\\n\\n<input id=\\\"slider\\\" type=\\\"range\\\" min=\\\"0\\\" max=\\\"1\\\" value=\\\"0.5\\\" step=\\\"0.01\\\"><\\/input>\\n    <button id=\\\"start\\\">start camera<\\/button>\\n    <button id=\\\"prev\\\">prev<\\/button>\\n    <button id=\\\"next\\\">next<\\/button>\\n    <button id=\\\"save\\\">save<\\/button>\\n\\n<style>\\n  #saved img { width: 160px; height: 120px;}\\n<\\/style>\\n<div id=\\\"saved\\\"><\\/div>\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"inports\": {\n" +
            "    \"prev\": {\n" +
            "      \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "      \"port\": \"prev\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 144,\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"next\": {\n" +
            "      \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "      \"port\": \"next\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 0,\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"outports\": {\n" +
            "    \"image\": {\n" +
            "      \"process\": \"core\\/Split_xyb8x\",\n" +
            "      \"port\": \"out\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 2000,\n" +
            "        \"y\": 1000,\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"groups\": [\n" +
            "    {\n" +
            "      \"name\": \"elements\",\n" +
            "      \"nodes\": [\n" +
            "        \"dom\\/GetElement_ah82a\",\n" +
            "        \"dom\\/GetElement_f4nkd\",\n" +
            "        \"dom\\/GetElement_z64xf\",\n" +
            "        \"dom\\/GetElement_ah36i\",\n" +
            "        \"core\\/Split_jzzu2\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"description\": \"get the elements from the dom\",\n" +
            "        \"color\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"setup\",\n" +
            "      \"nodes\": [\n" +
            "        \"interaction\\/ListenMouse_1w3vt\",\n" +
            "        \"core\\/Split_y0bla\",\n" +
            "        \"seriously\\/SetSource_szf33\",\n" +
            "        \"gum\\/GetUserMedia_9e9i4\",\n" +
            "        \"dom\\/SetAttribute_uto4k\",\n" +
            "        \"core\\/Split_occbw\",\n" +
            "        \"core\\/RepeatAsync_647ff\",\n" +
            "        \"core\\/Kick_4njgs\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"color\": 2\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"countdown\",\n" +
            "      \"nodes\": [\n" +
            "        \"core\\/Split_lbwyz\",\n" +
            "        \"interaction\\/ListenMouse_1u0rk\",\n" +
            "        \"strings\\/SendString_zry4n\",\n" +
            "        \"core\\/RunTimeout_3wulz\",\n" +
            "        \"dom\\/AddClass_9rihj\",\n" +
            "        \"core\\/Split_ho5ib\",\n" +
            "        \"strings\\/SendString_lnf0z\",\n" +
            "        \"core\\/Kick_7efi8\",\n" +
            "        \"dom\\/RemoveClass_ec7ug\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"description\": \"\",\n" +
            "        \"color\": 3\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"changefilter\",\n" +
            "      \"nodes\": [\n" +
            "        \"dom\\/GetElement_e16dy\",\n" +
            "        \"dom\\/GetElement_85so0\",\n" +
            "        \"interaction\\/ListenMouse_bil4d\",\n" +
            "        \"interaction\\/ListenMouse_aii7r\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"description\": \"\",\n" +
            "        \"color\": 5\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"save\",\n" +
            "      \"nodes\": [\n" +
            "        \"core\\/MakeFunction_t17n\",\n" +
            "        \"core\\/Split_xyb8x\",\n" +
            "        \"strings\\/SendString_7g9h8\",\n" +
            "        \"dom\\/GetElement_4houj\",\n" +
            "        \"core\\/RepeatAsync_grqs3\",\n" +
            "        \"dom\\/CreateElement_sf6ec\",\n" +
            "        \"dom\\/SetAttribute_3bmlw\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"description\": \"\",\n" +
            "        \"color\": 9\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"filter\",\n" +
            "      \"nodes\": [\n" +
            "        \"dom\\/GetElement_j674o\",\n" +
            "        \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"math\\/Multiply_rbxrn\",\n" +
            "        \"math\\/Multiply_3v13k\",\n" +
            "        \"seriously\\/HueSaturation_bzfvt\",\n" +
            "        \"core\\/Split_jx318\",\n" +
            "        \"math\\/Multiply_3eldx\",\n" +
            "        \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"seriously\\/Hex_lx162\",\n" +
            "        \"seriously\\/Ascii_17c9q\",\n" +
            "        \"seriously\\/Invert_7xnl3\",\n" +
            "        \"seriously\\/Edge_egmkb\",\n" +
            "        \"seriously\\/Split_7oj15\",\n" +
            "        \"seriously\\/SetTarget_kii7s\"\n" +
            "      ],\n" +
            "      \"metadata\": {\n" +
            "        \"description\": \"\",\n" +
            "        \"color\": 10\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"processes\": {\n" +
            "    \"dom\\/GetElement_f4nkd\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 324,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"startButton\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"interaction\\/ListenMouse_1w3vt\": {\n" +
            "      \"component\": \"interaction\\/ListenMouse\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 324,\n" +
            "        \"y\": 288,\n" +
            "        \"label\": \"clickStart\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"gum\\/GetUserMedia_9e9i4\": {\n" +
            "      \"component\": \"gum\\/GetUserMedia\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 324,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"webCam\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_z64xf\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 504,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"videoEl\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/SetAttribute_uto4k\": {\n" +
            "      \"component\": \"dom\\/SetAttribute\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 432,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"setVideoSrc\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/SetSource_szf33\": {\n" +
            "      \"component\": \"seriously\\/SetSource\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 576,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"setFilterSource\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/Ascii_17c9q\": {\n" +
            "      \"component\": \"seriously\\/Ascii\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1080,\n" +
            "        \"y\": 396,\n" +
            "        \"label\": \"seriously\\/Ascii\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_ah82a\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1404,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"canvasEl\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/SetTarget_kii7s\": {\n" +
            "      \"component\": \"seriously\\/SetTarget\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1404,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"filterTarget\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_85so0\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 720,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"prevButton\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"interaction\\/ListenMouse_aii7r\": {\n" +
            "      \"component\": \"interaction\\/ListenMouse\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 720,\n" +
            "        \"y\": 288,\n" +
            "        \"label\": \"clickPrev\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_e16dy\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 864,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"nextButton\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"interaction\\/ListenMouse_bil4d\": {\n" +
            "      \"component\": \"interaction\\/ListenMouse\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 864,\n" +
            "        \"y\": 288,\n" +
            "        \"label\": \"clickNext\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"routers\\/KickRouter_bzaiw\": {\n" +
            "      \"component\": \"routers\\/KickRouter\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 828,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"chooseFilter\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/TVGlitch_e1qre\": {\n" +
            "      \"component\": \"seriously\\/TVGlitch\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1080,\n" +
            "        \"y\": 504,\n" +
            "        \"label\": \"seriously\\/TVGlitch\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/Hex_lx162\": {\n" +
            "      \"component\": \"seriously\\/Hex\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1008,\n" +
            "        \"y\": 612,\n" +
            "        \"label\": \"seriously\\/Hex\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/Edge_egmkb\": {\n" +
            "      \"component\": \"seriously\\/Edge\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1152,\n" +
            "        \"y\": 612,\n" +
            "        \"label\": \"seriously\\/Edge\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/Invert_7xnl3\": {\n" +
            "      \"component\": \"seriously\\/Invert\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1008,\n" +
            "        \"y\": 828,\n" +
            "        \"label\": \"negative\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/Split_7oj15\": {\n" +
            "      \"component\": \"seriously\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1152,\n" +
            "        \"y\": 756,\n" +
            "        \"label\": \"halfScreen\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_jx318\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1008,\n" +
            "        \"y\": 720,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_occbw\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 684,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_y0bla\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 504,\n" +
            "        \"y\": 468,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"seriously\\/HueSaturation_bzfvt\": {\n" +
            "      \"component\": \"seriously\\/HueSaturation\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1080,\n" +
            "        \"y\": 972,\n" +
            "        \"label\": \"seriously\\/HueSaturation\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_jzzu2\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1404,\n" +
            "        \"y\": 288,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Kick_7efi8\": {\n" +
            "      \"component\": \"core\\/Kick\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1476,\n" +
            "        \"y\": 792,\n" +
            "        \"label\": \"sendCanvas\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/MakeFunction_t17n\": {\n" +
            "      \"component\": \"core\\/MakeFunction\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1296,\n" +
            "        \"y\": 936,\n" +
            "        \"label\": \"canvasToJPEG\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_ah36i\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"saveButton\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"interaction\\/ListenMouse_1u0rk\": {\n" +
            "      \"component\": \"interaction\\/ListenMouse\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 288,\n" +
            "        \"label\": \"clickSave\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/CreateElement_sf6ec\": {\n" +
            "      \"component\": \"dom\\/CreateElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 1008,\n" +
            "        \"label\": \"dom\\/CreateElement\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/SetAttribute_3bmlw\": {\n" +
            "      \"component\": \"dom\\/SetAttribute\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 1152,\n" +
            "        \"label\": \"dom\\/SetAttribute\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/RepeatAsync_grqs3\": {\n" +
            "      \"component\": \"core\\/RepeatAsync\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1440,\n" +
            "        \"y\": 1152,\n" +
            "        \"label\": \"core\\/RepeatAsync\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_4houj\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 792,\n" +
            "        \"label\": \"savedEl\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_xyb8x\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1296,\n" +
            "        \"y\": 1080,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"strings\\/SendString_7g9h8\": {\n" +
            "      \"component\": \"strings\\/SendString\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1440,\n" +
            "        \"y\": 1008,\n" +
            "        \"label\": \"strings\\/SendString\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/RepeatAsync_647ff\": {\n" +
            "      \"component\": \"core\\/RepeatAsync\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 612,\n" +
            "        \"y\": 792,\n" +
            "        \"label\": \"core\\/RepeatAsync\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Kick_4njgs\": {\n" +
            "      \"component\": \"core\\/Kick\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 756,\n" +
            "        \"y\": 792,\n" +
            "        \"label\": \"kickFirstFilter\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/GetElement_j674o\": {\n" +
            "      \"component\": \"dom\\/GetElement\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 432,\n" +
            "        \"y\": 1044,\n" +
            "        \"label\": \"sliderEl\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"interaction\\/ListenChange_z7m5u\": {\n" +
            "      \"component\": \"interaction\\/ListenChange\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 612,\n" +
            "        \"y\": 1044,\n" +
            "        \"label\": \"slid\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"math\\/Multiply_3eldx\": {\n" +
            "      \"component\": \"math\\/Multiply\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 828,\n" +
            "        \"y\": 1152,\n" +
            "        \"label\": \"x2xPI\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"math\\/Multiply_3v13k\": {\n" +
            "      \"component\": \"math\\/Multiply\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 828,\n" +
            "        \"y\": 1044,\n" +
            "        \"label\": \"tenth\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"math\\/Multiply_rbxrn\": {\n" +
            "      \"component\": \"math\\/Multiply\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 828,\n" +
            "        \"y\": 936,\n" +
            "        \"label\": \"tenth\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/RunTimeout_3wulz\": {\n" +
            "      \"component\": \"core\\/RunTimeout\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 432,\n" +
            "        \"label\": \"core\\/RunTimeout\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_ho5ib\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1584,\n" +
            "        \"y\": 576,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/AddClass_9rihj\": {\n" +
            "      \"component\": \"dom\\/AddClass\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1836,\n" +
            "        \"y\": 360,\n" +
            "        \"label\": \"dom\\/AddClass\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"core\\/Split_lbwyz\": {\n" +
            "      \"component\": \"core\\/Split\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1728,\n" +
            "        \"y\": 144,\n" +
            "        \"label\": \"core\\/Split\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"dom\\/RemoveClass_ec7ug\": {\n" +
            "      \"component\": \"dom\\/RemoveClass\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1836,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"dom\\/RemoveClass\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"strings\\/SendString_zry4n\": {\n" +
            "      \"component\": \"strings\\/SendString\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1728,\n" +
            "        \"y\": 360,\n" +
            "        \"label\": \"countdown\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    },\n" +
            "    \"strings\\/SendString_lnf0z\": {\n" +
            "      \"component\": \"strings\\/SendString\",\n" +
            "      \"metadata\": {\n" +
            "        \"x\": 1728,\n" +
            "        \"y\": 648,\n" +
            "        \"label\": \"countdown\",\n" +
            "        \"width\": 72,\n" +
            "        \"height\": 72\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"connections\": [\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_f4nkd\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_1w3vt\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_1w3vt\",\n" +
            "        \"port\": \"click\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"gum\\/GetUserMedia_9e9i4\",\n" +
            "        \"port\": \"start\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"9\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"gum\\/GetUserMedia_9e9i4\",\n" +
            "        \"port\": \"url\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_uto4k\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/Ascii_17c9q\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_85so0\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_aii7r\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_e16dy\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_bil4d\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_aii7r\",\n" +
            "        \"port\": \"click\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"prev\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"9\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_bil4d\",\n" +
            "        \"port\": \"click\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"next\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"9\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Hex_lx162\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/Edge_egmkb\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/Hex_lx162\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Edge_egmkb\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_jx318\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_jx318\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Invert_7xnl3\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/SetSource_szf33\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_occbw\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_occbw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_z64xf\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_y0bla\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_y0bla\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_uto4k\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_y0bla\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetSource_szf33\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/HueSaturation_bzfvt\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/HueSaturation_bzfvt\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_ah82a\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_jzzu2\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_jzzu2\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/SetTarget_kii7s\",\n" +
            "        \"port\": \"target\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_jzzu2\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Kick_7efi8\",\n" +
            "        \"port\": \"data\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Kick_7efi8\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/MakeFunction_t17n\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_jx318\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"sourcea\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"seriously\\/Invert_7xnl3\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"sourceb\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/CreateElement_sf6ec\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_3bmlw\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_4houj\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/CreateElement_sf6ec\",\n" +
            "        \"port\": \"container\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/RepeatAsync_grqs3\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_3bmlw\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_xyb8x\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/RepeatAsync_grqs3\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/MakeFunction_t17n\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_xyb8x\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_xyb8x\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_7g9h8\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"strings\\/SendString_7g9h8\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/CreateElement_sf6ec\",\n" +
            "        \"port\": \"tagname\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_occbw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/RepeatAsync_647ff\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/RepeatAsync_647ff\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Kick_4njgs\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Kick_4njgs\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"index\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Ascii_17c9q\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"routers\\/KickRouter_bzaiw\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"source\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"5\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_j674o\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_3eldx\",\n" +
            "        \"port\": \"multiplicand\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"math\\/Multiply_3eldx\",\n" +
            "        \"port\": \"product\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"angle\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_3v13k\",\n" +
            "        \"port\": \"multiplicand\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"math\\/Multiply_3v13k\",\n" +
            "        \"port\": \"product\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Hex_lx162\",\n" +
            "        \"port\": \"size\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_rbxrn\",\n" +
            "        \"port\": \"multiplicand\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"math\\/Multiply_rbxrn\",\n" +
            "        \"port\": \"product\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"distortion\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenChange_z7m5u\",\n" +
            "        \"port\": \"value\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/HueSaturation_bzfvt\",\n" +
            "        \"port\": \"hue\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"3\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_1u0rk\",\n" +
            "        \"port\": \"click\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/RunTimeout_3wulz\",\n" +
            "        \"port\": \"start\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"9\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/RunTimeout_3wulz\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_ho5ib\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_ho5ib\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Kick_7efi8\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"dom\\/GetElement_ah36i\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Split_lbwyz\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_lbwyz\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_1u0rk\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_lbwyz\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/AddClass_9rihj\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_lbwyz\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/RemoveClass_ec7ug\",\n" +
            "        \"port\": \"element\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"10\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"strings\\/SendString_zry4n\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/AddClass_9rihj\",\n" +
            "        \"port\": \"class\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"strings\\/SendString_lnf0z\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/RemoveClass_ec7ug\",\n" +
            "        \"port\": \"class\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"core\\/Split_ho5ib\",\n" +
            "        \"port\": \"out\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_lnf0z\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"src\": {\n" +
            "        \"process\": \"interaction\\/ListenMouse_1u0rk\",\n" +
            "        \"port\": \"click\"\n" +
            "      },\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_zry4n\",\n" +
            "        \"port\": \"in\"\n" +
            "      },\n" +
            "      \"metadata\": {\n" +
            "        \"route\": \"9\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#start\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_f4nkd\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#vid\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_z64xf\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"src\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_uto4k\",\n" +
            "        \"port\": \"attribute\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#out\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_ah82a\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#prev\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_85so0\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#next\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_e16dy\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": 0.01,\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"fuzzy\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.5\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/Split_7oj15\",\n" +
            "        \"port\": \"split\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.25\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/HueSaturation_bzfvt\",\n" +
            "        \"port\": \"saturation\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"return x.toDataURL(\\\"image\\/jpeg\\\", 0.85);\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/MakeFunction_t17n\",\n" +
            "        \"port\": \"function\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#save\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_ah36i\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#saved\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_4houj\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"src\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/SetAttribute_3bmlw\",\n" +
            "        \"port\": \"attribute\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"img\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_7g9h8\",\n" +
            "        \"port\": \"string\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/Kick_4njgs\",\n" +
            "        \"port\": \"data\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"#slider\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"dom\\/GetElement_j674o\",\n" +
            "        \"port\": \"selector\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"6.283185\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_3eldx\",\n" +
            "        \"port\": \"multiplier\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": 0.01,\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"verticalsync\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.04\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"linesync\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": 0.01,\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"time\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.01\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"bars\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": 0.15,\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"seriously\\/TVGlitch_e1qre\",\n" +
            "        \"port\": \"scanlines\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.1\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_3v13k\",\n" +
            "        \"port\": \"multiplier\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"0.1\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"math\\/Multiply_rbxrn\",\n" +
            "        \"port\": \"multiplier\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"3000\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"core\\/RunTimeout_3wulz\",\n" +
            "        \"port\": \"time\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"countdown\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_zry4n\",\n" +
            "        \"port\": \"string\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"data\": \"countdown\",\n" +
            "      \"tgt\": {\n" +
            "        \"process\": \"strings\\/SendString_lnf0z\",\n" +
            "        \"port\": \"string\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";

    private static final String JSON_GRAPH_HAS_UNWIRED = "{\"caseSensitive\":false,\"properties\":{},\"inports\":{},\"outports\":{},\"groups\":[],\"processes\":{\"1772\":{\"component\":\"basic\",\"metadata\":{\"label\":\"basic\",\"x\":338,\"y\":355,\"width\":72,\"height\":72}},\"crp\":{\"component\":\"tall\",\"metadata\":{\"label\":\"tall\",\"x\":540,\"y\":396,\"width\":72,\"height\":120}}},\"connections\":[{\"src\":{\"process\":\"1772\",\"port\":\"out\"},\"tgt\":{\"process\":\"crp\",\"port\":\"in0\"},\"metadata\":{}},{\"src\":{\"process\":\"1772\",\"port\":\"out\"},\"tgt\":{\"process\":\"crp\",\"port\":\"in4\"},\"metadata\":{\"route\":0}},{\"src\":{\"process\":\"crp\",\"port\":\"out0\"},\"tgt\":{\"process\":\"1772\",\"port\":\"in0\"},\"metadata\":{}},{\"src\":{\"process\":\"crp\",\"port\":\"out0\"},\"tgt\":{\"process\":\"1772\",\"port\":\"in1\"},\"metadata\":{\"route\":0}},{\"src\":{\"process\":\"crp\",\"port\":\"out0\"},\"tgt\":{\"process\":\"1772\",\"port\":\"in2\"},\"metadata\":{\"route\":0}}]}";

    private static final String JSON_LIBRARY = "{\"basic\":{\"name\":\"basic\",\"description\":\"basic demo component\",\"icon\":\"eye\",\"inports\":[{\"name\":\"in0\",\"type\":\"all\"},{\"name\":\"in1\",\"type\":\"all\"},{\"name\":\"in2\",\"type\":\"all\"}],\"outports\":[{\"name\":\"out\",\"type\":\"all\"}]},\"tall\":{\"name\":\"tall\",\"description\":\"tall demo component\",\"icon\":\"cog\",\"inports\":[{\"name\":\"in0\",\"type\":\"all\"},{\"name\":\"in1\",\"type\":\"all\"},{\"name\":\"in2\",\"type\":\"all\"},{\"name\":\"in3\",\"type\":\"all\"},{\"name\":\"in4\",\"type\":\"all\"},{\"name\":\"in5\",\"type\":\"all\"},{\"name\":\"in6\",\"type\":\"all\"},{\"name\":\"in7\",\"type\":\"all\"},{\"name\":\"in8\",\"type\":\"all\"},{\"name\":\"in9\",\"type\":\"all\"},{\"name\":\"in10\",\"type\":\"all\"},{\"name\":\"in11\",\"type\":\"all\"},{\"name\":\"in12\",\"type\":\"all\"}],\"outports\":[{\"name\":\"out0\",\"type\":\"all\"}]}}";

    /** Main Method Entry Point. */
    public static final void main(final String ... pArgs) throws Exception {
        // Allocate an ObjectMapper.
        final ObjectMapper         lObjectMapper    = new ObjectMapper();
        // Fetch the root-most JsonNode of the Library.
        final JsonNode             lLibraryJson     = lObjectMapper.readTree(Main.JSON_LIBRARY);
        // Fetch the root-most JsonNode of the Diagram.
        final JsonNode             lGraphJson       = lObjectMapper.readTree(Main.JSON_GRAPH_HAS_UNWIRED);
        // Fetch the Catalogue.
        final Catalogue            lCatalogue       = FlowHubGlobal.getCatalogue(lLibraryJson);
        // Fetch parameters of the Graph.
        final boolean              lIsCaseSensitive = lGraphJson.at("/caseSensitive").asBoolean();
        // Declare the ProcessMap. (A Map of all of the Diagram Processes and their corresponding lookup key.)
        final Map<String, Process> lProcessMap      = new HashMap<>();
        // Fetch the Processes.
        final List<Process>        lProcesses       = FlowHubGlobal.onFetchProcesses(lGraphJson.at("/processes"), lCatalogue, lProcessMap);
        // Fetch the Inports and Outports; we'll name these Parameters.
        final List<Port>           lInports         = FlowHubGlobal.onFetchPorts(lGraphJson.at("/inports"), lProcessMap);
        final List<Port>           lOutports        = FlowHubGlobal.onFetchPorts(lGraphJson.at("/outports"), lProcessMap);
        // Fetch the Connection.
        final List<Connection>     lConnections     = FlowHubGlobal.onFetchConnections(lGraphJson.at("/connections"), lProcessMap);
        // Iterate the Processes.
        for(final Process lProcess : lProcesses) {
            // Iterate the Process' ports.
            for(final Component.Port lInport : lProcess.getComponent().getInports()) {
                // Fetch the number of Drivers.
                final List<Connection> lDrivers = FlowHubGlobal.getDrivers(lProcess, lInport, lConnections);
                // Are there no drivers?
                if(lDrivers.isEmpty()) {
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
        // Iterate sequentially across the Diagram.
        FlowHubGlobal.iterate(lProcesses, lInports, lOutports, lConnections, new IFloListener<Component, Object>() {
            /** Define the data provided by an Inport Parameter. */
            @Override public final Object getInportValue(final Port pPort) { return null; }
            /** Define the data supplied by a Constant. */
            @Override public final Object valueOf(final Connection.Data pDataConnection) { return null; }
            /** Executes a Component. Use the DataMap to look-up input data related via the SourceConnections, and write responses from the Process using the SinkConnections. (SinkConnections *must* provide output data in order for execution to work successfully.) */
            @Override public final void onExecutionOf(final Component pComponent, List<Connection> pSourceConnections, final List<Connection> pSinkConnections, Map<Connection, Object> pDataMap) {
                // i.e.
                for(final Connection lOutputConnection : pSinkConnections) { pDataMap.put(lOutputConnection, "data at this output"); }
            }
            /** Handles the data written to an Outport Parameter. */
            @Override public final void onOutportResponse(final Port pPort, final Object pData) { }
        });
    }

}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagram</title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/gojs/1.8.22/go-debug.js"></script>
    <style>
        .loader {
            margin-top:200px;
            border: 10px solid #f3f3f3;
            border-radius: 120%;
            border-top: 10px solid brown;
            width: 60px;
            height: 60px;
            -webkit-animation: spin 2s linear infinite;
            animation: spin 1s linear infinite;
        }

        @-webkit-keyframes spin {
            0% { -webkit-transform: rotate(0deg); }
            100% { -webkit-transform: rotate(360deg); }
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body onload="init()">
<center>
    <div id="myDiagramDiv" style="border: solid 1px black; width:100%; height:600px">
        <div className="loader"></div>
    </div>
</center>

<script type="text/javascript">
    function init() {
        // if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
        var $ = go.GraphObject.make;

        myDiagram =
            $(go.Diagram, "myDiagramDiv",
                {
                    initialContentAlignment: go.Spot.Center,
                    "undoManager.isEnabled": true,
                    layout: $(go.TreeLayout,
                        { // this only lays out in trees nodes connected by "generalization" links
                            angle: 90,
                            path: go.TreeLayout.PathSource,  // links go from child to parent
                            setsPortSpot: false,  // keep Spot.AllSides for link connection spot
                            setsChildPortSpot: false,  // keep Spot.AllSides
                            // nodes not connected by "generalization" links are laid out horizontally
                            arrangement: go.TreeLayout.ArrangementHorizontal
                        })
                });

        // show visibility or access as a single character at the beginning of each property or method
        function convertVisibility(v) {
            switch (v) {
                case "public": return "+";
                case "private": return "-";
                case "protected": return "#";
                case "package": return "~";
                default: return v;
            }
        }
        function convertScope(s) {
            switch (s){
                case "interface": return "<<interface>>";
                default: return "";
            }
        }
        // the item template for properties
        var propertyTemplate =
            $(go.Panel, "Horizontal",
                // property visibility/access
                $(go.TextBlock,
                    { isMultiline: false, editable: false, width: 12 },
                    new go.Binding("text", "visibility", convertVisibility)),
                // property name, underlined if scope=="class" to indicate static property
                $(go.TextBlock,
                    { isMultiline: false, editable: true },
                    new go.Binding("text", "name").makeTwoWay(),
                    new go.Binding("isUnderline", "scope", function(s) { return s[0] === 'c' })),

                // property type, if known
                $(go.TextBlock, "",
                    new go.Binding("text", "type", function(t) { return (t ? ": " : ""); })),
                $(go.TextBlock,
                    { isMultiline: false, editable: true },
                    new go.Binding("text", "type").makeTwoWay()),
                // property default value, if any
                $(go.TextBlock,
                    { isMultiline: false, editable: false },
                    new go.Binding("text", "default", function(s) { return s ? " = " + s : ""; }))
            );

        // the item template for methods
        var methodTemplate =
            $(go.Panel, "Horizontal",
                // method visibility/access
                $(go.TextBlock,
                    { isMultiline: false, editable: false, width: 12 },
                    new go.Binding("text", "visibility", convertVisibility)),
                // method name, underlined if scope=="class" to indicate static method
                $(go.TextBlock,
                    { isMultiline: false, editable: true },
                    new go.Binding("text", "name").makeTwoWay(),
                    new go.Binding("isUnderline", "scope", function(s) { return s[0] === 'c' })),
                // method parameters
                $(go.TextBlock, "()",
                    // this does not permit adding/editing/removing of parameters via inplace edits
                    new go.Binding("text", "parameters", function(parr) {
                        var s = "(";
                        for (var i = 0; i < parr.length; i++) {
                            var param = parr[i];
                            if (i > 0) s += ", ";
                            s += param.name + ": " + param.type;
                        }
                        return s + ")";
                    })),
                // method return type, if any
                $(go.TextBlock, "",
                    new go.Binding("text", "type", function(t) { return (t ? ": " : ""); })),
                $(go.TextBlock,
                    { isMultiline: false, editable: true },
                    new go.Binding("text", "type").makeTwoWay())
            );

        // this simple template does not have any buttons to permit adding or
        // removing properties or methods, but it could!
        myDiagram.nodeTemplate =
            $(go.Node, "Auto",
                {
                    locationSpot: go.Spot.Center,
                    fromSpot: go.Spot.AllSides,
                    toSpot: go.Spot.AllSides
                },
                $(go.Shape, { fill: "lightyellow" }),
                $(go.Panel, "Table",
                    { defaultRowSeparatorStroke: "black" },
                    // header
                    $(go.TextBlock,
                        {
                            row: 1, columnSpan: 2, margin: 3, alignment: go.Spot.Center,
                            font: "bold 12pt sans-serif",
                            isMultiline: false, editable: true
                        },
                        new go.Binding("text", "name").makeTwoWay()),

                    $(go.TextBlock,
                        {
                            row: 0, columnSpan: 2, margin: 3, alignment: go.Spot.Center,
                            font: "bold 10pt serif",
                            isMultiline: false, editable: true
                        },
                        new go.Binding("text", "scope", convertScope)),
                    // properties
                    $(go.TextBlock, "Properties",
                        { row: 2, font: "italic 10pt sans-serif" },
                        new go.Binding("visible", "visible", function(v) { return !v; }).ofObject("PROPERTIES")),
                    $(go.Panel, "Vertical", { name: "PROPERTIES" },
                        new go.Binding("itemArray", "properties"),
                        {
                            row: 2, margin: 3, stretch: go.GraphObject.Fill,
                            defaultAlignment: go.Spot.Left, background: "lightyellow",
                            itemTemplate: propertyTemplate
                        }
                    ),
                    $("PanelExpanderButton", "PROPERTIES",
                        { row: 2, column: 1, alignment: go.Spot.TopRight, visible: false },
                        new go.Binding("visible", "properties", function(arr) { return arr.length > 0; })),
                    // methods
                    $(go.TextBlock, "Methods",
                        { row: 3, font: "italic 10pt sans-serif" },
                        new go.Binding("visible", "visible", function(v) { return !v; }).ofObject("METHODS")),
                    $(go.Panel, "Vertical", { name: "METHODS" },
                        new go.Binding("itemArray", "methods"),
                        {
                            row: 3, margin: 3, stretch: go.GraphObject.Fill,
                            defaultAlignment: go.Spot.Left, background: "lightyellow",
                            itemTemplate: methodTemplate
                        }
                    ),
                    $("PanelExpanderButton", "METHODS",
                        { row: 3, column: 1, alignment: go.Spot.TopRight, visible: false },
                        new go.Binding("visible", "methods", function(arr) { return arr.length > 0; }))
                )
            );

        function convertIsTreeLink(r) {
            return r === 'extends';
        }

        function convertFromArrow(r) {
            switch (r) {
                case "extends": return "";
                default: return "";
            }
        }
        function convertRelationship(r) {
            switch (r){
                case "implements": return [4,2];
                default: return [0,0];
            }
            // return r === 'implements';
        }

        function convertToArrow(r) {
            switch (r) {
                case "extends": return "Triangle";
                case "implements": return "Triangle";
                default: return "";
            }
        }

        myDiagram.linkTemplate =
            $(go.Link,
                { routing: go.Link.Orthogonal },
                new go.Binding("isLayoutPositioned", "relationship", convertIsTreeLink),
                /*$(go.Shape,{stroke: "purple"},
                    new go.Binding("strokeDashArray", "relationship").makeTwoWay()),*/

                $(go.Shape, {strokeWidth:1},
                    new go.Binding("strokeDashArray", "relationship",convertRelationship)),
                /*$(go.Shape, {strokeWidth:1},
                    new go.Binding("strokeDashArray", "link")),*/
                $(go.Shape, { scale: 1.3, fill: "white"  },
                    new go.Binding("fromArrow", "relationship", convertFromArrow)),
                $(go.Shape, { scale: 1.3, fill: "white"  },
                    new go.Binding("toArrow", "relationship", convertToArrow))
            );


        var xmlhttp1 = new XMLHttpRequest();
        var nodedata =[];
        xmlhttp1.open("GET", "http://localhost:3000/classes", false);
        xmlhttp1.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                nodedata = JSON.parse(this.responseText).classes;


            }
        };
        xmlhttp1.send();
        var linkdata = [];
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.open("GET", "http://localhost:3000/relationships", false);
        xmlhttp2.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                linkdata = JSON.parse(this.responseText).relationships;


            }
        };
        xmlhttp2.send();
        myDiagram.model = $(go.GraphLinksModel,
            {
                copiesArrays: true,
                copiesArrayObjects: true,
                nodeDataArray: nodedata,
                linkDataArray: linkdata
            });
    }

</script>


</body>
</html>

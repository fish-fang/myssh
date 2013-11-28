<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!--
  $Id: monitor.html,v 1.16 2011-04-28 15:31:10 gaudenz Exp $
  Copyright (c) 2006-2010, JGraph Ltd
  
  Monitor example for mxGraph. This eample demonstrates using a
  graph to display the current state of a workflow.
-->
<html>
<head>
	<title>mxGraph Workflow Monitor</title>

	<!-- Sets the basepath for the library if not in same directory -->
	<script type="text/javascript">
		mxBasePath = 'src';
	</script>

	<!-- Loads and initializes the library -->
	<script type="text/javascript" src="src/js/mxClient.js"></script>

	<!-- Example code -->
	<script type="text/javascript">
		// Program starts here. Creates a sample graph in the
		// DOM node with the specified ID. This function is invoked
		// from the onLoad event handler of the document (see below).
		function main(container)
		{
		      var test = new Object();
			test["a"] = document.getElementById("hid1").value;
			test["satus"] = document.getElementById("hid2").value;
			// Checks if the browser is supported
			if (!mxClient.isBrowserSupported())
			{
				// Displays an error message if the browser is not supported.
				mxUtils.error('Browser is not supported!', 200, false);
			}
			else
			{
				// Creates the graph inside the given container
				var graph = createGraph(container);
				graph.setHtmlLabels(true);


				graph.addListener(mxEvent.DOUBLE_CLICK, function(sender, evt)
				{
					var cell = evt.getProperty('cell');	
                    if(cell.id == 3){
                    	if(test.satus != 2){
                    		if(mxUtils.confirm("create a process")){
                            	window.location.href="swimlanes.html";
                        	}                        
                        	evt.consume();
                    	}
                        
                    }	
					
					
				});
				var parent = graph.getDefaultParent();
				try{
					// Creates a process display using the activity names as IDs to refer to the elements
					var xml = '<mxGraphModel grid="1" guides="1" tooltips="1" connect="1" fold="1" page="0" pageScale="1" pageWidth="826" pageHeight="1169"><root><mxCell id="0"/><mxCell id="1" parent="0"/><mxCell id="5" name="teacher" value="member" style="member" vertex="1" parent="1"><mxGeometry x="160" y="170" width="80" height="80" as="geometry"/></mxCell><mxCell id="7" value="Proces&#xa;sA" style="swimlane" vertex="1" parent="1"><mxGeometry x="310" y="180" width="140" height="60" as="geometry"/></mxCell><mxCell id="8" value="" style="shape=plus" vertex="1" connectable="0" parent="7"><mxGeometry x="0.5" y="1" width="12" height="12" relative="1" as="geometry"><mxPoint x="-6" y="-12" as="offset"/></mxGeometry></mxCell><mxCell id="9" style="entryX=0;entryY=0.5" edge="1" parent="1" source="5" target="7"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="10" value="ProcessB" style="swimlane" vertex="1" parent="1"><mxGeometry x="520" y="180" width="140" height="60" as="geometry"/></mxCell><mxCell id="11" value="" style="shape=plus" vertex="1" connectable="0" parent="10"><mxGeometry x="0.5" y="1" width="12" height="12" relative="1" as="geometry"><mxPoint x="-6" y="-12" as="offset"/></mxGeometry></mxCell><mxCell id="12" edge="1" parent="1" source="7" target="10"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="13" value="sdfs" style="end" vertex="1" parent="1"><mxGeometry x="755.5" y="185.5" width="49" height="49" as="geometry"/></mxCell><mxCell id="14" style="entryX=0;entryY=0.5;entryPerimeter=0" edge="1" parent="1" source="10" target="13"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="15" value="manager" style="manager" vertex="1" parent="1"><mxGeometry x="550" y="250" width="80" height="80" as="geometry"/></mxCell></root></mxGraphModel>';
					xml = xml.replace(/member/,test.a);
					var doc = mxUtils.parseXml(xml);
					var codec = new mxCodec(doc);
					codec.decode(doc.documentElement, graph.getModel());
				} finally{
					graph.getModel().endUpdate();
				}

				
			}
		};

		/**
		 * Creates and returns an empty graph inside the given container.
		 */
		function createGraph(container)
		{
			var graph = new mxGraph(container);
			graph.setTooltips(true);
			graph.setEnabled(false);
			var style = new Object();
			style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
			style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
			style[mxConstants.STYLE_IMAGE] = 'Worker1_128x128.png';
			style[mxConstants.STYLE_IMAGE_WIDTH] = '180';
			style[mxConstants.STYLE_IMAGE_HEIGHT] = '180';
			style[mxConstants.STYLE_FONTCOLOR] = '#000000';
			style[mxConstants.STYLE_VERTICAL_LABEL_POSITION] = mxConstants.ALIGN_CENTER;
			graph.getStylesheet().putCellStyle('member', style);
			var style = new Object();
			style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
			style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
			style[mxConstants.STYLE_IMAGE] = 'Suit1_128x128.png';
			style[mxConstants.STYLE_IMAGE_WIDTH] = '180';
			style[mxConstants.STYLE_IMAGE_HEIGHT] = '180';
			style[mxConstants.STYLE_FONTCOLOR] = '#000000';
			style[mxConstants.STYLE_VERTICAL_LABEL_POSITION] = mxConstants.ALIGN_CENTER;
			graph.getStylesheet().putCellStyle('manager', style);		// Disables folding
			var style = new Object();
			style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RHOMBUS;
			style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RhombusPerimeter;
			style[mxConstants.STYLE_STROKECOLOR] = 'gray';
			style[mxConstants.STYLE_FONTCOLOR] = 'gray';
			style[mxConstants.STYLE_FILLCOLOR] = '#91BCC0';
			style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
			style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
			style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE;
			style[mxConstants.STYLE_FONTSIZE] = '14';
			graph.getStylesheet().putCellStyle('swimlane', style);
			graph.isCellFoldable = function(cell, collapse)
			{
				return false;
			};
			return graph;
		};
	</script>
</head>
<s:hidden id="hid1" name="hid1" value="%{xmlstr}"/>
<s:hidden id="hid2" name="hid2" value="%{satus}"/>
<!-- Page passes the container and control to the main function -->
<body onload="main(document.getElementById('graphContainer'));">

	<!-- Acts as a container for the graph -->
	<div id="graphContainer" style="overflow:hidden;width:861px;height:401px;">
	</div>
	<br>
</body>
</html>
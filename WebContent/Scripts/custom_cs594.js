$("#save").on(
    "click",
    function() {

        var projectName = $("#projectName").val();
        var encodedProjectName = encodeURIComponent(projectName);

        var url = $("#url").val();
        var encodedUrl = encodeURIComponent(url);

        var requestCount = $("#requests").val();
        var encodedRequests = encodeURIComponent(requestCount);

        $("#status-msg").text("Saving project '" + projectName + "'...");

        $.ajax({
            type: "GET",
            url: "/client/v1/project/create?uri=" + encodedUrl + "&count=" + encodedRequests + "&projectname=" + encodedProjectName,
            success: function(data) {
                $("#status-msg").text("Project saved.");

                setTimeout(function() {
                    $("#status-msg").empty();
                }, 3000);
            },
            error: function(jqXHR, status) {
                $("#status-msg").text("Error: unable to save project.");
                setTimeout(function() {
                    $("#status-msg").empty();
                }, 3000);
            }
        });
    });

function updateChart(data) {

    var url = $("#url").val();
    var encodedUrl = encodeURIComponent(url);
    
    var projectName = $("#projectName").val();
    var encodedProjectName = encodeURIComponent(projectName);
    
    $.ajax({
        type: "GET",
        url: "/client/v1/loadtest/graphdata?uri=" + encodedUrl + "&projectname=" + encodedProjectName,
        success: function(data) {
            $("#status-msg").text("Received graph data.");

            setTimeout(function() {
                $("#status-msg").empty();
            }, 3000);
        },
        error: function(jqXHR, status) {
            $("#status-msg").text("Error: unable to retrieve graph data");
            setTimeout(function() {
                $("#status-msg").empty();
            }, 3000);
        }
    });

}

function startProgress() {

    /*
     * max: tells the max value of the progress bar
     */
    var progressbar = $("#progressbar-2");

    // progressbar() is a jQuery function built into the jquery progressbar
    // widget (part of jQuery UI library).
    $("#progressbar-2").progressbar({
        max: 100
            // max is a option that indicates the max value before the progress bar is
            // filled is "100"
    });

    function progress() {

        // set the "value" of the progress bar to whatever the "val" variable
        // is.
        // "value" indicates the progress on the progress bar        

        var projectName = $("#projectName").val();
        var encodedProjectName = encodeURIComponent(projectName);

        var url = $("#url").val();
        var encodedUrl = encodeURIComponent(url);

        var requestCount = $("#requests").val();
        var encodedRequests = encodeURIComponent(requestCount);

        $("#status-msg").text("Running '" + projectName + "'...");

        // http://localhost:8088/client/v1/loadtest/start?uri=http%3a%2f%2flocalhost%3a8080%2fwebservice%2fv1%2fhello&count=10000
        $.ajax({
            type: "GET",
            url: "/client/v1/loadtest/start?uri=" + encodedUrl + "&count=" + encodedRequests + "&projectname=" + encodedProjectName,
            success: function(data) {
                var percent = data.percentDone;
                progressbar.progressbar("value", percent);
                $("#new-project-conn-count").text(data.maxConcurrentUsers);
                $("#new-project-response-time").text(data.avgResponseTime);
            },
            error: function(jqXHR, status) {
                $("#status-msg").text("Error: unable to start load test");
            }
        });

        var val = progressbar.progressbar("value") || 0;

        // call progress(), as long as val < 99 (not 100%)
        if (val < 99) {
            setTimeout(progress, 100); // progress is called every 100
            // milliseconds, or .1 seconds
        } else {
            $("#start").attr("value", "Start");
            // change the "Stop" button to a "Start" button since the
            // operation is finished
            $("#status-msg").text("Done.");

            setTimeout(function() {
                $("#status-msg").empty();
                getChartData(url, "newProjectChart", projectName);
            }, 3000);

            $("#start").trigger("click");
        }
    }

    // setTimeOut automatically starts filling the progress bar, after 1000
    // milliseconds, or 1 second.
    setTimeout(progress, 1000);
}

// resets the progress bar
function stopProgress() {
    $("#progressbar-2").progressbar("destroy");
}

function handler1() {
    $(this).attr("value", "Stop");
    startProgress(); // start filling up the progress bar
    $(this).one("click", handler2);
}

function handler2() {
    $(this).attr("value", "Start");
    stopProgress();
    $(this).one("click", handler1);
}

$("#start").one("click", handler1);

function getChartData(url, chartType, projectname) {

    if (url === null) {
        url = "http://localhost:8080/webservice/v1/hello";
    }

    var encodedUrl = encodeURIComponent(url);
    var encodedProjectName = encodeURIComponent(projectname);
    var seriesObject = {};
    var categoryObject = {};

    $.ajax({
        type: "GET",
        url: "/client/v1/loadtest/graphdata?uri=" + encodedUrl + "&projectname=" + encodedProjectName,        
        success: function(data) {
            updateFusionChart(data, chartType);
            updateFusionChart(data.averages, "avg" + chartType);
        },
        error: function(jqXHR, status) {
            $("#status-msg").text("Error: unable to retrieve graph data");
            setTimeout(function() {
                $("#status-msg").empty();
                $("#viz-status-msg").empty();
            }, 3000);

        }
    });
    
    if ($("#newProjectChartDiv").css("display") === "none") {
    	showHiddenChart(chartType);
    }    
}

function showHiddenChart(chartType) {
	var chart = chartType;
	
    $("#" + chart + "Div").show()
    
}

function updateFusionChart(data, chartType) {

    // var seriesObject = data.series;
    var seriesObject;
    if (typeof data.series === 'undefined' || data.series === null) {
        seriesObject = data.dataset;
    } else {
        seriesObject = data.series;
        seriesObject.seriesname = data.uri;
    }
    
    var categoryObject = data.category;
       
    var seriesArray;
    if (seriesObject.constructor === Array) {
        seriesArray = seriesObject;
    } else {
        seriesArray = [ seriesObject ];
    }

    $("#" + chartType).insertFusionCharts({
        id: 'perfchart',
        type: 'msarea',
        renderAt: 'chart-container',
        width: '100%',
        height: '550',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "Load Test",
                "subCaption": "Response Time VS Test Duration",
                "xAxisName": "Total Test Duration (ms)",
                "yAxisName": "Average Response Time (ms)",
                "numberPrefix": "",
                "paletteColors": "#0075c2,#1aaf5d",
                "bgColor": "#ffffff",
                "showBorder": "0",
                "showCanvasBorder": "0",
                "plotBorderAlpha": "10",
                "usePlotGradientColor": "0",
                "legendBorderAlpha": "0",
                "legendShadow": "0",
                "plotFillAlpha": "60",
                "showXAxisLine": "1",
                "axisLineAlpha": "25",
                "showValues": "0",
                "captionFontSize": "16",
                "subcaptionFontSize": "16",
                "subcaptionFontBold": "0",
                "divlineColor": "#999999",
                "divLineIsDashed": "1",
                "divLineDashLen": "1",
                "divLineGapLen": "1",
                "showAlternateHGridColor": "0",
                "toolTipColor": "#ffffff",
                "toolTipBorderThickness": "0",
                "toolTipBgColor": "#000000",
                "toolTipBgAlpha": "80",
                "toolTipBorderRadius": "2",
                "toolTipPadding": "5",
            },
            "categories": [categoryObject],
            "dataset": seriesArray
        }
    });
    
}

function foofoo() {
    var moo =
            {
                "chart": {
                    "caption": "Response Times",
                    "subCaption": "Response Time VS Test Duration",
                    "xAxisName": "Total Test Duration (s)",
                    "yAxisName": "Average Response Time (ms)",
                    "numberPrefix": "",
                    "paletteColors": "#0075c2,#1aaf5d",
                    "bgColor": "#ffffff",
                    "showBorder": "0",
                    "showCanvasBorder": "0",
                    "plotBorderAlpha": "10",
                    "usePlotGradientColor": "0",
                    "legendBorderAlpha": "0",
                    "legendShadow": "0",
                    "plotFillAlpha": "60",
                    "showXAxisLine": "1",
                    "axisLineAlpha": "25",
                    "showValues": "0",
                    "captionFontSize": "16",
                    "subcaptionFontSize": "16",
                    "subcaptionFontBold": "0",
                    "divlineColor": "#999999",
                    "divLineIsDashed": "1",
                    "divLineDashLen": "1",
                    "divLineGapLen": "1",
                    "showAlternateHGridColor": "0",
                    "toolTipColor": "#ffffff",
                    "toolTipBorderThickness": "0",
                    "toolTipBgColor": "#000000",
                    "toolTipBgAlpha": "80",
                    "toolTipBorderRadius": "2",
                    "toolTipPadding": "5",
                },
                "categories": [{
                        "category": [{
                                "label": "0"
                            }, {
                                "label": "25"
                            }, {
                                "label": "50"
                            }, {
                                "label": "75"
                            }, {
                                "label": "100"
                            }, {
                                "label": "125"
                            }, {
                                "label": "150"
                            }]
                    }],
                "dataset": [
                    {
                        "seriesname": "Amazon",
                        "data": [{
                                "label": "1",
                                "value": "10000"
                            }, {
                                "label": "2",
                                "value": "14500"
                            }, {
                                "label": "5",
                                "value": "13500"
                            }, {
                                "label": "5",
                                "value": "15000"
                            }, {
                                "value": "1500"
                            }, {
                                "label": "10",
                                "value": "17650"
                            }, {
                                "label": "100",
                                "value": "19500"
                            }]
                    },
                    {
                        "seriesname": "Google",
                        "data": [{
                                "value": "8400"
                            }, {
                                "value": "9800"
                            }, {
                                "value": "11800"
                            }, {
                                "value": "14400"
                            }, {
                                "value": "18800"
                            }, {
                                "value": "24800"
                            }, {
                                "value": "30800"
                            }]
                    },
                    {
                        "seriesname": "Netflix",
                        "data": [{
                                "value": "1000"
                            }, {
                                "value": "2000"
                            }, {
                                "value": "10000"
                            }, {
                                "value": "14400"
                            }, {
                                "value": "18800"
                            }, {
                                "value": "24800"
                            }, {
                                "value": "30800"
                            }]
                    }
                ]
            };
}

$(document).ready(function() {
	$("#newProjectChartDiv").hide();
	$("#savedProjectsChartDiv").hide();
	$("#methodsDivLabel").hide();
	$("#paramsDivLabel").hide();
	
});
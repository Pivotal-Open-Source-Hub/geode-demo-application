var chart; // global
    /**
     * Request data from the server, add it to the graph and set a timeout 
     * to request again
     */
    function requestData() {
        $.ajax({
            url: '/transactionData',
            success: function(point) {
                var series = chart.series[0],
                    shift = series.data.length > 20; // shift if the series is
                    // longer than 20

                // add the point
                chart.series[0].addPoint(point, true, shift);
                
                // call it again after one second
                setTimeout(requestData, 3000);    
            },
            cache: false
        });
    }
    requestData();

    
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart',
                defaultSeriesType: 'spline',
                backgroundColor:'rgba(157, 82, 163, 0.04)',
                events: {
                    load: requestData
                }
        		
            },
            title: {
                text: 'Order Count'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150,
                maxZoom: 20 * 1000
            },
            yAxis: {
                minPadding: 0.0,
                maxPadding: 0.0
            },
            series: [{
                name: 'Orders',
                data: []
            }]
        });        
    });

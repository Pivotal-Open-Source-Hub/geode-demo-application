//basic end point to listen to notifications
        $(document).ready(
                function () {
                    var notifications = '/notifications';
                    var socket = new SockJS(notifications);
                    var client = Stomp.over(socket);
                    var events = [];
                    var source   = $("#entry-template").html();
                    var template = Handlebars.compile(source);
                    client.connect(
                            {},
                            function (frame) {
                                var username = frame.headers['user-name'];
                                client.subscribe("/topic/alarms", function (message) {
                                    var event = JSON.parse(message.body);
                                    events.push(event);
                                    //only show the top 10
                                    if (events.length == 20) {
                                    	events.splice(10,20).reverse();
                                    }
                                    var content = template({eventList : events});
                                    document.getElementById("eventList").innerHTML=content;
                                });
                            },
                            function (error) {
                                console.log("STOMP protocol error " + error);
                            }
                    );
                });
var simulationClient = null;

function connectToAmazonSimulation() {
	var socket = new SockJS("/simulation-socket");
    
	simulationClient = Stomp.over(socket);
    simulationClient.connect({}, function (frame) {
		simulationClient.subscribe("/topic/storage-rack", function(message) {
			updateStorageRacks(JSON.parse(message.body));
		});
	
		simulationClient.subscribe("/topic/truck", function(message) {
			updateTruck(JSON.parse(message.body));
		});
	
		simulationClient.subscribe("/topic/robot", function(message) {
			updateRobot(JSON.parse(message.body));
		});
	});
};

function updateStorageRacks(storageRack) {
	console.log(storageRack); //Example Code.
}

function updateTruck(truck) {
	console.log(truck); //Example Code.
}

function updateRobot(robot) {
	console.log(robot); //Example Code.
}

connectToAmazonSimulation();

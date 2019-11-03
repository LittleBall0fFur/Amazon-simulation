var simulationClient = null;
var warehouse;

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

function updateStorageRacks(s) {
	if(warehouse != null){
		warehouse.updateStorageRack(s);
	}
}

function updateTruck(truck) {
	console.log(truck); //Example Code.
}

function updateRobot(r) {
	//console.log(robot); //Example Code.
	//console.log(robot.transform);
	if(warehouse != null){
		warehouse.updateRobot(r);
	}
}

connectToAmazonSimulation();

var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth/window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

camera.position.z = 5;

var controls = new THREE.OrbitControls(camera, renderer.domElement);
controls.enableDamping = true;
controls.campingFactor = 0.25;
controls.enableZoom = true;

var keyLight = new THREE.DirectionalLight(new THREE.Color('hsl(30, 100%, 75%)'), 1.0);
keyLight.position.set(-100, 0 , 100);

var fillLight = new THREE.DirectionalLight(new THREE.Color('hsl(240, 100%, 75%)'), 0.75);
fillLight.position.set(100, 0, 100);

var backLight= new THREE.DirectionalLight(0xffffff, 1.0);
backLight.position.set(100, 0, -100).normalize();

scene.add(keyLight);
scene.add(fillLight);
scene.add(backLight);

/*var mtlLoader = new THREE.MTLLoader();
mtlLoader.setTexturePath('/./js/3d-obj-loader/assets/');
mtlLoader.setPath('/./js/3d-obj-loader/assets/');
mtlLoader.load('r2-d2.mtl', function(materials){
	materials.preload();
	
	var objLoader = new THREE.OBJLoader();
	objLoader.setMaterials(materials);
	objLoader.setPath('/./js/3d-obj-loader/assets/');
	objLoader.load('r2-d2.obj', function(object){
		object.position.y -= 60;
		scene.add(object);
		moveObject = object;
		console.log("Added object");
	});
});*/

warehouse = new Warehouse(scene);

for(var i = 0; i < 8; i++){
	for(var j = 0; j < 5; j++){
		var geometry = new THREE.BoxGeometry( 0.2, 0.2, 0.2 );
		var material = new THREE.MeshBasicMaterial( {color: 0xffffff} );
		var node = new THREE.Mesh( geometry, material );
		node.position.x = i;
		node.position.z = j;
		scene.add(node);
	}
}

var animate = function () {
	requestAnimationFrame( animate );
	
	controls.update();
	
	renderer.render( scene, camera );
};

animate();
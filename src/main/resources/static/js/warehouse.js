function Warehouse(_scene){
	
	this.scene = _scene;
	this.robots = [];
	this.storageRacks = [];
	
	this.updateRobot = function(r){
		var robot = this.robots.find(function(element) { 
		  if(r.identifier == element.identifier)
			return element;
		});
		if(robot != null){
			robot.transform = r.transform;
		}else{
			robot = this.addRobot(r);
		}
		
		robot.mesh.position.x = robot.transform.position.x;
		robot.mesh.position.z = robot.transform.position.y;
	}
	
	this.addRobot = function(r){
		var robot = new Robot();
		robot.identifier = r.identifier;
		robot.transform = r.transform;
		this.robots.push(robot);
		this.scene.add(robot.mesh);
		return robot;
	}
	
	this.updateStorageRack = function(s){
		var storageRack = this.storageRacks.find(function(element) { 
		  if(s.identifier == element.identifier)
			return element;
		});
		if(storageRack != null){
			storageRack.transform = s.transform;
		}else{
			storageRack = this.addStorageRack(s);
		}
		
		storageRack.mesh.position.x = storageRack.transform.position.x;
		storageRack.mesh.position.y = 1;
		storageRack.mesh.position.z = storageRack.transform.position.y;
	}
	
	this.addStorageRack = function(s){
		var storageRack = new StorageRack();
		storageRack.identifier = s.identifier;
		storageRack.transform = s.transform;
		this.storageRacks.push(storageRack);
		this.scene.add(storageRack.mesh);
		return storageRack;
	}
	
}
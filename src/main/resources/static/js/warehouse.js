function Warehouse(_scene){
	
	this.scene = _scene;
	this.robots = [];
	
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
	
}
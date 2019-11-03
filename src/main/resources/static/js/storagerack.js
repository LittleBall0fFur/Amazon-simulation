function StorageRack(){
   	this.identifier;

	this.geometry = new THREE.BoxGeometry(0.7, 3, 0.7);
	this.material = new THREE.MeshBasicMaterial( {color: 0x00ff00} );
	this.mesh = new THREE.Mesh(this.geometry, this.material);

   	this.transform = {
		localPosition : {x : 0.0,y : 0.0,z : 0.0},
		localRotation : {x : 0.0,y : 0.0,z : 0.0},
		position : {x : 0.0,y : 0.0,z : 0.0},
		rotation : {x : 0.0,y : 0.0,z : 0.0}
	};
	
}
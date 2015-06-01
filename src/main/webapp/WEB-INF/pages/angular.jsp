<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">li { cursor: pointer; cursor: hand; }</style>
<title>Spring MVC AngularJS demo</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.4/angular.min.js"></script>
<script>
	angular.module('app', []);
	angular.module('app').controller("MainController",
		function ($scope, $http){
		$scope.title = 'Todos list';
		$scope.taskToAdd = '';

		$http.defaults.headers.post["Content-Type"] = "text/plain; charset=utf-8";
		
		$scope.getTasksDataFromServer = function() {	    	
	    	$http({method: 'GET', url: 'json'}).
	        success(function(data, status, headers, config) {
	        	$scope.todoList = data;
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        })
	    
	    };
		$scope.removeTask = function(task) {	    	
	    	$http({method: 'POST', url: 'json/remove?task=' + task}).
	        success(function(data, status, headers, config) {
	        	$scope.todoList = data;
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        })
	    
	    };
	    $scope.addTask = function() {	    	
	    	$http({
	    		method: 'POST',
	    		url: 'json/add?task=' + $scope.taskToAdd, 
	    		headers: {
	    			"Accept": "application/json;charset=utf-8",
	    		       "Accept-Charset":"charset=utf-8",
                        'Content-Type': 'text/plain; charset=utf-8'
                }
	    	}).
	        success(function(data, status, headers, config) {
	        	$scope.todoList = data;
	        	document.getElementById('input').value = '';
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        })
	       
	    };
	});

</script>
</head>
<body data-ng-app="app" data-ng-controller="MainController" >
	<div class="container" data-ng-init="getTasksDataFromServer()">
     <h1>{{title}}</h1>
			<ul name="tasks">
	  		  <li class="list-group-item" class="list-group" ng-repeat="task in todoList"   ng-click="removeTask(task.task);" name="{{task.task}}"><span class="glyphicon glyphicon-star"></span> {{task.task}} </li>
			</ul>
	<label>Add task:</label>

			<div class="input-group">
     	 		<input type="text" ng-model="taskToAdd" class="form-control" id="input"  	 	ng-keypress="
     	 	  				code = ($event.keyCode ? $event.keyCode : $event.which);
							if(code == 13) { //Enter keycode
 									alert('enter press');
							}"></input> 
     	 	  	<span class="input-group-btn"><button class="btn btn-default" type="button" ng-click="addTask()" id="add">ADD</button></span> 
   	 	
    		</div>

	</div>
</body>
</html>
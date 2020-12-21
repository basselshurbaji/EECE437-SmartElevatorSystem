all: GUI dataMonitor demo elevator elevatorManager floor userInteraction
clean:
GUI:
	javac GUI/DataObject.java GUI/GUI.java GUI/GsonHandler.java GUI/Request.java GUI/userRequest.java
dataMonitor:
	javac dataMonitor/DataMonitor.java dataMonitor/ElevatorEvent.java dataMonitor/Loggable.java
demo:
	javac demo/IntegratedDemo.java demo/User.java
elevator:
	javac elevator/Elevator.java elevator/ElevatorDoorStatus.java elevator/ElevatorObserver.java elevator/EleveatorRequestType.java elevator/ElevatorStatus.java
elevatorManager:
	javac elevatorManager/ElevatorManager.java elevatorManager/ElevatorManagerDestinationRequest.java elevatorManager/ElevatorManagerMode.java elevatorManager/ElevatorManagerRequest.java
floor:
	javac floor/Floor.java  floor/FloorPriority.java
userInteraction:
	javac userInteraction/UserInteraction.java userInteraction/UserPriority.java

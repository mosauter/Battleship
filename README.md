Battleship
==========

Repository for the Game Battleship implemented in Java. With this Application we implement many different _Design Patterns_ by the GOF.
For example:
* Chain of responsibility
* Decorator
* Singleton

There should be more than one UI can be running at the same time showing the same game. So for that we used the _Observer Pattern_ to keep the UIs up to date and more important in sync.
The whole Application is implemented with a Model-View-Controller Architecture in a strict way. So the View (UI) communicates only with the controller while the controller layer is the only one which communicates to the model.

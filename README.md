## Getting Started

the main function is within `App.java` please just run this. The test input is feeded into the console app and it will print the rover ending positions and facing direction as output.


## statement

the verified output form input 
```
3 3 E
MMRMMRMRRM

```
is 

```
1 3 N
4 1 E
```
which is different from the given answer, the reason is that if the grid is init as `5*5` matrix, so index 4 is the boundary, and `MMRMMRMRRM`, first `MMR` will make it hit on wall.
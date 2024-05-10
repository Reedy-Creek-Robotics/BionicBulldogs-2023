#include "Macros.hpp"

/*
add your functions here
format is 'Function' return type '_' argument types
return types:
  V = void
  C = bool but if true stops opmode
argument types:
  S = string
  D = double
  B = bool
  V = void
current macros are:
FunctionV_S
FunctionC_D
FunctionV_B
FunctionV_V
FunctionV_SS
FunctionV_D

Classes:
call NewClass to make a class
call EndClass with class name to end the class
*/

FunctionV_S(print, print);

FunctionC_D(delay, delay);
FunctionV_B(isActive, isActive);

NewClass();
FunctionV_SS(telem, addData);
FunctionV_V(updateTelem, update);
EndClass(telem);

SetJavaObject(org/firstinspires/ftc/teamcode/opmode/lua/TestModule);

NewClass();
FunctionV_D(setPos, setPos);
FunctionV_D(setPos2, setPos2);
EndClass(servos);

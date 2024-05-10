#include "Macros.hpp"

FunctionS(print, print);

FunctionCD(delay, delay);
FunctionVB(isActive, isActive);

NewClass();
FunctionSS(telem, addData);
FunctionV(updateTelem, update);
EndClass(telem);

NewClass();
FunctionD(setPos, setPos);
FunctionD(setPos2, setPos2);
EndClass(servos);

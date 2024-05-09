#include "Macros.hpp"

FunctionS(print);

FunctionCD(delay);
FunctionVB(isActive);

NewClass();
CFunctionSS(telem, addData);
CFunctionV(updateTelem, update);
EndClass(telem);

NewClass();
CFunctionD(setPos, setPos);
CFunctionD(setPos2, setPos2);
EndClass(servos);

#include "Functions.hpp"
#include "JFunc.hpp"
#include "Macros.hpp"

namespace Functions
{
FunctionDefS(print);

FunctionDefSS(telem);
FunctionDefV(updateTelem);

FunctionDefCD(delay);
FunctionDefVB(isActive);

FunctionDefD(setPos);
FunctionDefD(setPos2);

void loadFunctions(lua_State* l)
{
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
}
} // namespace Functions

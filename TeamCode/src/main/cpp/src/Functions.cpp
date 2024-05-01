#include "Functions.hpp"
#include "JFunc.hpp"
#include "Macros.hpp"

namespace Functions
{
FunctionDefCD(delay);
FunctionDefVB(isActive);

FunctionDefD(setPos);
FunctionDefD(setPos2);
FunctionDefS(print);

void loadFunctions(lua_State* l)
{
	FunctionCD(delay);
  FunctionVB(isActive);

	FunctionD(setPos);
	FunctionD(setPos2);
	FunctionS(print);
}
} // namespace Functions

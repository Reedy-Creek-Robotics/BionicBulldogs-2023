#include "JFunc.hpp"
#include <LoadFunc.hpp>
#include <lua/lua.hpp>

#define MacroDef
#include "Functions.hpp"

#undef MacroDef

void loadFuncs(lua_State* l)
{
	bool inClass = false;
#include "Functions.hpp"
}

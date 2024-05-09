#include "JFunc.hpp"
#include <lua/lua.hpp>
#include <LoadFunc.hpp>

#define MacroDef
#include "Functions.hpp"

#undef MacroDef

void loadFuncs(lua_State* l)
{
#include "Functions.hpp"
}

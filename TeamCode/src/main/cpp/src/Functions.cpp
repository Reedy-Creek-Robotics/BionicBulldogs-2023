#include "JFunc.hpp"
#include <LoadFunc.hpp>
#include <lua/lua.hpp>
#include <unordered_map>

#define MacroDef

#include "Functions.hpp"

#undef MacroDef

std::unordered_map<jclass, jobject> objects = {};

void addObject(jobject object)
{
  jclass clazz = FuncStat::env->GetObjectClass(object);
  objects[clazz] = FuncStat::env->NewGlobalRef(object);
}

void loadFuncs(lua_State* l)
{
	bool inClass = false;
#include "Functions.hpp"
}

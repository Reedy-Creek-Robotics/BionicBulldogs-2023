#include "JFunc.hpp"
#include "LoadFunc.hpp"
#include <lua/lua.hpp>
#include <unordered_map>
#include <string>
#define MacroDef

#include "Functions.hpp"

#undef MacroDef

std::unordered_map<std::string, jobject> objects = {};

void addObject(jobject object)
{
  jclass clazz = FuncStat::env->GetObjectClass(object);
  jmethodID mid = FuncStat::env->GetMethodID(clazz, "getClass", "()Ljava/lang/Class;");
  jobject clsObj = FuncStat::env->CallObjectMethod(object, mid);
  jclass clazzz = FuncStat::env->GetObjectClass(clsObj);
  mid = FuncStat::env->GetMethodID(clazzz, "getCanonicalName", "()Ljava/lang/String;");
  jstring strObj = (jstring)FuncStat::env->CallObjectMethod(clsObj, mid);
  
  const char* str = FuncStat::env->GetStringUTFChars(strObj, NULL);
  std::string res(str);
  
  FuncStat::env->ReleaseStringUTFChars(strObj, str);
  
  for(char& c : res)
  {
    if(c == '.')
    {
      c = '/';
    }
  }
  
  objects[res] = FuncStat::env->NewGlobalRef(object);
}

void loadFuncs(lua_State* l)
{
	bool inClass = false;
#include "Functions.hpp"
}

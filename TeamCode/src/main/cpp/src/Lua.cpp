#include "LoadFunc.hpp"
#include "JFunc.hpp"
#include <lua/lua.hpp>
#include <string>
#include <unordered_map>

static lua_State* l = nullptr;
JFunc<void, jstring> printF;
JFunc<void, jstring> errorF;
void print(const char* str)
{
	jstring j = printF.env->NewStringUTF(str);
	printF.callV(j);
	printF.env->ReleaseStringUTFChars(j, printF.env->GetStringUTFChars(j, NULL));
}
void err(const char* str)
{
	jstring j = errorF.env->NewStringUTF(str);
	errorF.callV(j);
}

std::unordered_map<std::string, int> opmodes;
int dispMarkerInd = 0;

std::string getPathName(const std::string& name)
{
	int i = 0;
	for (auto& [k, v] : opmodes)
	{
		if (name == k)
		{
			i = v;
			break;
		}
	}
	lua_getglobal(l, "Opmodes");
	lua_rawgeti(l, -1, i);
	lua_getfield(l, -1, "path");
	if (lua_type(l, -1) == LUA_TSTRING)
	{
		std::string str = lua_tostring(l, -1);
		lua_pop(l, 3);
		return str;
	}
	lua_pop(l, 3);
	return "";
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_init(JNIEnv* env,
																								   jobject thiz)
{
  if(l != nullptr)
  {
    lua_close(l);
  }
	jobject ref = env->NewGlobalRef(thiz);
	FuncStat::setVals(env, ref);

	printF.init("print", "(Ljava/lang/String;)V");
	errorF.init("err", "(Ljava/lang/String;)V");

	JFunc<jstring> getDataDir("getDataDir", "()Ljava/lang/String;");

	jstring dataDirJ = getDataDir.call();
	const char* dataDirC = env->GetStringUTFChars(dataDirJ, NULL);
	std::string dataDir = dataDirC;
	env->ReleaseStringUTFChars(dataDirJ, dataDirC);

	FuncStat::storageDir = dataDir;

	l = luaL_newstate();
  luaL_openlibs(l);

	loadFuncs(l);

	if (luaL_dofile(l, (dataDir + "/lua/main.lua").c_str()))
	{
		err(lua_tostring(l, -1));
		return NULL;
	}
	lua_getglobal(l, "Opmodes");
	if (lua_type(l, -1) != LUA_TTABLE)
	{
		err("opmodes table must be a table");
		return NULL;
	}
	lua_pushnil(l);

	int count = 0;
	while (lua_next(l, -2))
	{
		count++;
		lua_pushvalue(l, -2);
		std::string key = lua_tostring(l, -1);

		if (lua_type(l, -2) != LUA_TTABLE)
		{
			lua_pop(l, 2);
			err("opmode must be a table");
			return NULL;
		}

		lua_getfield(l, -2, "name");
		if (lua_type(l, -1) != LUA_TSTRING)
		{
			err("opmode name must be a string");
			return NULL;
		}
		std::string name = lua_tostring(l, -1);
		lua_pop(l, 1);
		opmodes.emplace(name, stoi(key));

		lua_pop(l, 2);
	}
	lua_pop(l, 1);

	jobjectArray arr = env->NewObjectArray(count, env->FindClass("java/lang/String"), env->NewStringUTF("T"));
	int i = 0;
	for (auto& [k, v] : opmodes)
	{
		env->SetObjectArrayElement(arr, i++, env->NewStringUTF(k.c_str()));
	}
	return arr;
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_start(JNIEnv* env, jobject thiz,
																							jstring name,
																							int recognition)
{
  lua_settop(l, 0);
  lua_newtable(l);
	FuncStat::obj = thiz;
	lua_getglobal(l, "Opmodes");
	int ind = -1;
	const char* c = env->GetStringUTFChars(name, NULL);
	for (auto& [k, v] : opmodes)
	{
		if (k == c)
		{
			ind = v;
			break;
		}
	}
	if (ind == -1)
	{
		return;
	}

	lua_rawgeti(l, -1, ind);
	lua_getfield(l, -1, "start");
	if (lua_type(l, -1) == LUA_TFUNCTION)
	{
    lua_pushvalue(l, 1);
    lua_pushinteger(l, recognition);
		if (lua_pcall(l, 2, 0, 0))
		{
			err(lua_tostring(l, -1));
			return;
		}
	}
	lua_settop(l, 1);
	lua_getglobal(l, "Opmodes");
	lua_rawgeti(l, -1, ind);
	lua_getfield(l, -1, "markers");
	if (lua_type(l, -1) != LUA_TTABLE)
	{
		lua_pop(l, 1);
		lua_newtable(l);
	}
	dispMarkerInd = 0;
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_stop(JNIEnv* env, jobject thiz)
{
	lua_close(l);
  l = nullptr;
}
void callNextDispMarker()
{
	dispMarkerInd++;
	lua_rawgeti(l, 4, dispMarkerInd);
	if (lua_type(l, -1) == LUA_TFUNCTION)
	{
    lua_pushvalue(l, 1);
		if (lua_pcall(l, 1, 0, 0))
		{
			err(lua_tostring(l, -1));
			return;
		}
	}
}

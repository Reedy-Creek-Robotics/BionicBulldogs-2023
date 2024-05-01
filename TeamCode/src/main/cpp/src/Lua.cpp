#include "Functions.hpp"
#include "JFunc.hpp"
#include <lua/lua.hpp>
#include <string>
#include <unordered_map>

lua_State* l;
JFunc<void, jstring> printF;
void print(const char* str)
{
	jstring j = FuncStat::env->NewStringUTF(str);
	printF.callV(j);
	FuncStat::env->ReleaseStringUTFChars(j, FuncStat::env->GetStringUTFChars(j, NULL));
}

std::unordered_map<std::string, int> opmodes;

extern "C" JNIEXPORT jobjectArray JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_init(JNIEnv* env,
																								   jobject thiz)
{
	FuncStat::setVals(env, thiz);

	printF.init("print", "(Ljava/lang/String;)V");

	JFunc<jstring> getDataDir("getDataDir", "()Ljava/lang/String;");

	jstring dataDirJ = getDataDir.call();
	const char* dataDirC = env->GetStringUTFChars(dataDirJ, NULL);
	std::string dataDir = dataDirC;
	env->ReleaseStringUTFChars(dataDirJ, dataDirC);

	l = luaL_newstate();

	Functions::loadFunctions(l);

	if (luaL_dofile(l, (dataDir + "/lua/main.lua").c_str()))
	{
		print((std::string("lua error: ") + lua_tostring(l, -1)).c_str());
		return NULL;
	}
	lua_getglobal(l, "opmodes");
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
			print("opmode must be a table");
			return NULL;
		}

		lua_getfield(l, -2, "name");
		if (lua_type(l, -1) != LUA_TSTRING)
		{
			print("opmode name must be a string");
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
		print(k.c_str());
		env->SetObjectArrayElement(arr, i++, env->NewStringUTF(k.c_str()));
	}
	return arr;
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_start(JNIEnv* env, jobject thiz,
																							jstring name)
{
	FuncStat::obj = thiz;
	lua_getglobal(l, "opmodes");
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
		if (lua_pcall(l, 0, 0, 0))
		{
			print((std::string("lua error: ") + lua_tostring(l, -1)).c_str());
		}
	}
	else
	{
		print("opmode start function must be a function");
	}
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_stop(JNIEnv* env, jobject thiz)
{
	lua_close(l);
}

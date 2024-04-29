#include "JFunc.hpp"
#include "jni.h"
#include <lua/lua.hpp>

JFunc<void, jstring> log;

int print(lua_State* l)
{
	const char* str = lua_tostring(l, -1);
	jstring jstr = FuncStat::env->NewStringUTF(str);
	log.callV(jstr);
	FuncStat::env->ReleaseStringUTFChars(jstr, FuncStat::env->GetStringUTFChars(jstr, NULL));
	return 0;
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_dothing(JNIEnv* env, jobject thiz)
{
	jstring jstr = env->NewStringUTF("This comes from jni.");
	FuncStat::setVals(env, thiz);

	log.init("log", "(Ljava/lang/String;)V");

	log.callV(jstr);

	const char* str = env->GetStringUTFChars(jstr, NULL);
	env->ReleaseStringUTFChars(jstr, str);

	lua_State* l = luaL_newstate();
	lua_pushcfunction(l, print);
	lua_setglobal(l, "print");
	luaL_dostring(l, R"(
                  print("this is a string");
                  )");
}

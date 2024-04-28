#include <lua/lua.hpp>
#include "JFunc.hpp"

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_dothing(JNIEnv* env, jobject thiz)
{
	jstring jstr = env->NewStringUTF("This comes from jni.");
	FuncStat::setVals(env, thiz);

	JFunc<void, jstring> log("log", "(Ljava/lang/String;)V");

	log.callV(jstr);

	const char* str = env->GetStringUTFChars(jstr, NULL);
	env->ReleaseStringUTFChars(jstr, str);

	// lua_State* l = luaL_newstate();
	// luaL_dofile(l, "main.lua");
}

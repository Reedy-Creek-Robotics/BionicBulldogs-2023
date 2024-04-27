#include "include/lua/lua.hpp"
#include <iostream>
#include <jni.h>

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_Lua_dothing(JNIEnv* env, jobject thiz)
{
	jstring jstr = env->NewStringUTF("This comes from jni.");
	jclass clazz = env->GetObjectClass(thiz);
	jmethodID messageMe = env->GetMethodID(clazz, "log", "(Ljava/lang/String;)V");
	env->CallVoidMethod(thiz, messageMe, jstr);

	const char* str = env->GetStringUTFChars(jstr, NULL);
	env->ReleaseStringUTFChars(jstr, str);

	lua_State* l = luaL_newstate();
	luaL_dofile(l, "main.lua");
}

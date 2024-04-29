#pragma once
#include <jni.h>
struct FuncStat
{
	static jobject obj;
	static jclass clazz;
	static JNIEnv* env;
	static void setVals(JNIEnv* _env, jobject& _obj);
};

template <typename T, typename... Types> class JFunc
{
  public:
	JFunc();
	JFunc(const char* name, const char* sig)
	{
		init(name, sig);
	}
	void init(const char* name, const char* sig)
	{
		method = FuncStat::env->GetMethodID(FuncStat::clazz, name, sig);
	}
	void callV(Types... args)
	{
		FuncStat::env->CallVoidMethod(FuncStat::obj, method, args...);
	}
	T call(Types... args)
	{
		return (T)FuncStat::env->CallObjectMethod(FuncStat::obj, method, args...);
	}

  private:
	jmethodID method;
};

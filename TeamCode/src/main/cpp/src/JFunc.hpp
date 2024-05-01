#pragma once
#include <jni.h>
struct FuncStat
{
	static jobject obj;
	static jclass clazz;
	static JNIEnv* env;
	static void setVals(JNIEnv* _env, jobject& _obj);
};

template <typename T, typename... Args> class JFunc
{
  public:
	JFunc()
	{
	}
	JFunc(const char* name, const char* sig)
	{
		init(name, sig);
	}
	void init(const char* name, const char* sig)
	{
		method = FuncStat::env->GetMethodID(FuncStat::clazz, name, sig);
	}
	void callV(Args... args)
	{
		FuncStat::env->CallVoidMethod(FuncStat::obj, method, args...);
	}
	bool callB(Args... args)
	{
		return FuncStat::env->CallBooleanMethod(FuncStat::obj, method, args...);
	}
	T call(Args... args)
	{
		return (T)FuncStat::env->CallObjectMethod(FuncStat::obj, method, args...);
	}

  private:
	jmethodID method;
};

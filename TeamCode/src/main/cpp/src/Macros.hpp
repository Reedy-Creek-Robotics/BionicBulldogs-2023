#ifdef FunctionV_D
#undef FunctionV_D
#undef FunctionV_B
#undef FunctionV_V
#undef FunctionV_SS
#undef FunctionV_S
#undef FunctionC_D
#undef NewClass
#undef EndClass
#undef SetJavaObject
#undef err
#endif

#ifndef MacroDef
#define err() int* i; *i = 1
#define SetJavaObject(className)                                                                                       \
	FuncStat::clazz = FuncStat::env->FindClass(#className);                                                            \
	FuncStat::obj = objects[#className]

#define NewClass()                                                                                                     \
	lua_newtable(l);                                                                                                   \
	inClass = true

#define EndClass(name)                                                                                                 \
	lua_setglobal(l, #name);                                                                                           \
	inClass = false

#define FunctionC_D(name, funcName)                                                                                    \
	name.init(#name, "(D)Z");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV_D(name, funcName)                                                                                    \
	name.init(#name, "(D)V");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV_S(name, funcName)                                                                                    \
	name.init(#name, "(Ljava/lang/String;)V");                                                                         \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV_SS(name, funcName)                                                                                   \
	name.init(#name, "(Ljava/lang/String;Ljava/lang/String;)V");                                                       \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV_V(name, funcName)                                                                                    \
	name.init(#name, "()V");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV_B(name, funcName)                                                                                    \
	name.init(#name, "()Z");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (!inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#endif
#ifdef MacroDef
#define err()
#define SetJavaObject(className)

#define FunctionC_D(name, _)                                                                                           \
	JFunc<jboolean, jdouble> name;                                                                                     \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		jdouble i = lua_tonumber(L, -1);                                                                               \
		bool a = name.callB(i);                                                                                        \
		if (a)                                                                                                         \
		{                                                                                                              \
			luaL_error(L, "robot stopped :)");                                                                         \
		}                                                                                                              \
		return 0;                                                                                                      \
	}

#define FunctionV_D(name, _)                                                                                           \
	JFunc<void, jdouble> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		jdouble i = lua_tonumber(L, -1);                                                                               \
		name.callV(i);                                                                                                 \
		return 0;                                                                                                      \
	}

#define FunctionV_S(name, _)                                                                                           \
	JFunc<void, jstring> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		const char* i = lua_tostring(L, -1);                                                                           \
		jstring j = FuncStat::env->NewStringUTF(i);                                                                    \
		name.callV(j);                                                                                                 \
		FuncStat::env->ReleaseStringUTFChars(j, FuncStat::env->GetStringUTFChars(j, NULL));                            \
		return 0;                                                                                                      \
	}

#define FunctionV_SS(name, _)                                                                                          \
	JFunc<void, jstring, jstring> name;                                                                                \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		const char* i1 = lua_tostring(L, 1);                                                                           \
		const char* i2 = lua_tostring(L, 2);                                                                           \
		jstring j1 = FuncStat::env->NewStringUTF(i1);                                                                  \
		jstring j2 = FuncStat::env->NewStringUTF(i2);                                                                  \
		name.callV(j1, j2);                                                                                            \
		FuncStat::env->ReleaseStringUTFChars(j1, FuncStat::env->GetStringUTFChars(j1, NULL));                          \
		FuncStat::env->ReleaseStringUTFChars(j2, FuncStat::env->GetStringUTFChars(j2, NULL));                          \
		return 0;                                                                                                      \
	}

#define FunctionV_V(name, _)                                                                                           \
	JFunc<void> name;                                                                                                  \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		name.callV();                                                                                                  \
		return 0;                                                                                                      \
	}

#define FunctionV_B(name, _)                                                                                           \
	JFunc<jboolean> name;                                                                                              \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		bool a = name.callB();                                                                                         \
		lua_pushboolean(L, a);                                                                                         \
		return 1;                                                                                                      \
	}

#define NewClass()

#define EndClass(name)

#endif

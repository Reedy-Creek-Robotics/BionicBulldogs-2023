#ifdef FunctionD
#undef FunctionD
#undef FunctionVB
#undef FunctionV
#undef FunctionSS
#undef FunctionS
#undef FunctionCD
#undef NewClass
#undef EndClass
#endif

#ifndef MacroDef

#define NewClass()                                                                                                     \
	lua_newtable(l);                                                                                                   \
	inClass = true

#define EndClass(name)                                                                                                 \
	lua_setglobal(l, #name);                                                                                           \
	inClass = false

#define FunctionCD(name, funcName)                                                                                     \
	name.init(#name, "(D)Z");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionD(name, funcName)                                                                                      \
	name.init(#name, "(D)V");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionS(name, funcName)                                                                                      \
	name.init(#name, "(Ljava/lang/String;)V");                                                                         \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionSS(name, funcName)                                                                                     \
	name.init(#name, "(Ljava/lang/String;Ljava/lang/String;)V");                                                       \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionV(name, funcName)                                                                                      \
	name.init(#name, "()V");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#define FunctionVB(name, funcName)                                                                                     \
	name.init(#name, "()Z");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	if (inClass)                                                                                                       \
	{                                                                                                                  \
		lua_setglobal(l, #funcName);                                                                                   \
	}                                                                                                                  \
	else                                                                                                               \
	{                                                                                                                  \
		lua_setfield(l, -2, #funcName);                                                                                \
	}

#endif
#ifdef MacroDef

#define FunctionCD(name, _)                                                                                            \
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

#define FunctionD(name, _)                                                                                             \
	JFunc<void, jdouble> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		jdouble i = lua_tonumber(L, -1);                                                                               \
		name.callV(i);                                                                                                 \
		return 0;                                                                                                      \
	}

#define FunctionS(name, _)                                                                                             \
	JFunc<void, jstring> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		const char* i = lua_tostring(L, -1);                                                                           \
		jstring j = FuncStat::env->NewStringUTF(i);                                                                    \
		name.callV(j);                                                                                                 \
		FuncStat::env->ReleaseStringUTFChars(j, FuncStat::env->GetStringUTFChars(j, NULL));                            \
		return 0;                                                                                                      \
	}

#define FunctionSS(name, _)                                                                                            \
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

#define FunctionV(name, _)                                                                                             \
	JFunc<void> name;                                                                                                  \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		name.callV();                                                                                                  \
		return 0;                                                                                                      \
	}

#define FunctionVB(name, _)                                                                                            \
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

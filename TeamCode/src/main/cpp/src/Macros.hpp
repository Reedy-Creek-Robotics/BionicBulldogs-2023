// standalone functions
#define FunctionCD(name)                                                                                               \
	name.init(#name, "(D)Z");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefCD(name)                                                                                            \
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

#define FunctionD(name)                                                                                                \
	name.init(#name, "(D)V");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefD(name)                                                                                             \
	JFunc<void, jdouble> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		jdouble i = lua_tonumber(L, -1);                                                                               \
		name.callV(i);                                                                                                 \
		return 0;                                                                                                      \
	}

#define FunctionS(name)                                                                                                \
	name.init(#name, "(Ljava/lang/String;)V");                                                                         \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefS(name)                                                                                             \
	JFunc<void, jstring> name;                                                                                         \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		const char* i = lua_tostring(L, -1);                                                                           \
		jstring j = FuncStat::env->NewStringUTF(i);                                                                    \
		name.callV(j);                                                                                                 \
		FuncStat::env->ReleaseStringUTFChars(j, FuncStat::env->GetStringUTFChars(j, NULL));                            \
		return 0;                                                                                                      \
	}

#define FunctionSS(name)                                                                                               \
	name.init(#name, "(Ljava/lang/String;Ljava/lang/String;)V");                                                       \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefSS(name)                                                                                            \
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

#define FunctionV(name)                                                                                                \
	name.init(#name, "()V");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefV(name)                                                                                             \
	JFunc<void> name;                                                                                                  \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		name.callV();                                                                                                  \
		return 0;                                                                                                      \
	}

#define FunctionVB(name)                                                                                               \
	name.init(#name, "()Z");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefVB(name)                                                                                            \
	JFunc<jboolean> name;                                                                                              \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		bool a = name.callB();                                                                                         \
		lua_pushboolean(L, a);                                                                                         \
		return 1;                                                                                                      \
	}

// class functions

#define NewClass() lua_newtable(l)

#define EndClass(name) lua_setglobal(l, #name)

#define CFunctionCD(name, funcName)                                                                                    \
	name.init(#name, "(D)Z");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

#define CFunctionD(name, funcName)                                                                                     \
	name.init(#name, "(D)V");                                                                                          \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

#define CFunctionS(name, funcName)                                                                                     \
	name.init(#name, "(Ljava/lang/String;)V");                                                                         \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

#define CFunctionSS(name, funcName)                                                                                    \
	name.init(#name, "(Ljava/lang/String;Ljava/lang/String;)V");                                                       \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

#define CFunctionV(name, funcName)                                                                                     \
	name.init(#name, "()V");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

#define CFunctionVB(name, funcName)                                                                                    \
	name.init(#name, "()Z");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setfield(l, -2, #funcName)

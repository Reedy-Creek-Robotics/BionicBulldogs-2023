
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

#define FunctionV(name)                                                                                                \
	name.init(#name, "()V");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefV(name)                                                                                             \
	JFunc<void> name;                                                                                            \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		name.call();                                                                                                   \
		return 0;                                                                                                      \
	}

#define FunctionVB(name)                                                                                               \
	name.init(#name, "()Z");                                                                                           \
	lua_pushcfunction(l, name##F);                                                                                     \
	lua_setglobal(l, #name)

#define FunctionDefVB(name)                                                                                            \
	JFunc<jboolean> name;                                                                                        \
	int name##F(lua_State* L)                                                                                          \
	{                                                                                                                  \
		bool a = name.callB();                                                                                         \
		lua_pushboolean(L, a);                                                                                         \
		return 1;                                                                                                      \
	}

import { createContext, useContext, useEffect, useReducer } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGOUT = "LOGOUT";

const authReducer = (prevState, action) => {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return {
        ...prevState,
        token: action.payload.token,
        user: action.payload.user,
        isAuthenticated: true,
      };
    case LOGOUT:
      return {
        ...prevState,
        token: null,
        user: null,
        isAuthenticated: false,
      };
    default:
      return prevState;
  }
};

export const AuthProvider = ({ children }) => {
  const initialAuthState = (() => {
    try {
      const token = localStorage.getItem("token");
      const user = localStorage.getItem("user");

      if (token && user) {
        return {
          token,
          user: JSON.parse(user),
          isAuthenticated: true,
        };
      }
    } catch (error) {
      console.error("Failed to parse auth from localStorage:", error);
    }

    return {
      token: null,
      user: null,
      isAuthenticated: false,
    };
  })();

  const [authState, dispatch] = useReducer(authReducer, initialAuthState);

  useEffect(() => {
    try {
      if (authState.isAuthenticated) {
        localStorage.setItem("token", authState.token);
        localStorage.setItem("user", JSON.stringify(authState.user));
      } else {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
      }
    } catch (error) {
      console.error("Failed to save auth to localStorage:", error);
    }
  }, [authState]);

  const loginSuccess = (token, user) => {
    dispatch({ type: LOGIN_SUCCESS, payload: { token, user } });
  };

  const logout = () => {
    dispatch({ type: LOGOUT });
  };

  return (
    <AuthContext.Provider
      value={{
        token: authState.token,
        user: authState.user,
        isAuthenticated: authState.isAuthenticated,
        loginSuccess,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

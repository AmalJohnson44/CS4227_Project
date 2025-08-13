
'use client';

import React, { createContext, useContext, useEffect, useState } from 'react';

type AuthState = {
  token: string | null;
  username: string | null;
  role: 'PATIENT' | 'DOCTOR' | 'ADMIN' | null;
};

type Ctx = AuthState & {
  login: (token: string, username: string, role: AuthState['role']) => void;
  logout: () => void;
};

const AuthContext = createContext<Ctx>({
  token: null, username: null, role: null,
  login: () => {}, logout: () => {}
});

export const AuthProvider: React.FC<{children: React.ReactNode}> = ({ children }) => {
  const [token, setToken] = useState<string|null>(null);
  const [username, setUsername] = useState<string|null>(null);
  const [role, setRole] = useState<AuthState['role']>(null);

  useEffect(() => {
    const t = localStorage.getItem('jwt'); const u = localStorage.getItem('username'); const r = localStorage.getItem('role');
    if (t) setToken(t); if (u) setUsername(u); if (r) setRole(r as any);
  }, []);

  const login = (t: string, u: string, r: AuthState['role']) => {
    setToken(t); setUsername(u); setRole(r);
    localStorage.setItem('jwt', t); localStorage.setItem('username', u); if (r) localStorage.setItem('role', r);
  };

  const logout = () => {
    setToken(null); setUsername(null); setRole(null);
    localStorage.removeItem('jwt'); localStorage.removeItem('username'); localStorage.removeItem('role');
  };

  return <AuthContext.Provider value={{ token, username, role, login, logout }}>{children}</AuthContext.Provider>;
};

export const useAuth = () => useContext(AuthContext);

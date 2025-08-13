
'use client';
import { useRouter } from 'next/navigation';
import { useEffect } from 'react';
import { useAuth } from './AuthContext';

export default function Protected({ children, roles }: { children: React.ReactNode, roles?: Array<'PATIENT'|'DOCTOR'|'ADMIN'> }) {
  const { token, role } = useAuth();
  const router = useRouter();

  useEffect(() => {
    if (!token) router.replace('/login');
    if (roles && role && !roles.includes(role)) router.replace('/dashboard');
  }, [token, role, roles, router]);

  return <>{children}</>;
}

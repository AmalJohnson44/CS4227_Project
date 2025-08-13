
'use client';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { useAuth } from './AuthContext';

export default function NavLinks() {
  const pathname = usePathname();
  const { token, role, logout, username } = useAuth();

  const Item = (href: string, label: string) => (
    <Link href={href} className={pathname === href ? 'active' : ''}>{label}</Link>
  );

  return (
    <>
      {Item('/', 'Home')}
      {!token && Item('/login', 'Login')}
      {token && Item('/dashboard', 'Dashboard')}
      {token && role === 'PATIENT' && Item('/patient/appointments', 'My Appointments')}
      {token && role === 'DOCTOR' && Item('/doctor/appointments', 'Doctor')}
      {token && role === 'ADMIN' && Item('/admin/appointments', 'Admin')}
      {token && <a onClick={logout} style={{cursor:'pointer'}}>Logout{username ? ` (${username})` : ''}</a>}
    </>
  );
}

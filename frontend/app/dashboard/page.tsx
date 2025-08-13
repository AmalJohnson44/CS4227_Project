
'use client';
import Protected from '../../components/Protected';
import { useAuth } from '../../components/AuthContext';
import Link from 'next/link';

export default function Dashboard() {
  const { role, username } = useAuth();
  return (
    <Protected>
      <div className="card">
        <h2>Dashboard</h2>
        <p>Hello {username || 'User'} — signed in as <b>{role}</b>.</p>
        {role === 'PATIENT' && (
          <ul>
            <li><Link href="/patient/appointments">Manage my appointments</Link></li>
            <li><Link href="/records">View medical records</Link></li>
            <li><Link href="/notifications">Notifications</Link></li>
          </ul>
        )}
        {role === 'DOCTOR' && (
          <ul>
            <li><Link href="/doctor/appointments">View/Manage assigned appointments</Link></li>
            <li><Link href="/records">Update patient records</Link></li>
          </ul>
        )}
        {role === 'ADMIN' && (
          <ul>
            <li><Link href="/admin/appointments">Confirm bookings</Link></li>
            <li><Link href="/admin/payments">Confirm payments</Link></li>
          </ul>
        )}
      </div>
    </Protected>
  );
}

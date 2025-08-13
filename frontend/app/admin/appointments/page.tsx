
'use client';
import { useEffect, useState } from 'react';
import Protected from '../../../components/Protected';
import { useAuth } from '../../../components/AuthContext';
import { api } from '../../../lib/api';

export default function AdminAppointments() {
  const { token } = useAuth();
  const [list, setList] = useState<any[]>([]);

  async function load() {
    const data = await api('/api/appointments', {}, token || undefined);
    setList(data);
  }
  useEffect(()=>{ load(); }, []);

  async function confirm(appointmentId: string) {
    await api(`/api/admin/confirm-booking/${appointmentId}`, { method: 'POST' }, token || undefined);
    await load();
  }

  async function amend(appointmentId: string) {
    await api(`/api/admin/amend-appointment/${appointmentId}`, { method: 'PUT', body: JSON.stringify({ adminNote: 'Rescheduled' }) }, token || undefined);
    await load();
  }

  async function cancel(appointmentId: string) {
    await api(`/api/admin/cancel-appointment/${appointmentId}`, { method: 'POST' }, token || undefined);
    await load();
  }

  return (
    <Protected roles={['ADMIN']}>
      <div className="card">
        <h2>Admin — Manage Appointments</h2>
        <table>
          <thead><tr><th>Patient</th><th>Doctor</th><th>Date</th><th>Time</th><th>Status</th><th>Actions</th></tr></thead>
          <tbody>
            {list.map((a:any)=> (
              <tr key={a.id}>
                <td>{a.patient}</td><td>{a.doctor}</td><td>{a.date}</td><td>{a.time}</td><td>{a.status||'PENDING'}</td>
                <td>
                  <button className="btn secondary" onClick={()=>confirm(a.id)}>Confirm</button>{' '}
                  <button className="btn secondary" onClick={()=>amend(a.id)}>Amend</button>{' '}
                  <button className="btn secondary" onClick={()=>cancel(a.id)}>Cancel</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </Protected>
  );
}

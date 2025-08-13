
'use client';
import { useEffect, useState } from 'react';
import Protected from '../../../components/Protected';
import { useAuth } from '../../../components/AuthContext';
import { api } from '../../../lib/api';

export default function PatientAppointments() {
  const { token } = useAuth();
  const [list, setList] = useState<any[]>([]);
  const [form, setForm] = useState({ type: 'Checkup', surgeryOrHospital: 'City Clinic', doctor: 'Dr. Smith', patient: 'patient1', time: '10:00', date: new Date().toISOString().slice(0,10) });
  const [message, setMessage] = useState<string|null>(null);

  async function load() {
    const data = await api('/api/appointments', {}, token || undefined);
    setList(data);
  }

  useEffect(()=>{ load(); }, []);

  async function create(e: any) {
    e.preventDefault();
    setMessage(null);
    await api('/api/appointments', { method: 'POST', body: JSON.stringify(form) }, token || undefined);
    setMessage('Appointment requested.'); await load();
  }

  async function amend(id: string) {
    await api(`/api/appointments/${id}/amend`, { method: 'PUT', body: JSON.stringify({ ...form, note: 'Amended time' }) }, token || undefined);
    await load();
  }

  async function cancel(id: string) {
    await api(`/api/appointments/${id}/cancel`, { method: 'POST' }, token || undefined);
    await load();
  }

  return (
    <Protected roles={['PATIENT']}>
      <div className="card">
        <h2>My Appointments</h2>
        <form onSubmit={create} className="row">
          <input className="input" placeholder="type" value={form.type} onChange={e=>setForm({...form, type:e.target.value})} />
          <input className="input" placeholder="surgery/hospital" value={form.surgeryOrHospital} onChange={e=>setForm({...form, surgeryOrHospital:e.target.value})} />
          <input className="input" placeholder="doctor" value={form.doctor} onChange={e=>setForm({...form, doctor:e.target.value})} />
          <input className="input" placeholder="patient" value={form.patient} onChange={e=>setForm({...form, patient:e.target.value})} />
          <input className="input" placeholder="time" value={form.time} onChange={e=>setForm({...form, time:e.target.value})} />
          <input className="input" type="date" value={form.date} onChange={e=>setForm({...form, date:e.target.value})} />
          <button className="btn" type="submit">Book appointment</button>
        </form>
        {message && <p>{message}</p>}
        <h3 style={{marginTop:24}}>Existing</h3>
        <table>
          <thead><tr><th>Type</th><th>Doctor</th><th>Date</th><th>Time</th><th>Status</th><th>Actions</th></tr></thead>
          <tbody>
          {list.map((a:any)=> (
            <tr key={a.id}>
              <td>{a.type}</td><td>{a.doctor}</td><td>{a.date}</td><td>{a.time}</td><td>{a.status||'PENDING'}</td>
              <td>
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

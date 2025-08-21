
'use client';
import { useEffect, useState } from 'react';
import Protected from '../../../components/Protected';
import { useAuth } from '../../../components/AuthContext';
import { api } from '../../../lib/api';

export default function DoctorAppointments() {
  const { token } = useAuth();
  const [list, setList] = useState<any[]>([]);
  const [referral, setReferral] = useState({ patientId: '', note: '' });

  async function load() {
    const data = await api('/api/appointments', {}, token || undefined);
    setList(data);
  }
  useEffect(()=>{ load(); }, []);

  async function confirm(id: string) {
    await api(`/api/appointments/${id}/confirm`, { method: 'POST' }, token || undefined);
    await load();
  }

  async function sendReferral(e: any) {
    e.preventDefault();
    await api(`/api/doctors/${referral.patientId}/send-referral`, { method: 'POST', body: JSON.stringify({ note: referral.note }) }, token || undefined);
    setReferral({ patientId: '', note: '' });
  }

  async function updateRecord(patientId: string) {
    await api(`/api/doctors/update-patient-records/${patientId}`, { method: 'PUT', body: JSON.stringify({ description: 'Visit notes' }) }, token || undefined);
    alert('Record updated');
  }

  return (
    <Protected roles={['DOCTOR']}>
      <div className="card">
        <h2>Doctor — Appointments</h2>
        <table>
          <thead><tr><th>Patient</th><th>Type</th><th>Date</th><th>Time</th><th>Status</th><th>Actions</th></tr></thead>
          <tbody>
            {list.map((a:any)=> (
              <tr key={a.id}>
                <td>{a.patient}</td><td>{a.type}</td><td>{a.date}</td><td>{a.time}</td><td>{a.status||'PENDING'}</td>
                <td><button className="btn secondary" onClick={()=>confirm(a.id)}>Confirm</button>{' '}
                    <button className="btn secondary" onClick={()=>updateRecord(a.patient)}>Update Record</button></td>
              </tr>
            ))}
          </tbody>
        </table>
        <h3 style={{marginTop:24}}>Send Referral</h3>
        <form onSubmit={sendReferral} className="row">
          <input className="input" placeholder="patientId" value={referral.patientId} onChange={e=>setReferral({...referral, patientId:e.target.value})} />
          <input className="input" placeholder="note" value={referral.note} onChange={e=>setReferral({...referral, note:e.target.value})} />
          <button className="btn" type="submit">Send</button>
        </form>
      </div>
    </Protected>
  );
}


'use client';
import Protected from '../../../components/Protected';
import { useAuth } from '../../../components/AuthContext';
import { api } from '../../../lib/api';
import { useState } from 'react';

export default function AdminPayments() {
  const { token } = useAuth();
  const [patientId, setPatientId] = useState('patient1');

  async function confirmPayment() {
    await api(`/api/admin/confirm-payment/${patientId}`, { method: 'POST' }, token || undefined);
    alert('Payment confirmed');
  }

  return (
    <Protected roles={['ADMIN']}>
      <div className="card" style={{maxWidth:420}}>
        <h2>Admin — Confirm Payment</h2>
        <input className="input" placeholder="patientId" value={patientId} onChange={e=>setPatientId(e.target.value)} />
        <button className="btn" onClick={confirmPayment}>Confirm</button>
      </div>
    </Protected>
  );
}


'use client';
import Protected from '../../components/Protected';
import { useAuth } from '../../components/AuthContext';
import { api } from '../../lib/api';
import { useState } from 'react';

export default function Notifications() {
  const { token } = useAuth();
  const [payload, setPayload] = useState({ type: 'INFO', receiver: 'patient1', message: 'Your appointment is scheduled', time: '10:00', date: new Date().toISOString().slice(0,10) });

  async function send(e:any) {
    e.preventDefault();
    await api('/api/notifications/send', { method: 'POST', body: JSON.stringify(payload) }, token || undefined);
    alert('Notification sent');
  }

  return (
    <Protected>
      <div className="card" style={{maxWidth:600}}>
        <h2>Send Notification</h2>
        <form onSubmit={send} className="row">
          <input className="input" placeholder="type" value={payload.type} onChange={e=>setPayload({...payload, type:e.target.value})} />
          <input className="input" placeholder="receiver" value={payload.receiver} onChange={e=>setPayload({...payload, receiver:e.target.value})} />
          <input className="input" placeholder="message" value={payload.message} onChange={e=>setPayload({...payload, message:e.target.value})} />
          <input className="input" placeholder="time" value={payload.time} onChange={e=>setPayload({...payload, time:e.target.value})} />
          <input className="input" type="date" value={payload.date} onChange={e=>setPayload({...payload, date:e.target.value})} />
          <button className="btn" type="submit">Send</button>
        </form>
      </div>
    </Protected>
  );
}

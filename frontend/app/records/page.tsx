
'use client';
import { useEffect, useState } from 'react';
import Protected from '../../components/Protected';
import { useAuth } from '../../components/AuthContext';
import { api } from '../../lib/api';

export default function Records() {
  const { token, role } = useAuth();
  const [list, setList] = useState<any[]>([]);
  const [form, setForm] = useState({ name: '', description: '' });

  async function load() {
    const data = await api('/api/records', {}, token || undefined);
    setList(data);
  }
  useEffect(()=>{ load(); }, []);

  async function add(e:any) {
    e.preventDefault();
    await api('/api/records/add', { method: 'POST', body: JSON.stringify(form) }, token || undefined);
    setForm({ name: '', description: '' });
    await load();
  }

  async function amend(id: string) {
    await api(`/api/records/${id}/amend`, { method: 'PUT', body: JSON.stringify({ description: 'Updated by doctor' }) }, token || undefined);
    await load();
  }

  return (
    <Protected>
      <div className="card">
        <h2>Medical Records</h2>
        {(role === 'DOCTOR' || role === 'ADMIN') && (
          <form onSubmit={add} className="row">
            <input className="input" placeholder="name" value={form.name} onChange={e=>setForm({...form, name:e.target.value})} />
            <input className="input" placeholder="description" value={form.description} onChange={e=>setForm({...form, description:e.target.value})} />
            <button className="btn" type="submit">Add Record</button>
          </form>
        )}
        <table style={{marginTop:16}}>
          <thead><tr><th>Name</th><th>Description</th><th>Actions</th></tr></thead>
          <tbody>
            {list.map((r:any)=> (
              <tr key={r.id}>
                <td>{r.name}</td><td>{r.description}</td>
                <td>{(role==='DOCTOR'||role==='ADMIN') && <button className="btn secondary" onClick={()=>amend(r.id)}>Amend</button>}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </Protected>
  );
}


'use client';
import { useState } from 'react';
import { api } from '../../lib/api';
import { useAuth } from '../../components/AuthContext';
import { useRouter } from 'next/navigation';

export default function Login() {
  const [username, setUsername] = useState('patient1');
  const [password, setPassword] = useState('pass');
  const [role, setRole] = useState<'PATIENT'|'DOCTOR'|'ADMIN'>('PATIENT');
  const [error, setError] = useState<string|null>(null);
  const { login } = useAuth();
  const router = useRouter();

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    setError(null);
    try {
      const data = await api('/api/auth/login', {
        method: 'POST',
        body: JSON.stringify({ username, password })
      });
      login(data.token, data.username || username, role);
      router.push('/dashboard');
    } catch (err: any) {
      setError(err.message);
    }
  }

  return (
    <div className="card" style={{ maxWidth: 480 }}>
      <h2>Login</h2>
      <form onSubmit={submit} className="row">
        <input className="input" placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} />
        <input className="input" placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} />
        <div>
          <label>Role</label>
          <select className="input" value={role} onChange={e=>setRole(e.target.value as any)}>
            <option value="PATIENT">Patient</option>
            <option value="DOCTOR">Doctor</option>
            <option value="ADMIN">Admin</option>
          </select>
        </div>
        <button className="btn" type="submit">Login</button>
        {error && <p style={{color:'crimson'}}>{error}</p>}
      </form>
      <p style={{marginTop:12, fontSize:12}}>Note: Role selection is UI-side for now; connect to real roles from auth-service later.</p>
    </div>
  );
}

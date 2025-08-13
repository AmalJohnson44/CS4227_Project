
import './globals.css';
import Link from 'next/link';
import { AuthProvider } from '../components/AuthContext';
import NavLinks from '../components/NavLinks';

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>
        <AuthProvider>
          <header style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
            <div className="container" style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
              <div style={{ display: 'flex', alignItems: 'baseline', gap: 12 }}>
                <h1>Smart Healthcare</h1>
                <span className="badge">Appointments & Monitoring</span>
              </div>
              <nav style={{ display: 'flex', gap: 8 }}>
                <NavLinks />
              </nav>
            </div>
          </header>
          <main className="container" style={{ paddingTop: 24 }}>{children}</main>
        </AuthProvider>
      </body>
    </html>
  );
}

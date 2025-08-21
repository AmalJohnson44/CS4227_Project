
CREATE TABLE IF NOT EXISTS appointments (
  id VARCHAR(36) PRIMARY KEY,
  type VARCHAR(100) NOT NULL,
  surgery_or_hospital VARCHAR(200),
  doctor VARCHAR(100),
  patient VARCHAR(100),
  time VARCHAR(20),
  date DATE,
  status VARCHAR(20) DEFAULT 'PENDING'
);

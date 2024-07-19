CREATE UNIQUE INDEX IF NOT EXISTS idx_account_by_email ON account(email);

alter table wallet add constraint IF NOT EXISTS balance_nonnegative check (balance >= 0);

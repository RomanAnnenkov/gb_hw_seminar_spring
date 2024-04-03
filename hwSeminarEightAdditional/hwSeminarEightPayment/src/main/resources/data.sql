INSERT INTO accounts (id, account_name, account_amount)
VALUES ('1', 'company', '0')
ON CONFLICT (id) DO UPDATE
SET account_amount = EXCLUDED.account_amount;

INSERT INTO accounts (id, account_name, account_amount)
VALUES ('2', 'user', '100')
ON CONFLICT (id) DO UPDATE
SET account_amount = EXCLUDED.account_amount;
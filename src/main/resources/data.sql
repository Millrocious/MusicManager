INSERT INTO public._user (id, name, email, password, role, update_date, create_date)
VALUES (1, 'Admin', 'admin@example.com', '$2a$12$txTnPNjXm2D184gIqOZWeu69bl9y5IE6Sz/F0s9kSIoSxu5MhehNW', 'ADMIN', now(), now())
ON CONFLICT DO NOTHING;

INSERT INTO public._user (id, name, email, password, role, update_date, create_date)
VALUES (2, 'User', 'user@example.com', '$2a$12$wplWnt3oMlfhq6oO/5/7v.AXOjMEkeaGoYYaLrfu34HCfbl.NSTC.', 'USER', now(), now())
ON CONFLICT DO NOTHING;
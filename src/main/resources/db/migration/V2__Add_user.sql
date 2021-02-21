insert IGNORE into user (id,
                  email,
                  first_name,
                  last_name,
                  password,
                  is_account_non_expired,
                  is_account_non_locked,
                  is_credentials_non_expired,
                  is_enabled)
values (1, 'email', 'firstName', 'lastName', '$2a$10$MsWFOW3FisjccNFapcOEQ.Nnezmn4IwL2WQdMs1azibJ6fRKfyBve', true, true, true, true);

insert IGNORE into user (id,
                  email,
                  first_name,
                  last_name,
                  password,
                  is_account_non_expired,
                  is_account_non_locked,
                  is_credentials_non_expired,
                  is_enabled)
values (3, 'useremail', 'user', 'user', '$2a$10$ScXpEier52D/jPJE1Ais6.becBwoh6Z8X9OzDRXe07LHOihyjvn8y', true, true, true, true);

insert IGNORE into user_role (user_id, roles)
VALUES (1, 'ADMIN');

insert IGNORE into user_role (user_id, roles)
VALUES (3, 'USER');
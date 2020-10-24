INSERT INTO role(role_id, name) VALUES (1, 'ADMIN');
INSERT INTO role(role_id, name) VALUES (2, 'USER');

ALTER TABLE storm RENAME x TO LONGITUDE;
ALTER TABLE storm RENAME y TO LATITUDE;

-- Forecast implementation
UPDATE forecast SET rain1h = 0.0, rain3h = 0.0 WHERE rain1h IS NULL;

-- Add DM coordinates to storm entity
UPDATE storm SET latitude_dm = 0.0, longitude_dm = 0.0;
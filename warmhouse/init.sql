CREATE TABLE IF NOT EXISTS sensors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    location VARCHAR(100) NOT NULL,
    value FLOAT DEFAULT 0,
    unit VARCHAR(20),
    status VARCHAR(20) NOT NULL DEFAULT 'inactive',
    last_updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS permission (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_permission (
    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permission(id) ON DELETE CASCADE
);


INSERT INTO permission (name) VALUES ('READ'), ('WRITE'), ('ADMIN') ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    message TEXT,
    type VARCHAR(50) CHECK (type IN ('INFO', 'WARNING', 'ERROR')),
    sent BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS timers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    trigger_at TIMESTAMP,
    action VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS metrics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_id VARCHAR(255),
    metric_type VARCHAR(100),
    value DOUBLE,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS statistics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    metric_type VARCHAR(100),
    avg_value DOUBLE,
    min_value DOUBLE,
    max_value DOUBLE,
    calculation_time TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS cameras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    ip_address VARCHAR(100),
    recording BOOLEAN DEFAULT FALSE,
    motion_detection BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS smart_locks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    is_locked BOOLEAN NOT NULL DEFAULT TRUE,
    location VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS thermostats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    current_temperature DOUBLE,
    target_temperature DOUBLE,
    enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO notifications (title, message, type, sent, created_at) VALUES
('Система запущена', 'Микросервис уведомлений успешно стартовал.', 'INFO', true, NOW()),
('Температура превышена', 'Температура в гостиной достигла 30°C.', 'WARNING', false, NOW() - INTERVAL 1 HOUR),
('Ошибка подключения', 'Не удалось подключиться к камере ID: 5.', 'ERROR', false, NOW() - INTERVAL 30 MINUTE),
('Обновление доступно', 'Доступна новая версия прошивки для термостата.', 'INFO', true, NOW() - INTERVAL 1 DAY);

INSERT INTO timers (name, trigger_at, action, active) VALUES
('Включение отопления', '2025-04-05 07:00:00', 'turn_on_heating', true),
('Выключение света', '2025-04-05 23:00:00', 'turn_off_lights', true),
('Проверка камер', '2025-04-06 06:30:00', 'start_camera_surveillance', false),
('Резервное копирование', '2025-04-05 02:00:00', 'backup_system_data', true);

INSERT INTO cameras (name, ip_address, recording, motion_detection) VALUES
('Front Door Camera', '192.168.1.101', true, true),
('Garage Camera', '192.168.1.102', false, true),
('Backyard Camera', '192.168.1.103', true, false),
('Living Room Camera', '192.168.1.104', false, false);

INSERT INTO smart_locks (name, is_locked, location) VALUES
('Front Door Lock', true, 'Front Door'),
('Garage Lock', true, 'Garage'),
('Basement Lock', false, 'Basement'),
('Office Lock', true, 'Office');

INSERT INTO thermostats (name, current_temperature, target_temperature, enabled) VALUES
('Living Room Thermostat', 22.5, 23.0, true),
('Bedroom Thermostat', 20.0, 21.0, true),
('Kitchen Thermostat', 24.0, 22.0, true),
('Guest Room Thermostat', 18.5, 19.0, false);

INSERT INTO metrics (device_id, metric_type, value, timestamp) VALUES
('thermostat_001', 'temperature', 22.5, NOW() - INTERVAL 60 MINUTE),
('thermostat_001', 'temperature', 23.0, NOW() - INTERVAL 50 MINUTE),
('thermostat_002', 'temperature', 19.8, NOW() - INTERVAL 45 MINUTE),
('lock_001', 'power_usage', 0.5, NOW() - INTERVAL 40 MINUTE),
('camera_001', 'power_usage', 2.3, NOW() - INTERVAL 35 MINUTE),
('thermostat_001', 'temperature', 23.2, NOW() - INTERVAL 30 MINUTE),
('camera_001', 'power_usage', 2.4, NOW() - INTERVAL 25 MINUTE),
('thermostat_002', 'temperature', 20.1, NOW() - INTERVAL 20 MINUTE),
('lock_001', 'power_usage', 0.6, NOW() - INTERVAL 15 MINUTE),
('camera_001', 'power_usage', 2.5, NOW() - INTERVAL 10 MINUTE),
('thermostat_001', 'temperature', 23.5, NOW() - INTERVAL 5 MINUTE);

INSERT INTO statistics (metric_type, avg_value, min_value, max_value, calculation_time) VALUES
('temperature', 21.5, 19.8, 23.5, NOW() - INTERVAL 2 HOUR),
('power_usage', 1.3, 0.5, 2.5, NOW() - INTERVAL 2 HOUR),
('temperature', 23.2, 22.5, 23.5, NOW() - INTERVAL 30 MINUTE);

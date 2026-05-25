-- Players
INSERT INTO player (username, email, created_at) VALUES
('detective_alice', 'alice@game.com', NOW()),
('detective_bob', 'bob@game.com', NOW()),
('thief_eve', 'eve@game.com', NOW()),
('detective_charlie', 'charlie@game.com', NOW());

-- NPCs (also players but controlled by server)
INSERT INTO player (username, email, created_at) VALUES
('npc_patrol_guard', 'patrol@npc.game.com', NOW()),
('npc_witness', 'witness@npc.game.com', NOW()),
('npc_informant', 'informant@npc.game.com', NOW());

-- Game Map
INSERT INTO game_map (name, grid_width, grid_height, description) VALUES
('City Center', 10, 10, 'A dense urban map with streets, buildings and subway stations');

-- Locations
INSERT INTO location (map_id, name, coord_x, coord_y, type) VALUES
(1, 'Central Station',   1, 1, 'CHECKPOINT'),
(1, 'Old Market',        3, 1, 'STREET'),
(1, 'City Hall',         5, 1, 'BUILDING'),
(1, 'North Alley',       1, 3, 'STREET'),
(1, 'Subway Hub',        3, 3, 'CHECKPOINT'),
(1, 'East Boulevard',    5, 3, 'STREET'),
(1, 'Abandoned Factory', 1, 5, 'HIDEOUT'),
(1, 'Police HQ',         3, 5, 'BUILDING'),
(1, 'South Park',        5, 5, 'STREET'),
(1, 'Harbor',            5, 7, 'HIDEOUT');

-- Routes (connections between locations)
INSERT INTO route (map_id, from_location_id, to_location_id, transport, distance) VALUES
-- Central Station connections
(1, 1, 2, 'FOOT',    1),
(1, 1, 4, 'FOOT',    1),
(1, 1, 5, 'SUBWAY',  1),

-- Old Market connections
(1, 2, 1, 'FOOT',    1),
(1, 2, 3, 'FOOT',    1),
(1, 2, 5, 'FOOT',    1),

-- City Hall connections
(1, 3, 2, 'FOOT',    1),
(1, 3, 6, 'FOOT',    1),

-- North Alley connections
(1, 4, 1, 'FOOT',    1),
(1, 4, 7, 'FOOT',    1),
(1, 4, 5, 'VEHICLE', 2),

-- Subway Hub connections
(1, 5, 1, 'SUBWAY',  1),
(1, 5, 8, 'SUBWAY',  1),
(1, 5, 6, 'FOOT',    1),

-- East Boulevard connections
(1, 6, 3, 'FOOT',    1),
(1, 6, 5, 'FOOT',    1),
(1, 6, 9, 'FOOT',    1),

-- Abandoned Factory connections
(1, 7, 4, 'FOOT',    1),
(1, 7, 8, 'VEHICLE', 2),

-- Police HQ connections
(1, 8, 5, 'SUBWAY',  1),
(1, 8, 7, 'VEHICLE', 2),
(1, 8, 9, 'FOOT',    1),

-- South Park connections
(1, 9, 6, 'FOOT',    1),
(1, 9, 8, 'FOOT',    1),
(1, 9, 10, 'FOOT',   1),

-- Harbor connections
(1, 10, 9, 'FOOT',   1);

-- Game
INSERT INTO game (status, map_id, started_at, created_at) VALUES
('ACTIVE', 1, NOW(), NOW());

-- Game Players (humans)
INSERT INTO game_player (game_id, player_id, game_role, is_caught, joined_at) VALUES
(1, 1, 'DETECTIVE', FALSE, NOW()),  -- alice
(1, 2, 'DETECTIVE', FALSE, NOW()),  -- bob
(1, 3, 'THIEF',     FALSE, NOW()),  -- eve
(1, 4, 'DETECTIVE', FALSE, NOW());  -- charlie

-- Game Players (NPCs)
INSERT INTO game_player (game_id, player_id, game_role, is_caught, joined_at) VALUES
(1, 5, 'NPC', FALSE, NOW()),  -- patrol guard
(1, 6, 'NPC', FALSE, NOW()),  -- witness
(1, 7, 'NPC', FALSE, NOW());  -- informant

-- NPC Configs
INSERT INTO npc_config (game_player_id, npc_type, behavior, patrol_route, trigger_event) VALUES
(5, 'PATROL_GUARD', 'PATROL',     '[1, 2, 3, 6, 9, 8, 5, 1]', 'ALERT_DETECTIVES'),
(6, 'WITNESS',      'STATIONARY', NULL,                         'GENERATE_CLUE'),
(7, 'INFORMANT',    'STATIONARY', NULL,                         'REVEAL_TRANSPORT');

-- Moves (turn 1 — starting positions)
INSERT INTO move (game_id, player_id, from_location_id, to_location_id, transport, turn_number, moved_at) VALUES
(1, 1, NULL, 8,  'FOOT', 1, NOW()),   -- alice starts at Police HQ
(1, 2, NULL, 3,  'FOOT', 1, NOW()),   -- bob starts at City Hall
(1, 3, NULL, 7,  'FOOT', 1, NOW()),   -- eve starts at Abandoned Factory
(1, 4, NULL, 1,  'FOOT', 1, NOW()),   -- charlie starts at Central Station
(1, 5, NULL, 2,  'FOOT', 1, NOW()),   -- patrol guard starts at Old Market
(1, 6, NULL, 5,  'FOOT', 1, NOW()),   -- witness stays at Subway Hub
(1, 7, NULL, 8,  'FOOT', 1, NOW());   -- informant stays at Police HQ

-- Moves (turn 2)
INSERT INTO move (game_id, player_id, from_location_id, to_location_id, transport, turn_number, moved_at) VALUES
(1, 1, 8,  9,  'FOOT',   2, NOW()),   -- alice moves to South Park
(1, 2, 3,  6,  'FOOT',   2, NOW()),   -- bob moves to East Boulevard
(1, 3, 7,  4,  'FOOT',   2, NOW()),   -- eve moves to North Alley
(1, 4, 1,  5,  'SUBWAY', 2, NOW()),   -- charlie takes subway to Subway Hub
(1, 5, 2,  3,  'FOOT',   2, NOW()),   -- patrol guard moves to City Hall
(1, 6, 5,  5,  'FOOT',   2, NOW()),   -- witness stays at Subway Hub
(1, 7, 8,  8,  'FOOT',   2, NOW());   -- informant stays at Police HQ

-- Clues (witness generated a clue when eve passed through)
INSERT INTO clue (game_id, location_id, found_by_player_id, description, is_found, created_at, found_at) VALUES
(1, 7, NULL,  'Someone was seen leaving the Abandoned Factory in a hurry', FALSE, NOW(), NULL),
(1, 4, 1,     'A suspicious person was spotted in North Alley',            TRUE,  NOW(), NOW()),
(1, 5, NULL,  'Thief was seen using the subway',                           FALSE, NOW(), NULL);

-- Catch Attempts
INSERT INTO catch_attempt (game_id, detective_player_id, thief_player_id, location_id, successful, attempted_at) VALUES
(1, 1, 3, 9, FALSE, NOW());   -- alice tried to catch eve at South Park but failed

-- Player Stats
INSERT INTO player_stat (player_id, games_played, games_won, times_caught, thieves_caught) VALUES
(1, 5, 3, 0, 2),   -- alice
(2, 4, 2, 0, 1),   -- bob
(3, 6, 4, 2, 0),   -- eve
(4, 3, 1, 0, 0);   -- charlie